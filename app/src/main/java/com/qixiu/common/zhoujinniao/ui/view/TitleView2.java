package com.qixiu.common.zhoujinniao.ui.view;

import com.qixiu.common.zhoujinniao.main.App;
import com.qixiu.common.zhoujinniao.R;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TitleView2 extends BaseView {

	TextView title_text;
	ImageView right_img;
	ImageView backImageView;

	public TitleView2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getLayoutID() {
		// TODO Auto-generated method stub
		return R.layout.layout_title2;
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		title_text = (TextView) mView.findViewById(R.id.title_text);
		right_img = (ImageView) mView.findViewById(R.id.right_img);
		backImageView = (ImageView) mView.findViewById(R.id.back_image);
		setBackListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				App.mCurrentActivity.finish();
			}
		});
	}

	public void setTitle(String name) {
		title_text.setText(name);
	}

	public void setBackListener(View.OnClickListener pClickListener) {
		backImageView.setOnClickListener(pClickListener);
	}

	public void setBackVisibility(int v) {
		backImageView.setVisibility(v);
	}

	public void setRightTextVisible(int v) {
		right_img.setVisibility(v);
	}

	public void setRightValue(View.OnClickListener pClickListener) {
		right_img.setOnClickListener(pClickListener);
	}

}
