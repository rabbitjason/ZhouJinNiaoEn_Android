package com.qixiu.common.zhuojinniao.ui.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.data.bean.DownloadBean;
import com.qixiu.common.zhuojinniao.R;


public class DownloadAdapter extends MyBaseAdapter {

	ImageView img;
	TextView text, text_task;
	Context context;

	public void getDownload(Context context) {
		this.context = context;
	}

	public void setImg(ImageView img) {
		this.img = img;
	}

	@Override
	protected int getLayoutResID() {
		// TODO Auto-generated method stub
		return R.layout.item_download;
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub

		img = (ImageView) findViewById(R.id.imgLogo);
		text = (TextView) findViewById(R.id.text);
		text_task = (TextView) findViewById(R.id.text_task);

	}

	@Override
	protected void dataBindView() {
		// TODO Auto-generated method stub

		DownloadBean item = (DownloadBean) getItem(position);
		img.setImageResource(item.image_id);
		text.setText(item.wall_name);
		text_task.setText(item.description);

//		if (position == 0) {
//
//			 img.setImageResource(R.drawable.icon_img1);
//			 text.setText("果盟");
//
//		} else if (position == 1) {
//			img.setImageResource(R.drawable.icon_img3);
//			text.setText("多盟");
//
//		} else if (position == 2) {
//
//			img.setImageResource(R.drawable.icon_img4);
//			text.setText("有米");
//
//		} else if (position == 3) {
//			img.setImageResource(R.drawable.icon_img5);
//			text.setText("点入");
//
//		} else if (position == 4) {
//			img.setImageResource(R.drawable.icon_img6);
//			text.setText("中亿");
//
//		} else if (position == 5) {
//			img.setImageResource(R.drawable.icon_img7);
//			text.setText("点财");
//
//		} else if (position == 6) {
//
//			img.setImageResource(R.drawable.icon_img9);
//			text.setText("万普");
//
//		}

	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	class MyClickText extends ClickableSpan {
		private Context context;
		String userid;

		public MyClickText(Context context) {
			this.context = context;
			// this.userid = str;
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			super.updateDrawState(ds);
			// 设置文本的颜色
			ds.setColor(0xFFFF5019);
			// 超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
			ds.setUnderlineText(false);
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

		}
	}

}

