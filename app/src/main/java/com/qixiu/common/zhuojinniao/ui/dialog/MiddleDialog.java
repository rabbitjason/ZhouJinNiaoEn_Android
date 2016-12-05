package com.qixiu.common.zhuojinniao.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.R;


public class MiddleDialog extends Dialog {

	TextView cancel, yes, content, number;
	RelativeLayout rlt;
	ImageView img;
	Context context;
	boolean is, show;
	Thread thread;
	public MediaPlayer mPlayer;// mediaPlayer对象

	public MiddleDialog(Context context, boolean show) {
		this(context, R.style.MyDialog);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.is = show;
	}

	public MiddleDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		show = is;
		if (show) {
			this.setContentView(R.layout.dialog_modify_choose);
			Log.d("aa", is + "______" + show);
		} else {
			this.setContentView(R.layout.show_award_dialog);
			Log.d("aa", is + "？___" + show);
		}

		initView();
		if (show) {
			addListener();
		}

	}

	private void addListener() {
		// TODO Auto-generated method stub
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});


	}

	/** 确定按钮 */
	public void setYesClickListener(View.OnClickListener l) {
		yes.setOnClickListener(l);
	}

	/** 取消按钮 */
	public void setNoClickListener(View.OnClickListener l) {
		cancel.setOnClickListener(l);
	}

	/** 设置弹框提示内容 */
	public void setText(String str) {
		content.setText(str);
	}

	/** 奖励框 */
	public void setNoShowClickListener(View.OnClickListener l) {

		rlt.setOnClickListener(l);
	}

	/** 设置奖励框 */
	public void setAwardText(String str) {

		number.setText("    ×  " + str);
	}

	/** 显示不同的弹框 */
	public void showSign(boolean show) {

		if (show) {

			rlt.setVisibility(View.VISIBLE);
			img.setVisibility(View.GONE);
		} else {

			rlt.setVisibility(View.GONE);
			img.setVisibility(View.VISIBLE);
		}

	}

	/**签到弹框*/
	public void Sign(View.OnClickListener l) {

		img.setOnClickListener(l);
	}

	private void initView() {
		// TODO Auto-generated method stub
		if (show) {
			cancel = (TextView) findViewById(R.id.dialog_button_cancel);
			yes = (TextView) findViewById(R.id.yes);
			content = (TextView) findViewById(R.id.content);
		} else {
			rlt = (RelativeLayout) findViewById(R.id.rlt);
			number = (TextView) findViewById(R.id.number);
			img = (ImageView) findViewById(R.id.img);
		}

	}
}