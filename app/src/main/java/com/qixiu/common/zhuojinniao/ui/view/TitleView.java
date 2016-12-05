package com.qixiu.common.zhuojinniao.ui.view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.main.App;
import com.qixiu.common.zhuojinniao.R;


public class TitleView extends BaseView {
	TextView title_text, right_text;
	ImageView backImageView;

	public TitleView(Context context) {
		super(context);
	}

	@Override
	protected int getLayoutID() {
		return R.layout.layout_title;
	}

	@Override
	protected void initView() {
		title_text = (TextView) mView.findViewById(R.id.title_text);
		right_text = (TextView) mView.findViewById(R.id.right_text);
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
		right_text.setVisibility(v);
	}

	public void setRightValue(String text, View.OnClickListener pClickListener) {
		right_text.setText(text);
		right_text.setOnClickListener(pClickListener);
	}

}
