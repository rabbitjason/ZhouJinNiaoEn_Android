package com.qixiu.common.zhuojinniao.util;

import java.io.File;
import java.math.BigDecimal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;

/**
 * 
 * @author caimingyu Description:缓存的工具类
 */
public class CacheUtils {
	/**
	 * 得到缓存大小
	 * 
	 * @param file
	 * @return
	 * 
	 * @throws Exception
	 * 
	 */

	public static String getCacheSize(File file) throws Exception {
		return getFormatSize(getFolderSize(file));
	}

	/**
	 * 得到文件的大小
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 * 
	 */
	public static long getFolderSize(File file) throws Exception {

		long size = 0;
		try {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				// 如果还有文件
				if (files[i].isDirectory()) {
					size = size + getFolderSize(files[i]);
				} else {
					size = size + files[i].length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return size;
	}

	/**
	 * 
	 * 格式化单位
	 * 
	 * @param size
	 * @return
	 * 
	 */
	public static String getFormatSize(double size) {
		double kiloByte = size / 1024;
		if (kiloByte < 1) {
			// return size + Byte;
			return size + "byte";
		}
		double megaByte = kiloByte / 1024;
		if (megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "KB";
		}

		double gigaByte = megaByte / 1024;
		if (gigaByte < 1) {
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "MB";
		}

		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
				+ "TB";
	}

	public static void cleanSharedPeference(Context context) {

	}

	/**
	 * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache) * * @param
	 * context
	 */
	public static void cleanExternalCache(Context context) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			deleteFilesByDirectory(context.getExternalCacheDir());
		}
	}

	/** * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory */
	private static void deleteFilesByDirectory(File directory) {
		if (directory != null && directory.exists() && directory.isDirectory()) {
			for (File item : directory.listFiles()) {
				item.delete();
			}
		}
	}
	
	
	 /**
     * * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * *
     *
     * @param context
     */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }
	

	public void SharedPreferencesaddData(Context context) {
		// 获取SharedPreferences对象

		SharedPreferences sp = context.getSharedPreferences("SP",
				Context.MODE_PRIVATE);
		// 存入数据
		Editor editor = sp.edit();
		editor.putString("STRING_KEY", "string");
		editor.putInt("INT_KEY", 0);
		editor.putBoolean("BOOLEAN_KEY", true);
		editor.commit();
	}
}
