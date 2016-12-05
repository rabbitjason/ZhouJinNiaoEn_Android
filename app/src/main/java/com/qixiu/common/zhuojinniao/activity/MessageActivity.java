package com.qixiu.common.zhuojinniao.activity;

import java.util.ArrayList;

import com.qixiu.common.zhuojinniao.data.bean.MessageBean;
import com.qixiu.common.zhuojinniao.data.response.BaseResponse;
import com.qixiu.common.zhuojinniao.data.response.MegResponse;
import com.qixiu.common.zhuojinniao.data.responsedata.MessageData;
import com.qixiu.common.zhuojinniao.fragment.MainFragment;
import com.qixiu.common.zhuojinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhuojinniao.manager.PreferenceManager;
import com.qixiu.common.zhuojinniao.manager.UserMananger;
import com.qixiu.common.zhuojinniao.ui.adapter.MessageAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qixiu.common.zhuojinniao.R;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MessageActivity extends BaseActivity implements
		OnItemClickListener {

	RelativeLayout back;
	PullToRefreshListView mPullScrollView;
	ArrayList<MessageBean> list = new ArrayList<MessageBean>();
	static MessageAdapter adapter;
	int page = 1;
	String read = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		initView();
		initData();
		// getData(true);
		// showMessage();
	}

	private void initData() {
		// TODO Auto-generated method stub

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				finish();
			}
		});

		// list.add(new MessageBean());
		// list.add(new MessageBean());
		// list.add(new MessageBean());

		adapter = new MessageAdapter(this);
		adapter.setData(list);

		mPullScrollView.getRefreshableView().setAdapter(adapter);
		mPullScrollView.getRefreshableView().setOnItemClickListener(this);

		mPullScrollView
				.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						page = 1;
						getData(true);

					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView) {
						// TODO Auto-generated method stub
						page++;
						getData(true);
					}

				});

	}

	private void getData(boolean showLoding) {
		// TODO Auto-generated method stub

		if (showLoding) {

			showLoading();
		}

		UserMananger.getMsg(this, page, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				mPullScrollView.onRefreshComplete();
				hideLoading();
				MegResponse response = (MegResponse) pResponse;

				setData(response.o);

			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				mPullScrollView.onRefreshComplete();
				hideLoading();
			}
		});

	}

	private void setData(MessageData data) {
		// TODO Auto-generated method stub

		if (page == 1) {

			list.clear();
		}

		if (data.list != null && data.list.size() > 0) {

			list.addAll(data.list);
			adapter.notifyDataSetChanged();
		} else {

			if (page == 1) {

				adapter.notifyDataSetChanged();
			}
		}

	}

	private void initView() {
		// TODO Auto-generated method stub
		back = (RelativeLayout) findViewById(R.id.get_back);
		mPullScrollView = (PullToRefreshListView) findViewById(R.id.pull_layout);
		mPullScrollView.setMode(Mode.BOTH);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		getData(true);
		// showMessage();
//		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

//		MobclickAgent.onPause(this);
	}

	public void showMessage() {

		if (read.equals("0")) {

			adapter.red.setVisibility(View.VISIBLE);
			adapter.notifyDataSetChanged();
		} else if (read.equals("1")) {

			adapter.red.setVisibility(View.GONE);
			adapter.notifyDataSetChanged();
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		Intent intent = new Intent(this, MessageDetailsActivity.class);
		intent.putExtra("title", list.get(arg2 - 1).title);
		intent.putExtra("info", list.get(arg2 - 1).info);
		intent.putExtra("time", list.get(arg2 - 1).time);
		intent.putExtra("read", list.get(arg2 - 1).id);
		// read = list.get(arg2 - 1).read;
		startActivity(intent);
	}

}
