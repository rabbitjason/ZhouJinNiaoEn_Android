package com.qixiu.common.zhuojinniao.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


import com.qixiu.common.zhuojinniao.ui.adapter.ViewPagerAdapter;
import com.qixiu.common.zhuojinniao.R;

/**
 * 引导页
 */
public class GuideActivity extends Activity implements OnPageChangeListener {

	private Context mContext;

	private ViewPager guideViewpager;

	private LinearLayout ll;

	private ViewPagerAdapter vpAdapter;
	private List<View> views;

	// 引导图片资源
	private static final int[] pics = { R.drawable.guide_fragment_main_1,
			R.drawable.guide_fragment_main_2, R.drawable.guide_fragment_main_3,
			R.drawable.guide_fragment_main_4 };

	// 记录当前选中位置
	private int lastPoint = 0;
	private String office_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);

		mContext = this;

		initViews();
	}

	private void initViews() {

		guideViewpager = (ViewPager) findViewById(R.id.guideViewpager);
		views = new ArrayList<View>();
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		// 初始化引导图片列表
		for (int i = 0; i < pics.length; i++) { 
			ImageView iv = new ImageView(this);
			iv.setScaleType(ScaleType.CENTER_CROP);
			iv.setLayoutParams(mParams);
			iv.setImageResource(pics[i]);
			views.add(iv);
		}
		// 初始化Adapter
		vpAdapter = new ViewPagerAdapter(mContext, views);
		guideViewpager.setAdapter(vpAdapter);
		// 绑定回调
		guideViewpager.setOnPageChangeListener(this);
		guideViewpager.setCurrentItem(0);
		// 初始化底部小点
		//initDots();
		// 初始化轮播广告数据
		initRollAdData();
	}

	private void initDots() {
		// 循环取得小点图片
//		for (int i = 0; i < pics.length; i++) {
//			// 实例化指示点
//			ImageView point = new ImageView(mContext);
//			//point.setImageResource(R.drawable.point_seletor2);
//			int widthAndHeight = PhoneUtil.dp2px(mContext, 10);
//			int margin = PhoneUtil.dp2px(mContext, 20);
//			LayoutParams params = new LayoutParams(widthAndHeight,
//					widthAndHeight);
//			params.leftMargin = margin;
//			params.rightMargin = margin;
//			point.setLayoutParams(params);
//
//			ll.addView(point);
//			// 设置第一个高亮显示
//			if (i == 0) {
//				point.setEnabled(true);
//			} else {
//				point.setEnabled(false);
//			}
//		}
	}

	private void initRollAdData() {
		// 获取轮播图资源
//		Map<String, String> paramsMap = new HashMap<String, String>();
//		paramsMap.put("office_id", office_id);
//		mDataInterface.rollAdvertisements(paramsMap, new OnResponseListener() {
//
//			@Override
//			public void onSuccess(Object t) {
//				final List<RollAdBean> list;
//				int result = JsonUtil.getResultCode(t.toString());
//				if (result == 1) {
//					Response response = JSON.parseObject(t.toString(),
//							Response.class);
//					list = JSON.parseArray(response.getInfo().toString(),
//							RollAdBean.class);
//				} else {
//					list = new ArrayList<RollAdBean>();
//				}
//				LiverApplication.addrollAdListToCache(office_id + "rollAd",
//						list);
//
//			}
//
//			@Override
//			public void onStart() {
//			}
//
//			@Override
//			public void onFailed(int resultCode, String errorMsg) {
//				ToastUtil.debug(mContext, errorMsg);
//			}
//		});
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {
		setCurView(position);
		//setCurDot(position);
	}

	/** *设置当前的引导页 ***/
	private void setCurView(int position) {
		if (position < 0 || position >= pics.length) {
			// 不循环
			return;
		}
		guideViewpager.setCurrentItem(position);
	}

	/** *设置当前的小圆点 ***/
	private void setCurDot(int position) {
		// 设置对应的页面高亮
		ll.getChildAt(position).setEnabled(true);
		// 是上次的点不显示高亮
		ll.getChildAt(lastPoint).setEnabled(false);
		lastPoint = position;
	}
}
