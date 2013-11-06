package org.okfn.pod;


import android.os.AsyncTask;

public class MyTask extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... params) { 
		String page = null;
		try {
			page = new Communicator().readUrl(params[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		return page;
	}

}
