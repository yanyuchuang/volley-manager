package com.android.volley.manager;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * RequestManager
 * 
 */
public class RequestManager {

	public static final String ACCEPT_ENCODING = "Accept-Encoding";
	public static final String GZIP = "gzip";

	private volatile static RequestManager INSTANCE = null;
	private RequestQueue mRequestQueue = null;
	private final NetworkImageCache imageCache = new NetworkImageCache();
	private ImageLoader mImageLoader;

	private RequestManager() {}

	public void init(Context context) {
		this.mRequestQueue = Volley.newRequestQueue(context);
		mImageLoader = new ImageLoader(mRequestQueue,imageCache);
	}
	
	/**
	 * @description NetworkImageCache
	 */
	@SuppressLint("NewApi")
	public static class NetworkImageCache extends LruCache<String, Bitmap>
			implements ImageCache {

		public NetworkImageCache() {
			this(getDefaultLruCacheSize());
		}

		public NetworkImageCache(int sizeInKiloBytes) {
			super(sizeInKiloBytes);
		}

		@Override
		protected int sizeOf(String key, Bitmap value) {
			return value.getRowBytes() * value.getHeight() / 1024;
		}

		@Override
		public Bitmap getBitmap(String url) {
			return get(url);
		}

		@Override
		public void putBitmap(String url, Bitmap bitmap) {
			put(url, bitmap);
		}

		public static int getDefaultLruCacheSize() {
			final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
			final int cacheSize = maxMemory / 8;
			return cacheSize;
		}
	}

	/**
	 * SingleTon
	 * 
	 * @return  single Instance
	 */
	public static RequestManager getInstance() {
		if (null == INSTANCE) {
			synchronized (RequestManager.class) {
				if (null == INSTANCE) {
					INSTANCE = new RequestManager();
				}
			}
		}
		return INSTANCE;
	}

	public RequestQueue getRequestQueue() {
		return this.mRequestQueue;
	}
	
	/**
	 * 发起一个请求
	 * @param req
	 */
	public <T> void request(Request<T> req){
	    getRequestQueue().add(req);
	}
	 
	/**
	 * 获取ImageLoader
	 * @return
	 */
	public ImageLoader getImageLoader() {
	    return mImageLoader;
	}
	
	/**
	 * 取消任务
	 */
	public <T> void cacheRequest(Request<T> request){
		request.cancel();
	}

}
