package com.qixiu.common.zhuojinniao.activity;

import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qixiu.common.zhuojinniao.data.bean.SignInBean;
import com.qixiu.common.zhuojinniao.data.response.BaseResponse;
import com.qixiu.common.zhuojinniao.data.response.ProfileResponse;
import com.qixiu.common.zhuojinniao.manager.PreferenceManager;
import com.qixiu.common.zhuojinniao.manager.UserMananger;
import com.qixiu.common.zhuojinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhuojinniao.ui.adapter.SignInAdapter;
import com.qixiu.common.zhuojinniao.R;
import com.qixiu.common.zhuojinniao.ui.dialog.MiddleDialog;


public class SignInActivity extends BaseActivity implements
		OnItemClickListener, OnClickListener {

	ArrayList<SignInBean> list = new ArrayList<SignInBean>();

	SignInAdapter adapter;

	GridView gridView;

	RelativeLayout back;

	public static String sign = null;
    public String m;
    boolean isshow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);

		InitData();
		BindData();
		getData();
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
//				Toast.makeText(SignInActivity.this, "签到成功", Toast.LENGTH_SHORT)
//						.show();

				if (response.m.equals("")) {
                    isshow = true;

				} else {

					if (Integer.parseInt(response.m) % 28 == 0) {
						adapter.sign = 28;
						sign = 28 + "";

					} else {
						adapter.sign = Integer.parseInt(response.m) % 28;
						sign = Integer.parseInt(response.m) % 28 + "";

					}
                    m = response.m;
                    isshow = false;
					adapter.notifyDataSetChanged();
					Log.d("aa", "签到第" + response.m + "天");
					Log.d("aa", "签到第" + Integer.parseInt(response.m) % 28 + "天");

				}
                show();
			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				// Toast.makeText(getContext(), "签到失败",
				// Toast.LENGTH_SHORT).show();
                isshow = true;
                show();
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

	/** 显示奖励框 */

	public void show() {

		UserMananger.profile(this, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				ProfileResponse response = (ProfileResponse) pResponse;
				//Toast.makeText(SignInActivity.this, "奖励", Toast.LENGTH_SHORT);
				// if
				// (!response.o.today_profit.equals(PreferenceManager.getaward()))
				// {

				// Log.d("aa", "签到后的收益:" + PreferenceManager.getaward());

				final MiddleDialog dialog = new MiddleDialog(
						SignInActivity.this, false);
				// Toast.makeText(SignInActivity.this, "11111",
				// Toast.LENGTH_SHORT).show();
				if (!isshow) {
					// Toast.makeText(SignInActivity.this, "22222",
					// Toast.LENGTH_SHORT).show();
					dialog.show();

					dialog.showSign(true);
					PreferenceManager.saveaward(response.o.today_profit);
					PreferenceManager.savecredit(response.o.credit);
					if (Integer.parseInt(m) <= 7) {

						dialog.setAwardText("1");
					} else if (Integer.parseInt(m) > 7
							&& Integer.parseInt(m) <= 14) {
						dialog.setAwardText("2");
					} else if (Integer.parseInt(m) > 14
							&& Integer.parseInt(m) <= 21) {
						dialog.setAwardText("3");
					} else {
						dialog.setAwardText("4");
					}

					dialog.setNoShowClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub

							dialog.dismiss();
						}
					});
					new Handler().postDelayed(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					}, 5000);

				} else {
					// Toast.makeText(SignInActivity.this, "33333",
					// Toast.LENGTH_SHORT).show();
					dialog.show();
					dialog.showSign(false);
					dialog.Sign(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});

					new Handler().postDelayed(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					}, 2000);

				}
				// }
			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub

			}
		});

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


