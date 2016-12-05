package com.qixiu.common.zhuojinniao.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;

/** 时间 */
public class TimeUtils {

	@SuppressLint("SimpleDateFormat")
	public static String getTime() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm");
		String date = sDateFormat.format(new java.util.Date());
		return date;
	}
}
