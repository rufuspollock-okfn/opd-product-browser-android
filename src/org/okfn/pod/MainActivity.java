package org.okfn.pod;


import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "";
	public String gtin = "";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//this.callScan(getCurrentFocus());
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    // Do something in response to button

        
		if(gtin.length() > 0) {
			EditText editText = (EditText) findViewById(R.id.edit_gtin);
			editText.setText(gtin);
		} else {
        
			EditText editText = (EditText) findViewById(R.id.edit_gtin);
			gtin = editText.getText().toString();
		}

	    int value_code = 99;
	    String json = "";

		//String imgpp1 = "http://product.okfn.org.s3.amazonaws.com/images/gtin/gtin-309/3095757343101.jpg";
		// http://localhost/github/products/explorer/product/3095757343101
		// http://www.le10sport.com/img/ico/icon-club/as-monaco.png
		// http://product.okfn.org.s3.amazonaws.com/images/gtin/gtin/309/3095757343101.jpg		
		//json = "{\"gtin\":{\"code\":\"3095757343101\",\"name\":\"La c\u00f4tes de porc cuite et pr\u00e9par\u00e9e dans le filet\",\"product-line\":\"Charcuterie\",\"weight\":\"2 x 70 g\",\"volume\":null,\"alcool\":null,\"img\":\"http:--product.okfn.org.s3.amazonaws.com-images-gtin-gtin-309-3095757343101.jpg\",\"img-default\":\"http:--www.product-open-data.com-images-coming-soon.jpg\"},\"brand\":{\"code\":\"469\",\"name\":\"Fleury Michon\",\"type\":\"Manufacturer-brand\",\"link\":\"www.fleurymichon.fr\",\"img\":\"http:--product.okfn.org.s3.amazonaws.com-images-brand-00000469.jpg\",\"img-default\":\"http:--www.product-open-data.com-images-coming-soon.jpg\"},\"group\":{\"code\":null,\"name\":null},\"gcp\":{\"code\":\"309575\"},\"gpc\":{\"img\":\"http:--www.product-open-data.com-images-gpc-50000000.jpg\",\"img-default\":\"http:--www.product-open-data.com-images-coming-soon.jpg\",\"segment\":{\"code\":\"50000000\",\"nom\":\"Food-Beverage-Tobacco\"},\"family\":{\"code\":\"50240000\",\"nom\":\"Meat-Poultry\"},\"class\":{\"code\":\"50240100\",\"nom\":\"Meat-Poultry - Prepared-Processed\"},\"brick\":{\"code\":null,\"nom\":null}},\"gepir\":{\"return-code\":{\"code\":\"0\",\"name\":\"No error\"},\"source\":\"GEPIR\",\"gln\":{\"code\":\"3010957500109\",\"name\":\"FLEURY MICHON CHARCUTERIE SAS\",\"address-1\":\"BP 1\",\"address-2\":\"\",\"address-3\":\"\",\"cp\":\"85700\",\"city\":\"POUZAUGES\",\"country\":\"FR\"}}}";

		if(gtin.length() > 0) {
			
			// CALL WEBSERVICE		
			try {
				json = new MyTask().execute("http://www.product-open-data.com/product/"+gtin).get();
				//Toast.makeText(this,"ok"+json,1000).show();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONObject object;
			try {
				object = new JSONObject(json);
				value_code = object.getInt("code");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		} else {
			value_code = 98;
		}

		
		switch(value_code){
	    case 0 : 
			Intent intent = new Intent(this, GtinFoundActivity.class);
			intent.putExtra(EXTRA_MESSAGE, json);
			startActivity(intent);
			break;
	    case 1:
			Intent intent3 = new Intent(this, GtinNotFoundActivity.class);
			startActivity(intent3);	
			break; 
	    case 2:
			Intent intent2 = new Intent(this, GtinNotValidActivity.class);
			startActivity(intent2);
			break; 
	    case 98:
			Toast.makeText(this,"Gtin code required",1000).show();
			break;
	    case 99:
			Toast.makeText(this,"No Internet Connection",1000).show();
			break;
	}

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem AboutMenuItem = menu.findItem(R.id.action_about);
		
		PackageInfo pinfo = null;
		try {
			pinfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AboutMenuItem.setTitle("POD browser - version "+pinfo.versionName);
		return true;
	}

	public void callScan(View view) {
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if(resultCode != RESULT_CANCELED){
			if (scanResult != null) {
				gtin = intent.getStringExtra("SCAN_RESULT");
				gtin = ("0000000000000"+gtin).substring(gtin.length());
				this.sendMessage(getCurrentFocus());
			} // end 'scanResult != null'
		} // end 'resultCode != RESULT_CANCELED'
	} // end 'onActivityResult'


}


