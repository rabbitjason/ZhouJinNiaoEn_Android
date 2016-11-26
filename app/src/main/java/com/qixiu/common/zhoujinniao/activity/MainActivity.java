package com.qixiu.common.zhoujinniao.activity;

import java.util.Hashtable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.qixiu.common.zhoujinniao.fragment.MainFragment;
import com.qixiu.common.zhoujinniao.fragment.MineFragment;
import com.qixiu.common.zhoujinniao.main.App;
import com.qixiu.common.zhoujinniao.ui.dialog.MiddleDialog;
import com.qixiu.common.zhoujinniao.util.ScreenUtils;

import com.qixiu.common.zhoujinniao.R;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJEarnedCurrencyListener;
import com.tapjoy.TJError;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementVideoListener;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyLog;
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

	// private RelativeLayout mBottomLayout;
	// private BottomView mBottomView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainactivty);
		ScreenUtils.initScreen(this);
		initView();
		initFragment();
		showContent();

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

			final MiddleDialog dialog = new MiddleDialog(MainActivity.this);
			dialog.show();
			dialog.setText("是否确定退出登录？");
			dialog.setYesClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub

					finish();
					dialog.dismiss();
				}
			});

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
