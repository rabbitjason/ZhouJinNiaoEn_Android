package com.qixiu.common.zhoujinniao.fragment;

/**
 * 应用
 * mFragmentController = new FragmentController(getSupportFragmentManager(), R.id.realtabcontent);
 *mFragmentController.add(HomeFragment.class, "home", null);
 * */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentController {

	private FragmentManager fragmentManager;
	private int resource;

	public static String[] fragmentTags = { "home", "schedule", "mine" };

	private Fragment currentFragment;

	public FragmentController(FragmentManager fragmentManager, int resource) {
		this.resource = resource;
		this.fragmentManager = fragmentManager;
	}

	public void add(Class<? extends Fragment> clazz, String tag, Bundle bundle) {
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		for (String tagName : fragmentTags) {
			Fragment fragment = fragmentManager.findFragmentByTag(tagName);
			if (fragment != null) {
				transaction.hide(fragment);
			}
		}
		Fragment fragment = fragmentManager.findFragmentByTag(tag);
		currentFragment = null;
		if (fragment != null) {
			currentFragment = fragment;
			transaction.show(fragment);
		} else {
			try {
				fragment = clazz.newInstance();
				currentFragment = fragment;
				transaction.add(resource, fragment, tag);
				if (bundle != null) {
					fragment.setArguments(bundle);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		transaction.commitAllowingStateLoss();
	}

	public Fragment getCurrentFragment() {
		return currentFragment;
	}

}
