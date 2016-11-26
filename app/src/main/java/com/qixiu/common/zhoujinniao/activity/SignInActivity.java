package com.qixiu.common.zhoujinniao.activity;

import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qixiu.common.zhoujinniao.data.bean.SignInBean;
import com.qixiu.common.zhoujinniao.data.response.BaseResponse;
import com.qixiu.common.zhoujinniao.fragment.MainFragment;
import com.qixiu.common.zhoujinniao.manager.PreferenceManager;
import com.qixiu.common.zhoujinniao.manager.UserMananger;
import com.qixiu.common.zhoujinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhoujinniao.ui.adapter.SignInAdapter;
import com.qixiu.common.zhoujinniao.R;


public class SignInActivity extends BaseActivity implements
		OnItemClickListener, OnClickListener {

	ArrayList<SignInBean> list = new ArrayList<SignInBean>();

	SignInAdapter adapter;

	GridView gridView;

	RelativeLayout back;

	public static String sign = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		InitData();
		BindData();
		//getData();
	}

	private void BindData() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);

		for (int i = 0; i < 28; i++) {

			list.add(new SignInBean());
		}

		adapter = new SignInAdapter();

		adapter.setData(list);
		adapter.getContext(this);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(this);
		back.setOnClickListener(this);

	}

	public void getData() {

		UserMananger.Sign(this, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				BaseResponse response = pResponse;
				Toast.makeText(SignInActivity.this, "签到成功", Toast.LENGTH_SHORT)
						.show();

				if (response.m.contains("已签到")) {

				} else {

					if (Integer.parseInt(response.m) % 28 == 0) {
						adapter.sign = 28;
						sign = 28 + "";

					} else {
						adapter.sign = Integer.parseInt(response.m) % 28;
						sign = Integer.parseInt(response.m) % 28 + "";

					}

					adapter.notifyDataSetChanged();

					Log.d("aa", "签到第" + response.m + "天");
					Log.d("aa", "签到第" + Integer.parseInt(response.m) % 28 + "天");

				}

			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				// Toast.makeText(getContext(), "签到失败",
				// Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void InitData() {
		// TODO Auto-generated method stub
		gridView = (GridView) findViewById(R.id.gridview);
		back = (RelativeLayout) findViewById(R.id.get_back);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.get_back:

			finish();
			break;

		default:
			break;
		}
	}

	class Date {
		int[] a = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int[] b = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int sum = 0, date = 1, Date;

		public int zhizuo(int year, int month) {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				// System.out.println(a[month]);
				return a[month];
			else
				// System.out.println(b[month]);
				return b[month];
		}
	}

}


