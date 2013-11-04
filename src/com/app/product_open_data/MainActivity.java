package com.app.product_open_data;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    // Do something in response to button

	    int value_code = 99;
	    String json = "";

		EditText editText = (EditText) findViewById(R.id.edit_gtin);
		String gtin = editText.getText().toString();

		//String imgpp1 = "http://product.okfn.org.s3.amazonaws.com/images/gtin/gtin-309/3095757343101.jpg";
		//http://localhost/github/products/explorer/product/3095757343101
		// http://www.le10sport.com/img/ico/icon-club/as-monaco.png
		// http://product.okfn.org.s3.amazonaws.com/images/gtin/gtin/309/3095757343101.jpg		
		//json = "{\"gtin\":{\"code\":\"3095757343101\",\"name\":\"La c\u00f4tes de porc cuite et pr\u00e9par\u00e9e dans le filet\",\"product-line\":\"Charcuterie\",\"weight\":\"2 x 70 g\",\"volume\":null,\"alcool\":null,\"img\":\"http:--product.okfn.org.s3.amazonaws.com-images-gtin-gtin-309-3095757343101.jpg\",\"img-default\":\"http:--www.product-open-data.com-images-coming-soon.jpg\"},\"brand\":{\"code\":\"469\",\"name\":\"Fleury Michon\",\"type\":\"Manufacturer-brand\",\"link\":\"www.fleurymichon.fr\",\"img\":\"http:--product.okfn.org.s3.amazonaws.com-images-brand-00000469.jpg\",\"img-default\":\"http:--www.product-open-data.com-images-coming-soon.jpg\"},\"group\":{\"code\":null,\"name\":null},\"gcp\":{\"code\":\"309575\"},\"gpc\":{\"img\":\"http:--www.product-open-data.com-images-gpc-50000000.jpg\",\"img-default\":\"http:--www.product-open-data.com-images-coming-soon.jpg\",\"segment\":{\"code\":\"50000000\",\"nom\":\"Food-Beverage-Tobacco\"},\"family\":{\"code\":\"50240000\",\"nom\":\"Meat-Poultry\"},\"class\":{\"code\":\"50240100\",\"nom\":\"Meat-Poultry - Prepared-Processed\"},\"brick\":{\"code\":null,\"nom\":null}},\"gepir\":{\"return-code\":{\"code\":\"0\",\"name\":\"No error\"},\"source\":\"GEPIR\",\"gln\":{\"code\":\"3010957500109\",\"name\":\"FLEURY MICHON CHARCUTERIE SAS\",\"address-1\":\"BP 1\",\"address-2\":\"\",\"address-3\":\"\",\"cp\":\"85700\",\"city\":\"POUZAUGES\",\"country\":\"FR\"}}}";

		if(gtin.length() > 0) {
			// CALL WEBSERVICE
			try {
				json = readUrl("http://www.product-open-data.com/product/"+gtin);
				JSONObject object = new JSONObject(json);
				value_code = object.getInt("code");
				
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
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
		return true;
	}
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }      
	    
	}

}


