package com.qixiu.common.zhoujinniao.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.qixiu.common.zhoujinniao.data.bean.JavaBean;
import com.qixiu.common.zhoujinniao.manager.PreferenceManager;
import com.qixiu.common.zhoujinniao.ui.adapter.CountryAdapter;
import com.qixiu.common.zhoujinniao.R;


public class WithdrawThreeActivity extends BaseActivity implements
		OnClickListener, OnItemClickListener {

	ImageView back;

	ListView country;
	TextView Save, Cancel;
	ArrayList<JavaBean> list = new ArrayList<JavaBean>();
	int index = 0;

	CountryAdapter adapter;

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
		setContentView(R.layout.activity_withdraw_three);

		InitData();
		BindData();

	}

	private void BindData() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 13; i++) {

			list.add(new JavaBean(country_name[i], country_img[i]));

		}
		adapter = new CountryAdapter();
		adapter.setData(list);
		country.setAdapter(adapter);

		back.setOnClickListener(this);
		country.setOnItemClickListener(this);
		Save.setOnClickListener(this);
		Cancel.setOnClickListener(this);
		// country.set

	}

	private void InitData() {
		// TODO Auto-generated method stub
		back = (ImageView) findViewById(R.id.get_back);
		country = (ListView) findViewById(R.id.listview);
		Save = (TextView) findViewById(R.id.Save);
		Cancel = (TextView) findViewById(R.id.Cancel);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.get_back:

			finish();
			break;

		case R.id.Save:

			Intent data = new Intent();

			data.putExtra("name", PreferenceManager.getname());

			data.putExtra("index", PreferenceManager.getimg());
			setResult(0x002, data);

			finish();
			break;
		case R.id.Cancel:

			finish();
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		// arg1.setBackgroundResource(R.drawable);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).is = false;
		}

		list.get(arg2).is = true;

		adapter.notifyDataSetChanged();

		if (list.get(arg2).is == true) {

			index = arg2;
		}

	}
}
