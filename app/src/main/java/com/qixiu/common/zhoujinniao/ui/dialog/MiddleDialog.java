package com.qixiu.common.zhoujinniao.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qixiu.common.zhoujinniao.R;


public class MiddleDialog extends Dialog {

	TextView cancel, yes, content;
	Context context;

	public MiddleDialog(Context context) {
		this(context, R.style.MyDialog);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public MiddleDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog_modify_choose);
		initView();
		addListener();
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

	private void initView() {
		// TODO Auto-generated method stub
		cancel = (TextView) findViewById(R.id.dialog_button_cancel);
		yes = (TextView) findViewById(R.id.yes);
		content = (TextView) findViewById(R.id.content);
	}

}