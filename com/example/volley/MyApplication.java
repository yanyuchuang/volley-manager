package com.example.volley;

import android.app.Application;

import com.android.volley.manager.RequestManager;

public class MyApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();

		RequestManager.getInstance().init(MyApplication.this);
	}

}