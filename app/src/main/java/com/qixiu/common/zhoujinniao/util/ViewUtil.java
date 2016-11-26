package com.qixiu.common.zhoujinniao.util;

import android.app.Activity;
import android.view.View;

/**
 * findviewById()的工具类
 * @author Darker
 *
 */
public class ViewUtil {
	/**
     * activity.findViewById()
     * @param context
     * @param id
     * @return
     */
    public static <T extends View> T findViewById(Activity context, int id,Class<T> clazz) {
        return (T) context.findViewById(id);
    }

    /**
     * rootView.findViewById()
     * @param rootView
     * @param id
     * @return
     */
    public static <T extends View> T findViewById(View rootView, int id,Class<T> clazz) {
        return (T) rootView.findViewById(id);
    }
}
