package com.qixiu.common.zhuojinniao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.R;


public class WithdrawOneActivity extends BaseActivity implements
		OnClickListener {

	ImageView back;
	WebView web;
	RelativeLayout rlt1, rlt2, add, add1;
	LinearLayout ll;
	TextView withdraw, withdraw1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdraw);

		InitData();
		BindData();
		isZhorEn();

	}

	private void BindData() {
		// TODO Auto-generated method stub

		back.setOnClickListener(this);
		rlt1.setOnClickListener(this);
		rlt2.setOnClickListener(this);

	}

	private void InitData() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.get_back);
		web = (WebView) findViewById(R.id.web);
		ll = (LinearLayout) findViewById(R.id.ll);
		rlt1 = (RelativeLayout) findViewById(R.id.rlt1);
		rlt2 = (RelativeLayout) findViewById(R.id.rlt2);
		withdraw = (TextView) findViewById(R.id.withdraw);
		withdraw1 = (TextView) findViewById(R.id.withdraw1);
		add = (RelativeLayout) findViewById(R.id.add);
		add1 = (RelativeLayout) findViewById(R.id.add1);

	}

	public void isZhorEn() {

//		if (getResources().getConfiguration().locale.getLanguage().equals("en")) {

			web.setVisibility(View.GONE);
			ll.setVisibility(View.VISIBLE);

//		} else if (getResources().getConfiguration().locale.getLanguage()
//				.equals("zh")) {
//			String gold = getIntent().getStringExtra("total");
//
//			ll.setVisibility(View.GONE);
//
//		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (arg0.getId()) {
		case R.id.get_back:

			finish();
			break;

		case R.id.rlt1:
			intent = new Intent(WithdrawOneActivity.this,
					WithdrawTwoActivity.class);

			startActivity(intent);

			break;

		case R.id.rlt2:

			intent = new Intent(WithdrawOneActivity.this,
					WithdrawTwoActivity.class);
			startActivity(intent);
			break;
		}
	}
}
