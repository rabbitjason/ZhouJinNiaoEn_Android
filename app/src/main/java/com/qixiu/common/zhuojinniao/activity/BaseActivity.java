package com.qixiu.common.zhuojinniao.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.qixiu.common.zhuojinniao.main.App;
import com.qixiu.common.zhuojinniao.ui.view.LoadingView;
import com.qixiu.common.zhuojinniao.util.ViewUtil;
import com.qixiu.common.zhuojinniao.R;


public class BaseActivity extends FragmentActivity {
	RelativeLayout loadingLayout;
	LoadingView loadingView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		App.mCurrentActivity = this;

		// 横屏设置
		if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		super.onResume();
	}

	public void showLoading() {
		loadingLayout = (RelativeLayout) findViewById(R.id.loading);
		if (loadingView == null) {
			loadingView = new LoadingView(this);
			loadingLayout.addView(loadingView.getView());
		}
		loadingLayout.setVisibility(View.VISIBLE);
	}

	public void hideLoading() {
		if (loadingLayout != null)
			loadingLayout.setVisibility(View.GONE);
	}

	public <E extends View> E findID(int resId, Class<E> clazz) {
		return ViewUtil.findViewById(this, resId, clazz);
	}

}
