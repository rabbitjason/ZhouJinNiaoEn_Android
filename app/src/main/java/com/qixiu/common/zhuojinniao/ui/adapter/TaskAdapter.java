package com.qixiu.common.zhuojinniao.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.R;


public class TaskAdapter extends MyBaseAdapter {

	ImageView task_img, task_back;
	TextView task_text, task_text_content;

	@Override
	protected int getLayoutResID() {
		// TODO Auto-generated method stub
		return R.layout.item_task;
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		task_img = (ImageView) findViewById(R.id.task_img);
		task_text = (TextView) findViewById(R.id.task_text);
		task_text_content = (TextView) findViewById(R.id.task_text_content);
		task_back = (ImageView) findViewById(R.id.task_back);
	}

	@Override
	protected void dataBindView() {
		// TODO Auto-generated method stub
		if (position == 0) {

			task_img.setImageResource(R.drawable.icon_recruit);
			task_text.setText("收徒");
			task_text_content.setText("徒弟完成任务你都可以获得10%的收益");

		} else if (position == 1) {

			task_img.setImageResource(R.drawable.icon_ranking);
			task_text.setText("收入排名");
			task_text_content.setText("隔壁老王收入过万了,你还在等什么,还不行动");

		}
		if (position == 2) {

			task_img.setImageResource(R.drawable.icon_task_detail);
			task_text.setText("任务明细");
			task_text_content.setText("查看以往完成的任务");

		} else if (position == 3) {

			task_img.setImageResource(R.drawable.icon_manual);
			task_text.setText("新用户手册");
			task_text_content.setText("如何获取更多的收益,多久能到账");

		}
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

}
