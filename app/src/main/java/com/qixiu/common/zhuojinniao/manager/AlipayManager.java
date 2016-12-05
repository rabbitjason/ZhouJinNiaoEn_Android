package com.qixiu.common.zhuojinniao.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.qixiu.common.zhuojinniao.data.bean.PayResult;

public class AlipayManager {

	public interface PayResultListener {
		public void onSuccess(String result);

		public void onFail(String result);
	}

	Activity activity;
	PayResultListener mListener;
	private static final int SDK_PAY_FLAG = 1;

	public AlipayManager(Activity activity, PayResultListener pListener) {
		this.activity = activity;
		mListener = pListener;
	}

	public void pay(final String payInfo) {
		Log.i("test", "alipay:" + payInfo);
		// info是签名过的
		Runnable payRunnable = new Runnable() {
			@Override
			public void run() {
				PayTask alipay = new PayTask(activity);
				String result = alipay.pay(payInfo, true);
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);
				/**
				 * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
				 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
				 * docType=1) 建议商户依赖异步通知
				 */

				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(activity, "支付成功", Toast.LENGTH_SHORT).show();
					mListener.onSuccess(resultInfo);
				} else {
					mListener.onFail(resultInfo);
					// 判断resultStatus 为非"9000"则代表可能支付失败
					// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(activity, "支付结果确认中", Toast.LENGTH_SHORT)
								.show();
					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						// Toast.makeText(activity,
						// "支付失败" + resultInfo + "|" + resultStatus,
						// Toast.LENGTH_SHORT).show();
					}
				}
				break;
			}
			default:
				break;
			}
		};
	};
}
