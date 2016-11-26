package com.qixiu.common.zhoujinniao.ui.adapter;

import android.widget.TextView;

import com.qixiu.common.zhoujinniao.data.bean.DetailBean;
import com.qixiu.common.zhoujinniao.R;


public class DetailAdapter extends MyBaseAdapter {

	TextView task_text, date, gold;

	@Override
	protected int getLayoutResID() {
		// TODO Auto-generated method stub
		return R.layout.item_detail;
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		task_text = (TextView) findViewById(R.id.task_text);
		date = (TextView) findViewById(R.id.date);
		gold = (TextView) findViewById(R.id.gold);
	}

	@Override
	protected void dataBindView() {
		// TODO Auto-generated method stub
		DetailBean bean = (DetailBean) list.get(position);

		task_text.setText(bean.task_name);
		date.setText(bean.complete_time);
		gold.setText(bean.task_gold);

	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

}
