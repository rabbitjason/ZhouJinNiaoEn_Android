package com.qixiu.common.zhoujinniao.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface.OnClickListener;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.qixiu.common.zhoujinniao.main.App;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
//import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * 工具类
 * 
 * @author Daker
 * 
 */
public class Utils {

	/** 判断是否是email */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/** 确认菜单 */
	public static void showConfirmDialog(Context context, String title,
			String content, String okString, Boolean cancelable,
			OnClickListener pListener) {
		new AlertDialog.Builder(context).setTitle(title).setMessage(content)
				.setPositiveButton(okString, pListener).show()
				.setCancelable(cancelable);
	}

	public static void showSelectDialog() {

	}

	/** 判断密码 */
	public static boolean isPassword(String value) {
		String telRegex = "^[A-Za-z0-9\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\`\\~\\-\\=\\,\\.\\;\\:\\<\\>\\?\\|]{6,22}$";
		if (value.equals(""))
			return false;
		else
			return value.matches(telRegex);
	}

	public static DisplayImageOptions getOptions(int defaultResId) {
		return getOptions2(defaultResId);
		// return new DisplayImageOptions.Builder().cacheInMemory(true)
		// .resetViewBeforeLoading(false)
		// .bitmapConfig(Bitmap.Config.RGB_565)
		// .showImageOnLoading(defaultResId)
		// .showImageForEmptyUri(defaultResId)
		// .showImageOnFail(defaultResId).cacheOnDisc(false)
		// .imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();
	}

	public static DisplayImageOptions getOptions2(int defaultResId) {
		DisplayImageOptions options;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(defaultResId) // 设置图片在下载期间显示的图片
				.showImageForEmptyUri(defaultResId)// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(defaultResId) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis为你设置的下载前的延迟时间
				// 设置图片加入缓存前，对bitmap进行设置
				// .preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
//				.displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
//				.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
				.build();

		return options;
	}

	public static DisplayImageOptions getRoundedDisplayOptions(int resId) {
		return new DisplayImageOptions.Builder().delayBeforeLoading(0)
				.cacheInMemory(true).showImageOnLoading(resId).cacheInMemory(true).cacheOnDisc(true)
				.resetViewBeforeLoading(false).cacheOnDisc(false)
				.bitmapConfig(Bitmap.Config.RGB_565).showStubImage(resId)
				.showImageOnFail(resId)
				.displayer(new RoundedBitmapDisplayer(360))
				.showImageForEmptyUri(resId).build();
	}

	/** 时间工具 */
	@SuppressLint("SimpleDateFormat")
	public static String formatData(String dataa) {
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		Date date;
		try {
			date = sdf.parse(dataa);
			SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd HH:mm");
			String string = sdf1.format(date);
			return string;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataa;
	}

	public static int dp2px(Context context, int dp) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	public static void openInputMethos(Activity aActivity) {
		try {
			InputMethodManager imm = (InputMethodManager) aActivity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeInputMethod(Context context, View v) {
		InputMethodManager inputmanger = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

	/** 判断是否为图片 */
	public static boolean isImage(String fileName) {
		return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")
				|| fileName.endsWith(".png");
	}

	/** 是否存在sdcard */
	public static boolean isSDcardOK() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	public static String getSDcardRoot() {
		if (isSDcardOK()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}

		return null;
	}

	/** 时间显示成年月日 */
	public static String getDateString(long date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return month + "月" + day + "日";
	}

	/** 时间工具类，今天、明天、后天等 */
	public static String getDateTip(long date) {
		long todayDate = Calendar.getInstance().getTimeInMillis();
		Date d1 = new Date(todayDate);
		Date d2 = new Date(date);
		int bd = getBetweenDay(d1, d2);
		if (bd == 0)
			return "今天";
		else if (bd == 1) {
			return "明天";
		} else if (bd == 2) {
			return "后天";
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date);
			int day = calendar.get(Calendar.DAY_OF_WEEK);
			Log.i("test", "day" + day);
			switch (day) {
			case 1:
				return "周日";
			case 2:
				return "周一";
			case 3:
				return "周二";
			case 4:
				return "周三";
			case 5:
				return "周四";
			case 6:
				return "周五";
			case 7:
				return "周六";
			default:
				break;
			}
			return "";
		}
	}

	public static int getBetweenDay(Date date1, Date date2) {
		Calendar d1 = new GregorianCalendar();
		d1.setTime(date1);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(date2);
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		System.out.println("days=" + days);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	public static boolean isToday(long date) {
		Calendar calendar = Calendar.getInstance();
		int today = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTimeInMillis(date);
		if (today == calendar.get(Calendar.DAY_OF_YEAR)) {
			return true;
		}
		return false;
	}

	/** MD5算法 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** 读取assets中的图片 */
	public static Bitmap getBitmap(int i) {
		Bitmap img = null;

		AssetManager am = App.getContext().getAssets();
		try {
			InputStream is = am.open("face/" + i + ".png");
			img = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return img;
	}

}
