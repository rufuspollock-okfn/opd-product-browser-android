package com.app.product_open_data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Communicator {

	public String readUrl(String urlString) throws Exception {
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
	
	public String executeHttpGet(String URL) throws Exception 
    {
		
        BufferedReader in = null;
        try 
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(URL));
            HttpResponse response = client.execute(request);
            InputStream ips  = response.getEntity().getContent();
            BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));

            StringBuilder sb = new StringBuilder();
            String s;
            while(true )
            {
                s = buf.readLine();
                if(s==null || s.length()==0)
                    break;
                sb.append(s);

            }
            buf.close();
            ips.close();
            return sb.toString();
        } 
        finally 
        {
            if (in != null) 
            {
                try 
                {
                    in.close();
                } 
                catch (IOException e)    
                {
                    Log.d("Exceptions !", e.toString());
                }
            }
        }
    }

	public Bitmap ReadImg(String URL) throws Exception {
		
		Bitmap bitmap = null;
		
		try {
			bitmap = BitmapFactory.decodeStream((InputStream)new URL(URL).getContent());
			  
		} catch (MalformedURLException e) {
			  e.printStackTrace();
			} catch (IOException e) {
			  e.printStackTrace();
			}
		
		return bitmap;		
		  
    }

	
}
