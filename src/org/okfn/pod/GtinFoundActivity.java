package org.okfn.pod;


import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

public class GtinFoundActivity extends Activity {

	@SuppressWarnings("unused")
	@SuppressLint("NewApi") 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gtin_found);
		// Show the Up button in the action bar.
		setupActionBar();

		  //new Thread(new Runnable() {
			    //public void run() {

	
		
	    String value_gtin_code = null;
	    String value_gtin_name = null;
		String value_gtin_product_line = null;
		String value_gtin_weight = null;
		String value_gtin_volume = null;
		String value_gtin_alcool = null;
		String value_gtin_img = null;
		String value_gtin_img_default = null;

		String value_brand_code = null;
		String value_brand_name = null;
		String value_brand_type = null;
		String value_brand_link = null;
		String value_brand_img = null;
		String value_brand_img_default = null;
	
		String value_group_code = null;
		String value_group_name = null;

		String value_gcp_code = null;

		String value_gpc_img = null;
		String value_gpc_img_default = null;
		String value_gpc_segment_code = null;
		String value_gpc_segment_name = null;
		String value_gpc_family_code = null;
		String value_gpc_family_name = null;								
		String value_gpc_class_code = null;
		String value_gpc_class_name = null;
		String value_gpc_brick_code = null;
		String value_gpc_brick_name = null;

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
			JSONObject gtin = object.getJSONObject("gtin");
			value_gtin_code 			= gtin.getString("code");
			value_gtin_name 			= gtin.getString("name");			
			value_gtin_product_line 	= gtin.getString("product-line");
			value_gtin_weight 		= gtin.getString("weight");
			value_gtin_volume 		= gtin.getString("volume");
			value_gtin_alcool 		= gtin.getString("alcool");
			value_gtin_img 			= gtin.getString("img");

			JSONObject brand = object.getJSONObject("brand");		
			value_brand_code 				= brand.getString("code");
			value_brand_name 				= brand.getString("name");
			value_brand_type 				= brand.getString("type");
			value_brand_link 				= brand.getString("link");	
			value_brand_img 				= brand.getString("img");
		
			JSONObject group = object.getJSONObject("group");	
			value_group_code 			= group.getString("code");
			value_group_name 			= group.getString("name");

			JSONObject gcp = object.getJSONObject("gcp");
			value_gcp_code 			= gcp.getString("code");	

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
		
			
			JSONObject gpc = object.getJSONObject("gpc");
			value_gpc_img 			= gpc.getString("img");	
			JSONObject gpc_segment = gpc.getJSONObject("segment");
			value_gpc_segment_code 			= gpc_segment.getString("code");
			value_gpc_segment_name 			= gpc_segment.getString("name");
			JSONObject gpc_family = gpc.getJSONObject("family");
			value_gpc_family_code 			= gpc_family.getString("code");
			value_gpc_family_name 			= gpc_family.getString("name");									
			JSONObject gpc_class = gpc.getJSONObject("class");
			value_gpc_class_code 			= gpc_class.getString("code");
			value_gpc_class_name 			= gpc_class.getString("name");	
			JSONObject gpc_brick = gpc.getJSONObject("brick");
			value_gpc_brick_code 			= gpc_brick.getString("code");
			value_gpc_brick_name 			= gpc_brick.getString("name");	


				
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// GET IMAGES ---------------------- 
		
		// GTIN IMG
		
		new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
        .execute(value_gtin_img);
		
		// BRAND IMG
		
		new DownloadImageTask((ImageView) findViewById(R.id.imageView2))
        .execute(value_brand_img);
		
		/*	
		// GTIN IMG	
		ImageView i;
		Bitmap bitmap = null;	
		i = (ImageView)findViewById(R.id.imageView1);

		try {
			bitmap = BitmapFactory.decodeStream((InputStream)new URL(value_gtin_img).getContent());
			i.setImageBitmap(bitmap); 
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		// BRAND IMG
		ImageView i2;
		Bitmap bitmap2 = null;	
		i2 = (ImageView)findViewById(R.id.imageView2);


		try {
			bitmap2 = BitmapFactory.decodeStream((InputStream)new URL(value_brand_img).getContent());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    i2.setImageBitmap(bitmap2); 

		*/
		String Address_gln = value_gepir_gln_address_1;
		if(value_gepir_gln_address_1 != null && !value_gepir_gln_address_1.isEmpty())  Address_gln = Address_gln+"\n";
		Address_gln = Address_gln+value_gepir_gln_address_2;
		if(value_gepir_gln_address_2 != null && !value_gepir_gln_address_2.isEmpty()) Address_gln = Address_gln+"\n";
		Address_gln = Address_gln+value_gepir_gln_address_3;
		if(value_gepir_gln_address_3 != null && !value_gepir_gln_address_3.isEmpty())  Address_gln = Address_gln+"\n";
		Address_gln = Address_gln+value_gepir_gln_cp+ " "+value_gepir_gln_city+"\n";
		Address_gln = Address_gln+value_gepir_gln_country+"\n";
		
		// DISPLAY RESULT ---------------------- 	
		
		// GTIN CD
		TextView tv_gtin_code = (TextView) findViewById(R.id.textView1);
		tv_gtin_code.setTextSize(12);
		tv_gtin_code.setText(value_gtin_code);
		
		// GCP CODE
		TextView tv_gcp_code = (TextView) findViewById(R.id.textView6);
		tv_gcp_code.setTextSize(12);
		tv_gcp_code.setText(value_gcp_code);

		// BRAND
		TextView tv_brand_name = (TextView) findViewById(R.id.textView3);
		tv_brand_name.setTextSize(12);
		tv_brand_name.setText(value_brand_name);
		
		// GTIN NAME
		TextView tv_gtin_name = (TextView) findViewById(R.id.textView2);
		tv_gtin_name.setTextSize(12);
		if(value_gtin_product_line != "null") {
			tv_gtin_name.setText(value_gtin_product_line+" - "+value_gtin_name);
		} else {
			tv_gtin_name.setText(value_gtin_name);
		}

		// WEIGHT & VOLUME
		TextView tv_gtin_weigth = (TextView) findViewById(R.id.textView7);
		tv_gtin_weigth.setTextSize(12);
		if( (value_gtin_weight != "null") || (value_gtin_volume != "null" ) ) {
			
			String weigth_volume = "";
			
			if(value_gtin_weight != "null") weigth_volume = value_gtin_weight;
			weigth_volume = weigth_volume+" ";
			if(value_gtin_volume != "null") weigth_volume = weigth_volume+value_gtin_volume; 
			tv_gtin_weigth.setText(weigth_volume);
			
		} else {
			tv_gtin_weigth.setText("-");
		}

		
		
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

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		  ImageView bmImage;

		  public DownloadImageTask(ImageView bmImage) {
		      this.bmImage = bmImage;
		  }

		  protected Bitmap doInBackground(String... urls) {
		      String urldisplay = urls[0];
		      Bitmap mIcon11 = null;
		      try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		      } catch (Exception e) {
		          Log.e("Error", e.getMessage());
		          e.printStackTrace();
		      }
		      return mIcon11;
		  }

		  protected void onPostExecute(Bitmap result) {
		      bmImage.setImageBitmap(result);
		  }
		}
	
}
