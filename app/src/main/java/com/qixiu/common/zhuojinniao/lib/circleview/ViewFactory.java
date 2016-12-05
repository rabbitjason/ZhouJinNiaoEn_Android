package com.qixiu.common.zhuojinniao.lib.circleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.qixiu.common.zhuojinniao.util.Utils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qixiu.common.zhuojinniao.R;

/**
 * ImageView创建工厂
 */
public class ViewFactory {

	/**
	 * 获取ImageView视图的同时加载显示url
	 * 
	 * @param text
	 * @return
	 */
	public static ImageView getImageView(Context context, String url) {
		ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(
				R.layout.view_banner, null);
		ImageLoader.getInstance().displayImage(url, imageView,
				Utils.getOptions(R.drawable.icon_img_default));
		return imageView;
	}
}
