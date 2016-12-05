package com.qixiu.common.zhuojinniao.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.activity.MessageDetailsActivity;
import com.qixiu.common.zhuojinniao.data.bean.MessageBean;
import com.qixiu.common.zhuojinniao.R;

public class MessageAdapter extends MyBaseAdapter {

	TextView message, time;

	Context context;

	public ImageView red;

	MessageBean bean;

	public MessageAdapter(Context context) {
		super();
		this.context = context;
	}

	@Override
	protected int getLayoutResID() {
		// TODO Auto-generated method stub
		return R.layout.item_message;
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		message = (TextView) findViewById(R.id.message);
		time = (TextView) findViewById(R.id.time);
		red = (ImageView) findViewById(R.id.red);
	}

	@Override
	protected void dataBindView() {
		// TODO Auto-generated method stub

		bean = (MessageBean) list.get(position);

		message.setText(bean.title);
		time.setText(bean.time);

		if (bean.read.equals("0")) {

			red.setVisibility(View.VISIBLE);
			
		} else if (bean.read.equals("1")) {

			red.setVisibility(View.GONE);
		}
		
		Log.d("aasdas", "read:"+bean.read);

	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

		// message.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		//
		// Intent intent = new Intent(context,
		// MessageDetailsActivity.class);
		// intent.putExtra("title", bean.title);
		// intent.putExtra("info", bean.info);
		// intent.putExtra("time", bean.time);
		// context.startActivity(intent);
		// }
		// });
	}

}
