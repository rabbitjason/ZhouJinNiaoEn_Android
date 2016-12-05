package com.qixiu.common.zhuojinniao.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qixiu.common.zhuojinniao.data.bean.DetailBean;
import com.qixiu.common.zhuojinniao.data.response.BaseResponse;
import com.qixiu.common.zhuojinniao.data.response.GetTasksResponse;
import com.qixiu.common.zhuojinniao.data.responsedata.GetTasksData;
import com.qixiu.common.zhuojinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhuojinniao.manager.UserMananger;
import com.qixiu.common.zhuojinniao.ui.adapter.DetailAdapter;
import com.qixiu.common.zhuojinniao.R;


public class DetailActivity extends BaseActivity implements OnClickListener {

	ArrayList<DetailBean> list = new ArrayList<DetailBean>();

	DetailAdapter adapter;

	ListView mPullScrollView;

	RelativeLayout get_back;
	int pageNo = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Initdata();
		Binddata();
	}

	private void Binddata() {
		// TODO Auto-generated method stub
		getData(true);
		adapter = new DetailAdapter();
		adapter.setData(list);

		mPullScrollView.setAdapter(adapter);
		// mPullScrollView.getRefreshableView().setOnItemClickListener(this);
		
	}

	private void getData(boolean ShowLoding) {
		// TODO Auto-generated method stub

		if (ShowLoding) {
			showLoading();
		}

		UserMananger.getTasks(this, pageNo, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
			
				hideLoading();
				GetTasksResponse response = (GetTasksResponse) pResponse;

				setData(response.o);

				// Toast.makeText(DetailActivity.this, response.o,
				// Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				hideLoading();
				//mPullScrollView.onRefreshComplete();
				Toast.makeText(DetailActivity.this, "查询失败", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}

	private void setData(GetTasksData data) {
		// TODO Auto-generated method stub

		if (pageNo == 1) {

			list.clear();
		}

		if (data.list != null && data.list.size() > 0) {

			list.addAll(data.list);

			adapter.notifyDataSetChanged();
		} else {
			adapter.notifyDataSetChanged();
		}
	}

	private void Initdata() {
		// TODO Auto-generated method stub

		mPullScrollView =  (ListView) findViewById(R.id.pull_layout);
	
		get_back = (RelativeLayout) findViewById(R.id.get_back);

		get_back.setOnClickListener(this);

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
