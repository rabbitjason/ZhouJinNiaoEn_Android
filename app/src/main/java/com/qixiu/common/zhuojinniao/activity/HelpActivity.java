package com.qixiu.common.zhuojinniao.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.R;


public class HelpActivity extends BaseActivity implements OnClickListener {

	WebView web;
	String urlstring, titlestrString;
	RelativeLayout back;
	TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		InitData();
		BindData();
		initWeb();
	}

	private void InitData() {
		// TODO Auto-generated method stub

		back = (RelativeLayout) findViewById(R.id.get_back);
		web = (WebView) findViewById(R.id.web);
		title = (TextView) findViewById(R.id.title);
	}

	private void BindData() {
		// TODO Auto-generated method stub

		back.setOnClickListener(this);

		urlstring = getIntent().getStringExtra("url");
		titlestrString = getIntent().getStringExtra("name");

		title.setText(titlestrString);

		web.loadUrl(urlstring);
	}

	private void initWeb() {
		web.requestFocusFromTouch();
		// this.setWebJs();
		WebSettings webSettings = web.getSettings();

		web.requestFocus();
		web.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webSettings.setAllowFileAccess(true);
		webSettings.setBuiltInZoomControls(true);

		// -------------
		// webView.getSettings().setJavaScriptEnabled(true);

		// webView.getSettings().setBlockNetworkImage(false);
		// -------------
		web.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

		});

		// 设置WebChromeClient
		WebChromeClient mWebChromeClient = new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 100) {
					hideLoading();
				} else {
					showLoading();
				}
				super.onProgressChanged(view, newProgress);
			}
		};

		try {
			web.setWebChromeClient(mWebChromeClient);
			webSettings.setJavaScriptEnabled(true);
			webSettings.setDomStorageEnabled(true);
			webSettings.setAllowFileAccess(true);
			webSettings.setSupportZoom(true);
			webSettings.setBuiltInZoomControls(true);
			webSettings.setUseWideViewPort(true);
			webSettings.setLoadWithOverviewMode(true);

			// ----------------
			webSettings.setBlockNetworkImage(false);//
			webSettings.setUseWideViewPort(false); // 将图片调整到适合webview的大小
			webSettings.setLoadsImagesAutomatically(true); // 支持自动加载图片
			// ------------------------------
			// webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
			webSettings.setRenderPriority(RenderPriority.HIGH);
			// webSettings.setPluginState(PluginState.ON);

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				this.web.setLayerType(View.LAYER_TYPE_HARDWARE, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (web.canGoBack()) {
				web.goBack();// 返回上一页面
				return true;
			} else {
				finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.get_back:

			finish();
			break;

		default:
			break;
		}
	}
}
