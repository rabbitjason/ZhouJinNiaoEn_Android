package com.qixiu.common.zhoujinniao.manager;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.qixiu.common.zhoujinniao.activity.HelpActivity;
import com.qixiu.common.zhoujinniao.main.Config;
import com.loopj.android.http.Base64;

public class WebManager {
	// /**临时链接*/
	// public static String zhifuxieyi() {
	// return "http://www.quanminshow.com/";
	// }

	public static void startWebView(Activity activity, String name, String url,
			int requestCode) {

		Intent it = new Intent(activity, HelpActivity.class);
		it.putExtra("url", url);
		it.putExtra("name", name);
		if (requestCode == 0) {

			activity.startActivity(it);
		} else {
			activity.startActivityForResult(it, requestCode);
		}
	}

	public static void startWebView(Activity activity, String name, String url,
			String right, int requestCode) {
		Intent it = new Intent(activity, HelpActivity.class);
		it.putExtra("url", url);
		it.putExtra("name", name);
		it.putExtra("right", right);
		if (requestCode == 0) {
			activity.startActivity(it);
		} else {
			activity.startActivityForResult(it, requestCode);
		}
	}

	/** 常见问题 */
	public static String help() {
		// http://115.28.94.239/Goldpecker/App/User/help
		return "http://115.28.94.239/Goldpecker/App/User/help";
	}

	/** 百度 */
	public static String url() {
		return "http://www.baidu.com";

	}

}
