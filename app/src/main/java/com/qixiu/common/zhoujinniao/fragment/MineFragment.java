package com.qixiu.common.zhoujinniao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qixiu.common.zhoujinniao.ui.view.LoadingView;
import com.qixiu.common.zhoujinniao.ui.view.TitleView;
import com.qixiu.common.zhoujinniao.R;


public class MineFragment extends Fragment {
	View mView;
	boolean hasInit;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mView = inflater.inflate(R.layout.fragment_mine, container, false);
		hasInit = true;
		initView();
		return mView;
	}

	private void initView() {
		RelativeLayout titleLayout = (RelativeLayout) mView
				.findViewById(R.id.title_layout);
		TitleView titleView = new TitleView(getActivity());
		titleLayout.addView(titleView.getView());
		titleView.setTitle("首页");
	}

	@Override
	public void onResume() {
		super.onResume();
		if (hasInit)
			loadData();
	}

	private void loadData() {

	}

	RelativeLayout loadingLayout;
	LoadingView loadingView;

	public void showLoading() {
		loadingLayout = (RelativeLayout) mView.findViewById(R.id.loading);
		if (loadingView == null) {
			loadingView = new LoadingView(getActivity());
			loadingLayout.addView(loadingView.getView());
		}
		loadingLayout.setVisibility(View.VISIBLE);
	}

	public void hideLoading() {
		if (loadingLayout != null)
			loadingLayout.setVisibility(View.GONE);
	}
}
