package com.qixiu.common.zhuojinniao.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * ScreenUtils Created by hanj on 14-9-25.
 */
public class ScreenUtils {
	private static int screenW;
	private static int screenH;
	private static float screenDensity;

	public static void initScreen(Activity mActivity) {
		DisplayMetrics metric = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
		screenW = metric.widthPixels;
		screenH = metric.heightPixels;
		screenDensity = metric.density;
	}

	public static int getScreenW() {
		return screenW;
	}

	public static int getScreenH() {
		return screenH;
	}

	public static float getScreenDensity() {
		return screenDensity;
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);

	}

}
