package com.example.volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.manager.RequestManager;
import com.android.volley.toolbox.StringRequest;

/**
 * 测试程序
 * 
 *
 */
public class MainActivity extends Activity{

	private static final String GET_URL = "http://www.baidu.com";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.testGet();
	}
	
	Listener<String> mStringListener = new Listener<String>(){
		
		@Override
		public void onResponse(String response) {
			Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
			startActivity(new Intent(MainActivity.this, ImageTestActivity.class));
		}
		
	};
	
	ErrorListener errorListener = new ErrorListener(){
		
		@Override
		public void onErrorResponse(VolleyError error) {
			
		}
		
	};
	
	StringRequest stringRequest = new StringRequest(GET_URL, mStringListener, errorListener);

	private void testGet() {
		RequestManager.getInstance().request(stringRequest);
	}
	
	
	@Override
	protected void onStop() {
		super.onStop();
		RequestManager.getInstance().cacheRequest(stringRequest);
	}
}
