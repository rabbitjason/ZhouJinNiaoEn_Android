package com.qixiu.common.zhuojinniao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.qixiu.common.zhuojinniao.data.response.BaseResponse;
import com.qixiu.common.zhuojinniao.data.response.InvitationResponse;
import com.qixiu.common.zhuojinniao.data.responsedata.UserData;
import com.qixiu.common.zhuojinniao.fragment.MainFragment;
import com.qixiu.common.zhuojinniao.fragment.MineFragment;
import com.qixiu.common.zhuojinniao.main.Config;
import com.qixiu.common.zhuojinniao.manager.BaseHttpManager;
import com.qixiu.common.zhuojinniao.manager.PreferenceManager;
import com.qixiu.common.zhuojinniao.manager.UserMananger;
import com.qixiu.common.zhuojinniao.ui.dialog.MiddleDialog;
import com.qixiu.common.zhuojinniao.util.ScreenUtils;

import com.qixiu.common.zhuojinniao.R;
import com.trialpay.android.Trialpay;
//import com.tapjoy.easyapp.TapjoyEasyApp;

/**
 * 首页显示“最新”和“热门”
 * 
 * @author Darker:
 */
public class MainActivity extends BaseActivity {

	private String TAG = "MainActivity";

	private FragmentTransaction mFragmentTransaction;// 碎片的事物
	private FragmentManager mFragmentManager;// 碎片管理器

	private int currentPostion;

	private MainFragment mainFragment;
	private MineFragment mineFragment;

	private MainFragment mainCatchFragment;
	private MineFragment mineCatchFragment;

    public static UserData data;

	// private RelativeLayout mBottomLayout;
	// private BottomView mBottomView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainactivty);
        data = (UserData) getIntent().getSerializableExtra("data");
		ScreenUtils.initScreen(this);
		initView();
		initFragment();
		showContent();
		getData();

		//TrialPay init
		Trialpay.initApp(this, Config.TRIALPAY_APP_ID, Settings.Secure.getString(this.getContentResolver(),
				Settings.Secure.ANDROID_ID));


	}

	private void initView() {
		// mBottomLayout = (RelativeLayout) findViewById(R.id.bottom_layout);
		// mBottomView = new BottomView(this);
		// mBottomLayout.addView(mBottomView.getView());
		// mBottomView.setOnItemClickListener(new OnItemClickListener() {
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		// long arg3) {
		// currentPostion = arg2;
		// showContent();
		// }
		// });
	}

	// -----------------------------------------

	private void initFragment() {
		this.mainFragment = (MainFragment) Fragment.instantiate(this,
				MainFragment.class.getName(), null);
		this.mineFragment = (MineFragment) Fragment.instantiate(this,
				MineFragment.class.getName(), null);
	}

	private void showContent() {

		if (this.mFragmentManager == null) {
			this.mFragmentManager = this.getSupportFragmentManager();
		}
		this.mFragmentTransaction = this.mFragmentManager.beginTransaction();
		this.getCacheFragement();
		if (currentPostion == 0) {
			this.setCurrentFragment(mainCatchFragment, mainFragment, "tab1");
		} else if (currentPostion == 1) {
			this.setCurrentFragment(mineCatchFragment, mineFragment, "tab2");
		}
		this.mFragmentTransaction.commit();
		this.mFragmentManager.executePendingTransactions();

	}

	private void getCacheFragement() {
		this.mainCatchFragment = (MainFragment) getCacheFragment("tab1");
		this.mineCatchFragment = (MineFragment) getCacheFragment("tab2");

		this.hideFragment(mainCatchFragment);
		this.hideFragment(mineCatchFragment);

	}

	private Fragment getCacheFragment(String tag) {
		return this.mFragmentManager.findFragmentByTag(tag);
	}

	private void hideFragment(Fragment aHideFragment) {
		if (aHideFragment != null) {
			this.mFragmentTransaction.hide(aHideFragment);
		}
	}

	private void setCurrentFragment(Fragment aCache, Fragment aCurrt, String tag) {
		if (aCache == null) {
			this.mFragmentTransaction.add(R.id.realtabcontent, aCurrt, tag);
		} else {
			mFragmentTransaction.show(aCache);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (mainFragment != null)
			mainFragment.onActivityResult(requestCode, resultCode, data);
		if (mineFragment != null)
			mineFragment.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			// if ((System.currentTimeMillis() - exitTime) > 2000) {
			// Toast.makeText(getApplicationContext(), "再按一次退出程序",
			// Toast.LENGTH_SHORT).show();
			// exitTime = System.currentTimeMillis();
			// } else {
			// finish();
			// System.exit(0);
			// }

			final MiddleDialog dialog = new MiddleDialog(MainActivity.this,
					true);
			dialog.show();
			// mPlayer.start();
			dialog.setText("是否确定退出登录？");
			// dialog.setAwardText("5");
			dialog.setYesClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					//AppConnect.getInstance(MainActivity.this).close();
					// mPlayer.stop();
					finish();
					dialog.dismiss();
				}
			});

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void getData() {

		UserMananger.getInvites(this, new BaseHttpManager.BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				InvitationResponse response = (InvitationResponse) pResponse;

				PreferenceManager.savemoney(response.o.invited_money);
				// setData(response.o);
				Log.d("aa", "收徒前:" + PreferenceManager.getmoney());
			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub

			}
		});

	}

}
