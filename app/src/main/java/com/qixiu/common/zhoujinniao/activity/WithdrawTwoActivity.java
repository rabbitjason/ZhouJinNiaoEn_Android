package com.qixiu.common.zhoujinniao.activity;

import com.qixiu.common.zhoujinniao.manager.PreferenceManager;
import com.qixiu.common.zhoujinniao.R;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.RenderPriority;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WithdrawTwoActivity extends BaseActivity implements
		OnClickListener {

	ImageView back;
	WebView web;
	EditText email;
	ImageView select_country, countryimg;
	TextView country, submit;
	String country_name[] = { "Australian", "Brazil", "Russia", "Philippines",
			"Canada", "Malaysia", "America", "Mexico", "Formosa", "Hongkong",
			"Singapore", "Italy", "England" };

	int country_img[] = { R.drawable.australian, R.drawable.brazil,
			R.drawable.russia, R.drawable.philippines, R.drawable.canada,
			R.drawable.malaysia, R.drawable.america, R.drawable.mexico,
			R.drawable.formosa, R.drawable.hongkong, R.drawable.singapore,
			R.drawable.italy, R.drawable.england };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrwa_two);

		InitData();
		BindData();

	}

	private void BindData() {
		// TODO Auto-generated method stub

		// CountryData();

		back.setOnClickListener(this);
		select_country.setOnClickListener(this);
		submit.setOnClickListener(this);

		if (email.getText().toString() != null) {

			PreferenceManager.savename(email.getText().toString());
			email.setText(PreferenceManager.getname());
		}

		if (PreferenceManager.getname() != null) {

			email.setText(PreferenceManager.getname());
		}

	}

	private void InitData() {
		// TODO Auto-generated method stub

		back = (ImageView) findViewById(R.id.get_back);
		web = (WebView) findViewById(R.id.web);
		select_country = (ImageView) findViewById(R.id.select_country);
		countryimg = (ImageView) findViewById(R.id.countryimg);
		country = (TextView) findViewById(R.id.country);
		email = (EditText) findViewById(R.id.email);
		submit = (TextView) findViewById(R.id.submit);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.get_back:

			finish();
			break;

		case R.id.select_country:

			Intent intent = new Intent(WithdrawTwoActivity.this,
					WithdrawThreeActivity.class);

			startActivityForResult(intent, 0x001);

			break;
		case R.id.submit:

			Toast.makeText(WithdrawTwoActivity.this, "提交", Toast.LENGTH_SHORT)
					.show();
			break;
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);

		if (arg0 == 0x001 && arg1 == 0x002) {

			if (arg2.getStringExtra("name") != null) {

				country.setText("             "
						+ country_name[Integer.parseInt(PreferenceManager
								.getimg())]);
			}

			Log.d("aa", country.getText().toString() + "___country");
			if (arg2.getStringExtra("index") != null) {

				countryimg.setImageResource(country_img[Integer
						.parseInt(PreferenceManager.getimg())]);
			}

		}
	}

	public void CountryData() {

		if (PreferenceManager.getname() != null) {
			country.setText(PreferenceManager.getname());
		}

		if (PreferenceManager.getimg() != null) {

			countryimg.setImageResource(country_img[Integer
					.parseInt(PreferenceManager.getimg())]);
		}
	}
}
