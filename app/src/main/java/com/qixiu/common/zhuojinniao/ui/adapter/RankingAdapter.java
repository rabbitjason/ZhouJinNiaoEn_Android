package com.qixiu.common.zhuojinniao.ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.data.bean.RankingBean;
import com.qixiu.common.zhuojinniao.main.Config;
import com.qixiu.common.zhuojinniao.util.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qixiu.common.zhuojinniao.R;


public class RankingAdapter extends MyBaseAdapter {

//	RelativeLayout rlt, rlt1;
//
//	ImageView frist_img, two_img, no_number;
//
//	TextView frist_iphone, frist_gold, two_iphone, two_gold;
//
//	DisplayImageOptions options;
//
//	@Override
//	protected int getLayoutResID() {
//		// TODO Auto-generated method stub
//		return R.layout.item_ranking;
//	}
//
//	@Override
//	protected void findViews() {
//		// TODO Auto-generated method stub
//		options = Utils.getRoundedDisplayOptions(R.drawable.icon_img_default);
//		frist_img = (ImageView) findViewById(R.id.one_img);
//		frist_iphone = (TextView) findViewById(R.id.one_iphone);
//		frist_gold = (TextView) findViewById(R.id.one_gold);
//		two_img = (ImageView) findViewById(R.id.two_img);
//		two_iphone = (TextView) findViewById(R.id.two_iphone);
//		two_gold = (TextView) findViewById(R.id.two_gold);
//		rlt = (RelativeLayout) findViewById(R.id.rlt);
//		rlt1 = (RelativeLayout) findViewById(R.id.rlt1);
//		no_number = (ImageView) findViewById(R.id.no_number);
//	}
//
//	@Override
//	protected void dataBindView() {
//		// TODO Auto-generated method stub
//		RankingBean bean = (RankingBean) list.get(position);
//		if (position == 0) {
//
//			rlt.setVisibility(View.VISIBLE);
//			rlt1.setVisibility(View.GONE);
//			if (!TextUtils.isEmpty(bean.head)) {
//				ImageLoader.getInstance().displayImage(
//						Config.UrlImgString + bean.head, frist_img, options);
//			} else {
//
//				frist_img.setImageResource(R.drawable.default_icon);
//			}
//			frist_iphone.setText(bean.mobile);
//			frist_gold.setText(bean.total_profit);
//		} else {
//
//			rlt.setVisibility(View.GONE);
//			rlt1.setVisibility(View.VISIBLE);
//
//			if (position == 1) {
//
//				no_number.setImageResource(R.drawable.icon_no2);
//				if (!TextUtils.isEmpty(bean.head)) {
//					ImageLoader.getInstance().displayImage(
//							Config.UrlImgString + bean.head, two_img, options);
//				} else {
//
//					two_img.setImageResource(R.drawable.default_icon);
//				}
//				two_iphone.setText(bean.mobile);
//				two_gold.setText(bean.total_profit);
//
//			} else if (position == 2) {
//
//				no_number.setImageResource(R.drawable.icon_no3);
//				if (!TextUtils.isEmpty(bean.head)) {
//					ImageLoader.getInstance().displayImage(
//							Config.UrlImgString + bean.head, two_img, options);
//				} else {
//
//					two_img.setImageResource(R.drawable.default_icon);
//				}
//				two_iphone.setText(bean.mobile);
//				two_gold.setText(bean.total_profit);
//
//			} else if (position == 3) {
//
//				no_number.setImageResource(R.drawable.icon_no4);
//				if (!TextUtils.isEmpty(bean.head)) {
//					ImageLoader.getInstance().displayImage(
//							Config.UrlImgString + bean.head, two_img, options);
//				} else {
//
//					two_img.setImageResource(R.drawable.default_icon);
//				}
//				two_iphone.setText(bean.mobile);
//				two_gold.setText(bean.total_profit);
//
//			} else if (position == 4) {
//
//				no_number.setImageResource(R.drawable.icon_no5);
//				if (!TextUtils.isEmpty(bean.head)) {
//					ImageLoader.getInstance().displayImage(
//							Config.UrlImgString + bean.head, two_img, options);
//				} else {
//
//					two_img.setImageResource(R.drawable.default_icon);
//				}
//				two_iphone.setText(bean.mobile);
//				two_gold.setText(bean.total_profit);
//
//			} else if (position == 5) {
//
//				no_number.setImageResource(R.drawable.icon_no6);
//				if (!TextUtils.isEmpty(bean.head)) {
//					ImageLoader.getInstance().displayImage(
//							Config.UrlImgString + bean.head, two_img, options);
//				} else {
//
//					two_img.setImageResource(R.drawable.default_icon);
//				}
//				two_iphone.setText(bean.mobile);
//				two_gold.setText(bean.total_profit);
//
//			}
//		}
//
//	}
//
//	@Override
//	protected void addListeners() {
//		// TODO Auto-generated method stub
//
//	}

	RelativeLayout rlt, rlt1;

	ImageView frist_img, two_img, two_imgs;

	TextView frist_iphone, frist_gold, two_iphone, two_gold;
	// TextView number;

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
		// frist_img = (ImageView) findViewById(R.id.one_img);
		frist_iphone = (TextView) findViewById(R.id.one_iphone);
		frist_gold = (TextView) findViewById(R.id.one_gold);
		two_img = (ImageView) findViewById(R.id.two_img);
		two_iphone = (TextView) findViewById(R.id.two_iphone);
		two_gold = (TextView) findViewById(R.id.two_gold);
		rlt = (RelativeLayout) findViewById(R.id.rlt);
		rlt1 = (RelativeLayout) findViewById(R.id.rlt1);
		// number = (TextView) findViewById(R.id.number);
		two_imgs = (ImageView) findViewById(R.id.two_imgs);
	}

	@Override
	protected void dataBindView() {
		// TODO Auto-generated method stub
		RankingBean bean = (RankingBean) list.get(position);
		if (position == 0) {

			rlt.setVisibility(View.VISIBLE);
			rlt1.setVisibility(View.GONE);

			frist_iphone.setText(bean.mobile);
			frist_gold.setText(bean.total_profit);
		} else {

			rlt.setVisibility(View.GONE);
			rlt1.setVisibility(View.VISIBLE);

			if (position == 1) {

				two_img.setImageResource(R.drawable.icon_two);
				// number.setVisibility(View.GONE);
				two_img.setVisibility(View.VISIBLE);
				two_imgs.setVisibility(View.GONE);
				two_iphone.setText(bean.mobile);
				two_gold.setText(bean.total_profit);

			} else if (position == 2) {

				two_img.setImageResource(R.drawable.icon_three);
				// number.setVisibility(View.GONE);
				two_img.setVisibility(View.VISIBLE);
				two_imgs.setVisibility(View.GONE);
				two_iphone.setText(bean.mobile);
				two_gold.setText(bean.total_profit);

			} else if (position == 3) {

				two_img.setVisibility(View.GONE);
				two_imgs.setVisibility(View.VISIBLE);
				two_imgs.setImageResource(R.drawable.icon_four);
				// number.setText("4");
				// number.setVisibility(View.VISIBLE);
				two_iphone.setText(bean.mobile);
				two_gold.setText(bean.total_profit);

			} else if (position == 4) {

				two_img.setVisibility(View.GONE);
				two_imgs.setVisibility(View.VISIBLE);
				two_imgs.setImageResource(R.drawable.icon_five);
				// number.setText("5");
				// number.setVisibility(View.VISIBLE);
				two_iphone.setText(bean.mobile);

				two_gold.setText(bean.total_profit);

			} else if (position == 5) {

				two_img.setVisibility(View.GONE);
				two_imgs.setVisibility(View.VISIBLE);
				two_imgs.setImageResource(R.drawable.icon_six);
				// number.setText("5");
				// number.setVisibility(View.VISIBLE);
				two_iphone.setText(bean.mobile);

				two_gold.setText(bean.total_profit);
			} else if (position == 6) {

				two_img.setVisibility(View.GONE);
				two_imgs.setVisibility(View.VISIBLE);
				two_imgs.setImageResource(R.drawable.icon_seven);
				// number.setText("5");
				// number.setVisibility(View.VISIBLE);
				two_iphone.setText(bean.mobile);

				two_gold.setText(bean.total_profit);
			} else if (position == 7) {

				two_img.setVisibility(View.GONE);
				two_imgs.setVisibility(View.VISIBLE);
				two_imgs.setImageResource(R.drawable.icon_eight);
				// number.setText("5");
				// number.setVisibility(View.VISIBLE);
				two_iphone.setText(bean.mobile);

				two_gold.setText(bean.total_profit);
			} else if (position == 8) {

				two_img.setVisibility(View.GONE);
				two_imgs.setVisibility(View.VISIBLE);
				two_imgs.setImageResource(R.drawable.icon_nine);
				// number.setText("5");
				// number.setVisibility(View.VISIBLE);
				two_iphone.setText(bean.mobile);

				two_gold.setText(bean.total_profit);
			} else if (position == 9) {

				two_img.setVisibility(View.GONE);
				two_imgs.setVisibility(View.VISIBLE);
				two_imgs.setImageResource(R.drawable.icon_ten);
				// number.setText("5");
				// number.setVisibility(View.VISIBLE);
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
