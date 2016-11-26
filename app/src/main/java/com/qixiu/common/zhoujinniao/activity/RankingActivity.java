package com.qixiu.common.zhoujinniao.activity;

import java.util.ArrayList;

import com.qixiu.common.zhoujinniao.data.bean.RankingBean;
import com.qixiu.common.zhoujinniao.data.response.BaseResponse;
import com.qixiu.common.zhoujinniao.data.response.GetRanksResponse;
import com.qixiu.common.zhoujinniao.data.responsedata.GetRanksData;
import com.qixiu.common.zhoujinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhoujinniao.manager.UserMananger;
import com.qixiu.common.zhoujinniao.ui.adapter.RankingAdapter;
import com.qixiu.common.zhoujinniao.R;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RankingActivity extends BaseActivity implements OnClickListener {

	ArrayList<RankingBean> list = new ArrayList<RankingBean>();

	ListView listview;

	RankingAdapter adapter;

	RelativeLayout get_back;

	TextView userrank, person_iphone;
	String str = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ranking);

		InitData();
		BindData();

	}

	private void InitData() {
		// TODO Auto-generated method stub

		listview = (ListView) findViewById(R.id.listview);
		get_back = (RelativeLayout) findViewById(R.id.get_back);
		userrank = (TextView) findViewById(R.id.userrank);
		person_iphone = (TextView) findViewById(R.id.person_iphone);

	}

	private void BindData() {
		// TODO Auto-generated method stub

		// list.add(new RankingBean());
		// list.add(new RankingBean());
		// list.add(new RankingBean());
		// list.add(new RankingBean());
		// list.add(new RankingBean());

		adapter = new RankingAdapter();
		// adapter.getList(list);
		adapter.setData(list);

		listview.setAdapter(adapter);

		get_back.setOnClickListener(this);

		//getData();

	}

	public void getData() {

		UserMananger.getRanks(this, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				GetRanksResponse response = (GetRanksResponse) pResponse;

				userrank.setText("您排名在" + response.o.myrank + "," + "还需要努力哦");
				for (int i = 0; i < response.o.toplist.size(); i++) {

					str = str + response.o.toplist.get(i) + "        ";

				}
				Log.d("aa", str);
				person_iphone.setText(str);

				// Log.d("aa", response.myrank+"___");

				setData(response.o);

			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void setData(GetRanksData data) {
		// TODO Auto-generated method stub

		if (data.ranklist != null && data.ranklist.size() > 0) {
			list.addAll(data.ranklist);
			adapter.notifyDataSetChanged();
		} else {
			adapter.notifyDataSetChanged();
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.get_back:

			finish();

			break;

		default:
			break;
		}

	}

}
