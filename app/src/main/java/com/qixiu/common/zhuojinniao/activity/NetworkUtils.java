package com.qixiu.common.zhuojinniao.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.qixiu.common.zhuojinniao.main.App;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断网络连接状态
 * 
 */
public class NetworkUtils {
	private static NetworkUtils instance = null;

	private Context context;

	private NetworkUtils() {
		context = App.getContext();
	}

	public static synchronized NetworkUtils instance() {
		if (null == instance) {
			instance = new NetworkUtils();
		}
		return instance;
	}

	public boolean isNetworkConnected() {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断WIFI网络是否可用
	 * 
	 * @return null
	 */
	public boolean isWifiConnected() {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 判断MOBILE网络是否可用
	 * 
	 * @return null
	 */
	public boolean isMobileConnected() {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public int getConnectedType() {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}

	/**************************************************************************************/

	/**
	 * 获取Image信息
	 */

	private static Bitmap getBitmapData(String url) {
		URL imgurl = null;
		Bitmap bitmap = null;

		HttpURLConnection urlConnection;
		try {
			imgurl = new URL(url);
			urlConnection = (HttpURLConnection)
					imgurl.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();
			InputStream is = urlConnection.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

//	public static Bitmap getBitmapData(String imgUrl) {
//		Bitmap bmp = null;
//		InputStream inpStream = null;
//		try {
//			HttpGet http = new HttpGet(imgUrl);
//			HttpClient client = new DefaultHttpClient();
//			HttpResponse response = client.execute(http);
//			HttpEntity httpEntity = response.getEntity();
//			BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(
//					httpEntity);
//			// 获取数据流
//			inpStream = bufferedHttpEntity.getContent();
//			bmp = BitmapFactory.decodeStream(inpStream);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			if (inpStream != null) {
//				try {
//					inpStream.close();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//			}
//		}
//		return bmp;
//	}

	/**
	 * 获取url的InputStream
	 */
	public static InputStream getInputStream(String urlStr) {
        URL imgurl = null;
		InputStream inpStream = null;
        HttpURLConnection urlConnection;
        try {
            imgurl = new URL(urlStr);
            urlConnection = (HttpURLConnection)
                    imgurl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            inpStream = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return inpStream;
	}

//	public static InputStream getInputStream(String urlStr) {
//		InputStream inpStream = null;
//		try {
//			HttpGet http = new HttpGet(urlStr);
//			HttpClient client = new DefaultHttpClient();
//			HttpResponse response = client.execute(http);
//			HttpEntity httpEntity = response.getEntity();
//			BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(
//					httpEntity);
//			// 获取数据流
//			inpStream = bufferedHttpEntity.getContent();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			if (inpStream != null) {
//				try {
//					inpStream.close();
//				} catch (IOException ex) {
//					ex.printStackTrace();
//				}
//			}
//		}
//		return inpStream;
//	}
}
