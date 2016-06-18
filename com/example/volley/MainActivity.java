package com.example.volley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
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

	private static final String GET_URL = "http://www.panxw.com/about.html";

	private static final String POST_URL = "http://www.panxw.com/index.php";

	private static final String POST_JSON = "{\"action\":\"test\", \"info\":\"hello world\"}";




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.testGet();
//		this.testPost();
//		startActivity(new Intent(MainActivity.this, ImageTestActivity.class));
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
