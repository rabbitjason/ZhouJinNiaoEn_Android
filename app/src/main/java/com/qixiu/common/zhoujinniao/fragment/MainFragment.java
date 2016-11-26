package com.qixiu.common.zhoujinniao.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.common.zhoujinniao.activity.DetailActivity;

import com.qixiu.common.zhoujinniao.activity.InvitationActivity;
import com.qixiu.common.zhoujinniao.activity.RankingActivity;
import com.qixiu.common.zhoujinniao.activity.SignInActivity;
import com.qixiu.common.zhoujinniao.activity.DownloadActivity;
import com.qixiu.common.zhoujinniao.activity.WithdrawOneActivity;
import com.qixiu.common.zhoujinniao.data.bean.ImageTextBean;
import com.qixiu.common.zhoujinniao.data.bean.TaskBean;
import com.qixiu.common.zhoujinniao.data.response.BaseResponse;
import com.qixiu.common.zhoujinniao.data.response.ProfileResponse;
import com.qixiu.common.zhoujinniao.data.responsedata.ProfileData;
import com.qixiu.common.zhoujinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhoujinniao.manager.UserMananger;
import com.qixiu.common.zhoujinniao.ui.adapter.ImgeTextAdapter;
import com.qixiu.common.zhoujinniao.ui.adapter.TaskAdapter;
import com.qixiu.common.zhoujinniao.ui.view.LoadingView;
import com.qixiu.common.zhoujinniao.R;


public class MainFragment extends Fragment implements OnItemClickListener {
	View mView;
	boolean hasInit;
	ImageView set;
	RelativeLayout interCity;

	ArrayList<ImageTextBean> itList = new ArrayList<ImageTextBean>();
	ImgeTextAdapter adapter;
	GridView gridView;
	ArrayList<TaskBean> list = new ArrayList<TaskBean>();
	TaskAdapter taskAdapter;
	ListView listView;
	String total;
	// RelativeLayout rl_layout1;
	TextView earnings, balance, total_earnings;
	// ImageView user_head;
	String invited_num, invited_money, inviting_money;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mView = inflater.inflate(R.layout.fragment_main, container, false);
		hasInit = true;
		initView();
		addListeners();
		return mView;
	}

	private void addListeners() {
		// TODO Auto-generated method stub
		itList.add(new ImageTextBean());
		itList.add(new ImageTextBean());
		itList.add(new ImageTextBean());
		itList.add(new ImageTextBean());

		adapter = new ImgeTextAdapter();
		adapter.setData(itList);
		gridView.setAdapter(adapter);

		// --------------
		list.add(new TaskBean());
		list.add(new TaskBean());
		list.add(new TaskBean());
		list.add(new TaskBean());

		taskAdapter = new TaskAdapter();
		taskAdapter.setData(list);
		listView.setAdapter(taskAdapter);
		listView.setOnItemClickListener(this);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.d("aa", "gridview" + arg2);
				final Intent intent;
				switch (arg2) {
				case 0: // 下载任务
					intent = new Intent(getContext(), DownloadActivity.class);
				//	intent = new Intent(getContext(),DownloadActivity.class);
					startActivity(intent);
					break;

				case 1: // 签到

					intent = new Intent(getContext(), SignInActivity.class);

					startActivity(intent);

					break;

				case 2:

					break;
				case 3:

					intent = new Intent(getContext(), WithdrawOneActivity.class);
					startActivity(intent);

					break;

				}
			}
		});
	}

	private void initView() {

		gridView = (GridView) mView.findViewById(R.id.gridview);
		listView = (ListView) mView.findViewById(R.id.listview);
		// rl_layout1 = (RelativeLayout) mView.findViewById(R.id.rl_layout1);
		earnings = (TextView) mView.findViewById(R.id.earnings);
		balance = (TextView) mView.findViewById(R.id.balance);
		total_earnings = (TextView) mView.findViewById(R.id.total_earnings);

	}

	@Override
	public void onResume() {
		super.onResume();
		if (hasInit)
			loadData();

		// profile();
	}

	public void profile() {

		UserMananger.profile(getContext(), new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub

				ProfileResponse response = (ProfileResponse) pResponse;

				setProfileData(response.o);

			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void setProfileData(ProfileData data) {
		// TODO Auto-generated method stub

		if (!TextUtils.isEmpty(data.today_profit)) {
			total = data.total_profit;
			earnings.setText("¥ " + data.today_profit);
		} else {

			earnings.setText("暂无收益");
		}

		if (!TextUtils.isEmpty(data.credit)) {

			balance.setText(data.credit);
		} else {

			balance.setText("0");
		}

		if (!TextUtils.isEmpty(data.total_profit)) {

			total_earnings.setText(data.total_profit);

		} else {

			total_earnings.setText("0");
		}

	}

	// ----------------------------------------------------

	private void loadData() {

	}

	RelativeLayout loadingLayout;
	LoadingView loadingView;

	public void showLoading() {
		loadingLayout = (RelativeLayout) mView.findViewById(R.id.loading);
		if (loadingView == null) {
			loadingView = new LoadingView(getActivity());
			loadingLayout.addView(loadingView.getView());
		}
		loadingLayout.setVisibility(View.VISIBLE);
	}

	public void hideLoading() {
		if (loadingLayout != null)
			loadingLayout.setVisibility(View.GONE);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intent;

		switch (arg2) {
		case 0:// 收徒

			intent = new Intent(getContext(), InvitationActivity.class);
			intent.putExtra("invited_num", invited_num);
			intent.putExtra("invited_money", invited_money);
			intent.putExtra("inviting_money", inviting_money);
			startActivity(intent);

			break;

		case 1: // 排名

			intent = new Intent(getContext(), RankingActivity.class);
			startActivity(intent);

			break;
		case 2: // 任务明细
			intent = new Intent(getContext(), DetailActivity.class);
			startActivity(intent);
			break;
		case 3:// 新用户手册

			//WebManager.startWebView(getActivity(), "帮助", WebManager.help(), 0);

			//Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
