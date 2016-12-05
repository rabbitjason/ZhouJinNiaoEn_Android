package com.qixiu.common.zhuojinniao.activity;

import java.util.List;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.qixiu.common.zhuojinniao.data.response.BaseResponse;
import com.qixiu.common.zhuojinniao.data.response.LoginResponse;
import com.qixiu.common.zhuojinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhuojinniao.manager.PreferenceManager;
import com.qixiu.common.zhuojinniao.manager.UserMananger;
import com.qixiu.common.zhuojinniao.R;


public class WelcomeActivity extends BaseActivity {

	Thread thread;
	private final String mPageName = "WelcomeActivity";

	/**
	 * 小tips:这里的int数值不能太大，否则不会弹出请求权限提示，测试的时候,改到1000就不会弹出请求了
	 */
	private final static int READ_PHONE_STATE_CODE = 101;

	private final static int WRITE_EXTERNAL_STORAGE_CODE = 102;

	/**
	 * 有米 Android SDK 所需要向用户申请的权限列表
	 */
	private static PermissionModel[] models = new PermissionModel[] {
			new PermissionModel(Manifest.permission.READ_PHONE_STATE,
					"我们需要读取手机信息的权限来标识您的身份", READ_PHONE_STATE_CODE),
			new PermissionModel(Manifest.permission.WRITE_EXTERNAL_STORAGE,
					"我们需要您允许我们读写你的存储卡，以方便我们临时保存一些数据",
					WRITE_EXTERNAL_STORAGE_CODE), };

	// -------------------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.acticity_welcome);

		/**
		 * 有米平台
		 */
		// userid 不能为空 或者 空串,否则设置无效, 字符串长度必须要小于50
//		OffersManager.getInstance(this).setCustomUserId(
//				Uri.encode(
//						Secure.getString(this.getContentResolver(), "utf-8"),
//						Secure.ANDROID_ID));
		// 有米Android SDK v4.10之后的sdk还需要配置下面代码，以告诉sdk使用了服务器回调
//		OffersManager.getInstance(this).setUsingServerCallBack(true);
		// 初始化接口，应用启动的时候调用(appId, appSecret, isTestModel, isEnableYoumiLog)
//		AdManager.getInstance(this).init(
//				Uri.encode("806b163200481839", "utf-8"),
//				Uri.encode("65cd6b1b3b147b94", "utf-8"), true, true);
//		OffersManager.getInstance(this).onAppLaunch();

		// 点财平台


		// 贝多

		/**
		 * 友盟
		 */


		// ----------------------------------------
		if (Build.VERSION.SDK_INT < 17) {

			openDemo();
			return;
		}
		checkPermissions();
		enter();

	}

	private void enter() {
		// TODO Auto-generated method stub

		if (PreferenceManager.getFirst() == 0) {
			// timer();

			Intent intent = new Intent(this, GuideActivity.class);
			startActivity(intent);

			finish();
			// login();
		} else {
			if (TextUtils.isEmpty(PreferenceManager.getUserId())) {

				login();
			} else {
				startActivity(new Intent(this, MainActivity.class));
				finish();
			}

		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	public void login() {

		UserMananger.login(this, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				final Intent intent;

				LoginResponse response = (LoginResponse) pResponse;

				intent = new Intent(WelcomeActivity.this, MainActivity.class);

				// intent.putExtra("test", response.o);

				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						startActivity(intent);
					}
				}, 1000);
			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				Toast.makeText(WelcomeActivity.this, "登录失败", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}

	public void activityfinish() {

		finish();
	}

	// -----------------------------------
	private void openDemo() {
		Log.d("aa", "22222222222222222222222");
		startActivity(new Intent(this, MainActivity.class));
		this.finish();
	}

	/**
	 * 这里我们演示如何在Android 6.0+上运行时申请权限
	 * 
	 * @param models
	 */
	private void checkPermissions() {
		try {

			for (PermissionModel model : models) {
				if (PackageManager.PERMISSION_GRANTED != ContextCompat
						.checkSelfPermission(this, model.permission)) {
					ActivityCompat.requestPermissions(this,
							new String[] { model.permission },
							model.requestCode);
					return;
				}
			}
			Log.d("aa", "111111111111111111111111111111");
			// 到这里就表示有米所有需要的权限已经通过申请，权限已经申请就打开demo
			openDemo();
		} catch (Throwable e) {
			Log.d("YoumiSdk", "", e);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
			String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {

		case READ_PHONE_STATE_CODE:
		case WRITE_EXTERNAL_STORAGE_CODE:
			// 如果用户不允许，我们视情况发起二次请求或者引导用户到应用页面手动打开
			if (PackageManager.PERMISSION_GRANTED != grantResults[0]) {

				// 二次请求，表现为：以前请求过这个权限，但是用户拒接了
				// 在二次请求的时候，会有一个“不再提示的”checkbox
				// 因此这里需要给用户解释一下我们为什么需要这个权限，否则用户可能会永久不在激活这个申请
				// 方便用户理解我们为什么需要这个权限
				if (ActivityCompat.shouldShowRequestPermissionRationale(this,
						permissions[0])) {
					new AlertDialog.Builder(this)
							.setTitle("权限申请")
							.setMessage(findPermissionExplain(permissions[0]))
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											checkPermissions();
										}
									}).show();
				}
				// 到这里就表示已经是第3+次请求，而且此时用户已经永久拒绝了，这个时候，我们引导用户到应用权限页面，让用户自己手动打开
				else {
					Toast.makeText(this, "部分权限被拒绝获取，将会会影响后续功能的使用，建议重新打开",
							Toast.LENGTH_LONG).show();
					openAppPermissionSetting(123456789);
				}
				return;
			}

			// 到这里就表示用户允许了本次请求，我们继续检查是否还有待申请的权限没有申请
			if (isAllRequestedPermissionGranted()) {
				openDemo();
			} else {
				checkPermissions();
			}
			break;
		}
	}

	private String findPermissionExplain(String permission) {
		if (models != null) {
			for (PermissionModel model : models) {
				if (model != null && model.permission != null
						&& model.permission.equals(permission)) {
					return model.explain;
				}
			}
		}
		return null;
	}

	private boolean isAllRequestedPermissionGranted() {
		for (final PermissionModel model : models) {
			if (PackageManager.PERMISSION_GRANTED != ContextCompat
					.checkSelfPermission(this, model.permission)) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {

		case 123456789:
			if (isAllRequestedPermissionGranted()) {
				openDemo();
			} else {
				Toast.makeText(this, "部分权限被拒绝获取，退出", Toast.LENGTH_LONG).show();
				this.finish();
			}
			break;
		default:
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	private boolean openAppPermissionSetting(int requestCode) {
		try {
			Intent intent = new Intent(
					Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
					Uri.parse("package:" + this.getPackageName()));
			intent.addCategory(Intent.CATEGORY_DEFAULT);

			// Android L 之后Activity的启动模式发生了一些变化
			// 如果用了下面的 Intent.FLAG_ACTIVITY_NEW_TASK ，并且是 startActivityForResult
			// 那么会在打开新的activity的时候就会立即回调 onActivityResult
			// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			startActivityForResult(intent, requestCode);
			return true;
		} catch (Throwable e) {
			Log.e("YoumiSdk", "", e);
		}
		return false;
	}

	public static class PermissionModel {

		/**
		 * 请求的权限
		 */
		public String permission;

		/**
		 * 解析为什么请求这个权限
		 */
		public String explain;

		/**
		 * 请求代码
		 */
		public int requestCode;

		public PermissionModel(String permission, String explain,
				int requestCode) {
			this.permission = permission;
			this.explain = explain;
			this.requestCode = requestCode;
		}
	}

	public class MyViewPagerAdapter extends PagerAdapter {

		private List<View> mListViews;

		public MyViewPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;// 构造方法，参数是我们的页卡，这样比较方便。
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return false;
		}

	}
}

// -----------------------------------

