package com.qixiu.common.zhuojinniao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qixiu.common.zhuojinniao.ui.view.LoadingView;
import com.qixiu.common.zhuojinniao.R;


/**
 * 
 * 作者:caimingyu 功能:基本碎片 创建时间:2016-5-13 下午2:33:20 参与者:
 */
public abstract class BaseFragement extends Fragment {
	protected View mView;
	protected Context context;
	private boolean hasInit;
	
	RelativeLayout loadingLayout;
	LoadingView loadingView;


	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mView = inflater.inflate(getLayoutResId(), null);
		hasInit = true;
		context = getActivity();
		initView();
		initData();
		addListeners();
		hasInit = true;
		return mView;
	}

	protected abstract void initView();

	protected abstract void initData();

	protected abstract void addListeners();

	protected abstract int getLayoutResId();

	// 減少代码冗余----------------------------
	// 1.启动一个页面，不带参数
	protected void jumpToActivity(Class toClass) {
		jumpToActivity(toClass, null);
	}

	// 2.启动一个页面，带参数
	protected void jumpToActivity(Class toClass, Bundle params) {
		Intent intent = new Intent(getActivity(), toClass);
		if (params != null)
			intent.putExtras(params);
		startActivity(intent);
	}

	// 3.启动一个页面，可以回调，不带参数
	protected void jumpToActivity(Class toClass, int requestCode) {
		jumpToActivity(toClass, null, requestCode);
	}

	// 4.启动一个页面，可以回调，带参数
	protected void jumpToActivity(Class toClass, Bundle params, int requestCode) {
		Intent intent = new Intent(getActivity(), toClass);
		if (params != null)
			intent.putExtras(params);
		startActivityForResult(intent, requestCode);
	}

	public View findViewById(int id) {
		return mView.findViewById(id);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (hasInit)
			loadData();
	}
	
	public void showLoading() {
		loadingLayout = (RelativeLayout) findViewById(R.id.loading);
		if (loadingView == null) {
			loadingView = new LoadingView(context);
			loadingLayout.addView(loadingView.getView());
		}
		loadingLayout.setVisibility(View.VISIBLE);
	}

	public void hideLoading() {
		if (loadingLayout != null)
			loadingLayout.setVisibility(View.GONE);
	}


	protected abstract void loadData();
}
