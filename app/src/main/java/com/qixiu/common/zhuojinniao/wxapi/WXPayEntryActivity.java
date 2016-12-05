package com.qixiu.common.zhuojinniao.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.qixiu.common.zhuojinniao.main.App;
import com.qixiu.common.zhuojinniao.manager.AlipayManager.PayResultListener;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	public static PayResultListener pListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.wxApi.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		App.wxApi.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		// 0 success,-1 error,-2 cancel
		if (resp.errCode == 0) {
			if (pListener != null)
				pListener.onSuccess("");
		} else {
			if (pListener != null)
				pListener.onFail("");
		}
		finish();
	}
}