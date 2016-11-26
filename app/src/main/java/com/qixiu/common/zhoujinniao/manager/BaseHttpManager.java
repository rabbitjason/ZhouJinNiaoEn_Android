package com.qixiu.common.zhoujinniao.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.qixiu.common.zhoujinniao.data.response.BaseResponse;
import com.qixiu.common.zhoujinniao.main.App;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.qixiu.common.zhoujinniao.R;

import cz.msebera.android.httpclient.Header;


public class BaseHttpManager {

	public interface BaseCallListener {
		public void onSuccess(BaseResponse pResponse);

		public void onFail(BaseResponse pResponse);
	}

	private static AsyncHttpClient mAsyncHttpClient = new AsyncHttpClient();

	private static Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg == null)
				return;
			String tips = (String) msg.obj;
			switch (msg.what) {
			case 1:
				if (tips != null && tips.length() > 0)
					Toast.makeText(App.getContext(), tips, Toast.LENGTH_SHORT)
							.show();
				break;
			default:
				break;
			}
		}
	};

	private static void sendError(String value) {
		Message msg = mHandler.obtainMessage();
		msg.what = 1;
		msg.obj = value;
		mHandler.sendMessage(msg);
	}

	private static boolean isNetworkConnected(Context context) {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		if (mNetworkInfo != null) {
			return mNetworkInfo.isAvailable();
		}
		return false;
	}

	public static void post(Context context, String url, RequestParams data,
			Class<? extends BaseResponse> responseClass,
			BaseCallListener pListener) {
		connect(context, 0, url, data, responseClass, pListener);
	}

	public static void get(Context context, String url, RequestParams data,
			Class<? extends BaseResponse> responseClass,
			BaseCallListener pListener) {
		connect(context, 1, url, data, responseClass, pListener);
	}

	public static void put(Context context, String url, RequestParams data,
			Class<? extends BaseResponse> responseClass,
			BaseCallListener pListener) {
		connect(context, 2, url, data, responseClass, pListener);
	}

	public static void delete(Context context, String url, RequestParams data,
			Class<? extends BaseResponse> responseClass,
			BaseCallListener pListener) {
		connect(context, 3, url, data, responseClass, pListener);
	}

	private static void connect(Context context, int method, String url,
			RequestParams data, Class<? extends BaseResponse> responseClass,
			BaseCallListener pListener) {
		Log.i("test", url);
		AsyncHttpResponseHandler handler = getAsyncHttpResponseHandler(context,
				responseClass, pListener);
		if (isNetworkConnected(context)) {
			if (method == 0) {
				/** post */
				if (data == null) {
					mAsyncHttpClient.post(context, url, null, handler);
				} else
					mAsyncHttpClient.post(context, url, data, handler);
			} else if (method == 1) {
				/** get */
				if (data != null)
					mAsyncHttpClient.get(context, url, data, handler);
				else {
					mAsyncHttpClient.get(context, url, handler);
				}
			} else if (method == 2) {
				/** put **/
				mAsyncHttpClient.put(context, url, data, handler);
			} else if (method == 3) {
				/** delete **/
				mAsyncHttpClient.delete(url, data, handler);
			}
		} else {
			sendError(context.getResources().getString(R.string.error_netfail));
			if (pListener != null)
				pListener.onFail(null);
		}
	}

//    private AsyncHttpResponseHandler asy = new AsyncHttpResponseHandler() {
//        @Override
//        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//        }
//
//        @Override
//        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//        }
//    };


	private static AsyncHttpResponseHandler getAsyncHttpResponseHandler(
			final Context context,
			final Class<? extends BaseResponse> responseClass,
			final BaseCallListener pListener) {

		return new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				try {
					String jsonString = new String(responseBody, "UTF-8");
					Log.i("test", "jsonString" + jsonString);
					final BaseResponse response = new Gson().fromJson(
							jsonString, BaseResponse.class);
					if (response != null) {
						if (response.c == 1) {
							pListener.onSuccess(new Gson().fromJson(jsonString,
									responseClass));
						} else {
							sendError("error:" + response.m);
							if (pListener != null)
								pListener.onFail(null);
						}
					} else {
						sendError(context.getResources().getString(
								R.string.error_datafail));
						if (pListener != null)
							pListener.onFail(null);
					}
				} catch (Exception e) {
					e.printStackTrace();
					sendError(context.getResources().getString(
							R.string.error_datafail));
					if (pListener != null)
						pListener.onFail(null);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
					Throwable error) {
				sendError(context.getResources().getString(
						R.string.error_requestfail)
						+ "code" + statusCode);
				if (pListener != null)
					pListener.onFail(null);
			}
		};
	}
}
