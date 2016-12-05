package com.qixiu.common.zhuojinniao.ui.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qixiu.common.zhuojinniao.R;


public class SendCodeView extends BaseView {

	EditText phonetext, codetext;
	Button mSendVerifyCode;
	Timer timer = new Timer();

	public static String codeString;

	public SendCodeView(Context context) {
		super(context);
	}

	@Override
	protected int getLayoutID() {
		return R.layout.view_sendcode;
	}

	@Override
	protected void initView() {
		phonetext = (EditText) mView.findViewById(R.id.codetext);
		codetext = (EditText) mView.findViewById(R.id.code_text);
		mSendVerifyCode = (Button) mView.findViewById(R.id.send_verifCode);

		initListener();
	}

	/**
	 * 控件监听初始化
	 */
	private void initListener() {
		// TODO Auto-generated method stub

		// -----------
		// 获取验证码的按钮的监听方法
		mSendVerifyCode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v == mSendVerifyCode) {
					if (isPhone(getPhone())) {

						timer.start();

					}

				}
			}
		});

	}

	public String getPhone() {
		return phonetext.getText().toString().replaceAll(" ", "");
	}

	public String getCode() {
		return codetext.getText().toString().replaceAll(" ", "");
	}

	public void clearTimer() {
		if (timer != null)
			timer.cancel();
	}

	private class Timer extends CountDownTimer {

		public Timer() {
			super(60 * 1000, 1000);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			Log.i("test", "??");
			mSendVerifyCode.setText(millisUntilFinished / 1000 + "秒后重发");
		}

		@Override
		public void onFinish() {
			mSendVerifyCode.setText(R.string.send_verifcode);
			mSendVerifyCode.setTextColor(mContext.getResources().getColor(
					R.color.text_has_click_color));
			mSendVerifyCode.setEnabled(true);
		}

	}

	/** 判断手机号码输入是否正确 */
	private boolean isPhone(String phoneString) {
		boolean is = true;
		phoneString = phoneString.replaceAll(" ", "");
		if (phoneString.length() != 11) {
			Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
			is = false;
		}

		return is;
	}

}
