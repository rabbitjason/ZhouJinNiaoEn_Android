package com.qixiu.common.zhoujinniao.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.common.zhoujinniao.data.bean.RankingBean;
import com.qixiu.common.zhoujinniao.main.Config;
import com.qixiu.common.zhoujinniao.util.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qixiu.common.zhoujinniao.R;


public class RankingAdapter extends MyBaseAdapter {

	RelativeLayout rlt, rlt1;

	ImageView frist_img, two_img, no_number;

	TextView frist_iphone, frist_gold, two_iphone, two_gold;

	DisplayImageOptions options;

	@Override
	protected int getLayoutResID() {
		// TODO Auto-generated method stub
		return R.layout.item_ranking;
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		options = Utils.getRoundedDisplayOptions(R.drawable.icon_img_default);
		frist_img = (ImageView) findViewById(R.id.one_img);
		frist_iphone = (TextView) findViewById(R.id.one_iphone);
		frist_gold = (TextView) findViewById(R.id.one_gold);
		two_img = (ImageView) findViewById(R.id.two_img);
		two_iphone = (TextView) findViewById(R.id.two_iphone);
		two_gold = (TextView) findViewById(R.id.two_gold);
		rlt = (RelativeLayout) findViewById(R.id.rlt);
		rlt1 = (RelativeLayout) findViewById(R.id.rlt1);
		no_number = (ImageView) findViewById(R.id.no_number);
	}

	@Override
	protected void dataBindView() {
		// TODO Auto-generated method stub
		RankingBean bean = (RankingBean) list.get(position);
		if (position == 0) {

			rlt.setVisibility(View.VISIBLE);
			rlt1.setVisibility(View.GONE);
			if (!TextUtils.isEmpty(bean.head)) {
				ImageLoader.getInstance().displayImage(
						Config.UrlImgString + bean.head, frist_img, options);
			} else {

				frist_img.setImageResource(R.drawable.default_icon);
			}
			frist_iphone.setText(bean.mobile);
			frist_gold.setText(bean.total_profit);
		} else {

			rlt.setVisibility(View.GONE);
			rlt1.setVisibility(View.VISIBLE);

			if (position == 1) {

				no_number.setImageResource(R.drawable.icon_no2);
				if (!TextUtils.isEmpty(bean.head)) {
					ImageLoader.getInstance().displayImage(
							Config.UrlImgString + bean.head, two_img, options);
				} else {

					two_img.setImageResource(R.drawable.default_icon);
				}
				two_iphone.setText(bean.mobile);
				two_gold.setText(bean.total_profit);

			} else if (position == 2) {

				no_number.setImageResource(R.drawable.icon_no3);
				if (!TextUtils.isEmpty(bean.head)) {
					ImageLoader.getInstance().displayImage(
							Config.UrlImgString + bean.head, two_img, options);
				} else {

					two_img.setImageResource(R.drawable.default_icon);
				}
				two_iphone.setText(bean.mobile);
				two_gold.setText(bean.total_profit);

			} else if (position == 3) {

				no_number.setImageResource(R.drawable.icon_no4);
				if (!TextUtils.isEmpty(bean.head)) {
					ImageLoader.getInstance().displayImage(
							Config.UrlImgString + bean.head, two_img, options);
				} else {

					two_img.setImageResource(R.drawable.default_icon);
				}
				two_iphone.setText(bean.mobile);
				two_gold.setText(bean.total_profit);

			} else if (position == 4) {

				no_number.setImageResource(R.drawable.icon_no5);
				if (!TextUtils.isEmpty(bean.head)) {
					ImageLoader.getInstance().displayImage(
							Config.UrlImgString + bean.head, two_img, options);
				} else {

					two_img.setImageResource(R.drawable.default_icon);
				}
				two_iphone.setText(bean.mobile);
				two_gold.setText(bean.total_profit);

			}
		}

	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

}
