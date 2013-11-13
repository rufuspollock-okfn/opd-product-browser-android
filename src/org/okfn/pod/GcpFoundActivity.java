package org.okfn.pod;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class GcpFoundActivity extends Activity {

	@SuppressWarnings("unused")
	@SuppressLint("NewApi") 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gcp_found);
		// Show the Up button in the action bar.
		setupActionBar();

		  //new Thread(new Runnable() {
			    //public void run() {

	
		
	   

		String value_gepir_source = null;
		String value_gepir_return_code_code = null;
		String value_gepir_return_code_name = null;			
		String value_gepir_gln_code = null;
		String value_gepir_gln_name = null;
		String value_gepir_gln_address_1 = null;
		String value_gepir_gln_address_2 = null;
		String value_gepir_gln_address_3 = null;
		String value_gepir_gln_cp = null;
		String value_gepir_gln_city = null;
		String value_gepir_gln_country = null;
		String json = "";
		String extra_json = "";

	    // Get the message from the intent
	    Intent intent = getIntent();
	    extra_json = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		try {
			
			JSONObject object = new JSONObject(extra_json);

			// GTIN
			
			JSONObject gepir = object.getJSONObject("gepir");
			value_gepir_source 			= gepir.getString("source");	
			JSONObject gepir_return_code = gepir.getJSONObject("return-code");
			value_gepir_return_code_code 			= gepir_return_code.getString("code");
			value_gepir_return_code_name 			= gepir_return_code.getString("name");	
			JSONObject gepir_gln = gepir.getJSONObject("gln");				
			value_gepir_gln_code 			= gepir_gln.getString("code");
			value_gepir_gln_name 			= gepir_gln.getString("name");	
			value_gepir_gln_address_1		= gepir_gln.getString("address-1");	
			value_gepir_gln_address_2		= gepir_gln.getString("address-2");	
			value_gepir_gln_address_3		= gepir_gln.getString("address-3");	
			value_gepir_gln_cp 				= gepir_gln.getString("cp");	
			value_gepir_gln_city 			= gepir_gln.getString("city");	
			value_gepir_gln_country 		= gepir_gln.getString("country");	
		

				
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		String Address_gln = value_gepir_gln_address_1;
		if(value_gepir_gln_address_1 != null && !value_gepir_gln_address_1.isEmpty())  Address_gln = Address_gln+"\n";
		Address_gln = Address_gln+value_gepir_gln_address_2;
		if(value_gepir_gln_address_2 != null && !value_gepir_gln_address_2.isEmpty()) Address_gln = Address_gln+"\n";
		Address_gln = Address_gln+value_gepir_gln_address_3;
		if(value_gepir_gln_address_3 != null && !value_gepir_gln_address_3.isEmpty())  Address_gln = Address_gln+"\n";
		Address_gln = Address_gln+value_gepir_gln_cp+ " "+value_gepir_gln_city+"\n";
		Address_gln = Address_gln+value_gepir_gln_country+"\n";
		

		
		
		// GEPIR Data 
		
		TextView tv_gepir_gln_name = (TextView) findViewById(R.id.textView4);
		tv_gepir_gln_name.setTextSize(12);
		tv_gepir_gln_name.setText(value_gepir_gln_name);

		TextView tv_gepir_gln_address_1 = (TextView) findViewById(R.id.textView5);
		tv_gepir_gln_address_1.setTextSize(12);
		tv_gepir_gln_address_1.setText(Address_gln);

	       //}
		    
	 // }).start();		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gtin_found, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	
}
