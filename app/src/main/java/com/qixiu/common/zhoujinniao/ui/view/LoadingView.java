package com.qixiu.common.zhoujinniao.ui.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.qixiu.common.zhoujinniao.R;


public class LoadingView extends BaseView {
	RelativeLayout bgLayout;

	public LoadingView(Context context) {
		super(context);
	}

	@Override
	protected int getLayoutID() {
		return R.layout.view_loading_layout;
	}

	@Override
	protected void initView() {
		bgLayout = (RelativeLayout) mView.findViewById(R.id.bg_layout);
		bgLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

			}
		});
	}

}
