package com.qixiu.common.zhuojinniao.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.data.response.BaseResponse;
import com.qixiu.common.zhuojinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhuojinniao.manager.UserMananger;
import com.qixiu.common.zhuojinniao.R;
//import com.umeng.analytics.MobclickAgent;

public class MessageDetailsActivity extends BaseActivity {

	RelativeLayout back;

	TextView title, info, time;

	String read = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_details);

		initView();
		initData();
		getData();
	}

	private void initView() {
		// TODO Auto-generated method stub

		back = (RelativeLayout) findViewById(R.id.get_back);
		title = (TextView) findViewById(R.id.title);
		info = (TextView) findViewById(R.id.info);
		time = (TextView) findViewById(R.id.time);
	}

	private void initData() {
		// TODO Auto-generated method stub

		title.setText(getIntent().getStringExtra("title"));
		info.setText(getIntent().getStringExtra("info"));
		time.setText(getIntent().getStringExtra("time"));
		read = getIntent().getStringExtra("read");

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				finish();
			}
		});

	}

	public void getData() {

		UserMananger.read(this, read, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub

				Log.d("aasdas", "已查看");
			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

//		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

//		MobclickAgent.onPause(this);
	}
}
