package com.qixiu.common.zhuojinniao.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.R;


public class ImgeTextAdapter extends MyBaseAdapter {

	String texts[] = { "下载任务", "签到任务", "好评赢金币", "提现" };

	ImageView img;
	TextView text, number;
	RelativeLayout layout;
	RelativeLayout linear;

	@Override
	protected int getLayoutResID() {
		// TODO Auto-generated method stub
		return R.layout.item_imegtext;
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		img = (ImageView) findViewById(R.id.img1);
		text = (TextView) findViewById(R.id.text);
		layout = (RelativeLayout) findViewById(R.id.layout);
		linear = (RelativeLayout) findViewById(R.id.line);
		number = (TextView) findViewById(R.id.number);
	}

	@Override
	protected void dataBindView() {
		// TODO Auto-generated method stub
		if (position == list.size() - 1) {

			// img.setVisibility(View.INVISIBLE);
			// text.setVisibility(View.INVISIBLE);
			// number.setVisibility(View.GONE);
			img.setImageResource(R.drawable.icon_gold);
			text.setText(texts[3]);
			// number.setText("99");
			number.setVisibility(View.GONE);
			img.setImageResource(R.drawable.icon_moneys);

		} else {

			layout.setVisibility(View.GONE);
			linear.setVisibility(View.VISIBLE);

			if (position == 0) {

				img.setImageResource(R.drawable.icon_task);
				text.setText(texts[0]);
				number.setText("15");
				img.setVisibility(View.VISIBLE);
				number.setVisibility(View.GONE);
			} else if (position == 1) {

				img.setImageResource(R.drawable.icon_sign);
				text.setText(texts[1]);
				number.setText("99");
				number.setVisibility(View.GONE);
				img.setVisibility(View.VISIBLE);
			} else if (position == 2) {

				img.setImageResource(R.drawable.icon_like);
				text.setText(texts[2]);

				// number.setText("99");
				number.setVisibility(View.GONE);
				img.setVisibility(View.VISIBLE);
			} else if (position == 3) {

				img.setImageResource(R.drawable.icon_gold);
				text.setText(texts[3]);
				// number.setText("99");
				number.setVisibility(View.GONE);
				img.setImageResource(R.drawable.icon_moneys);

			}

		}
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

}
