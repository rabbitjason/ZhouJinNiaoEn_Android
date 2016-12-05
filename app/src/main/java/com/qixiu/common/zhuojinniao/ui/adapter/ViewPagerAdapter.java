package com.qixiu.common.zhuojinniao.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.qixiu.common.zhuojinniao.activity.MainActivity;
import com.qixiu.common.zhuojinniao.activity.WelcomeActivity;
import com.qixiu.common.zhuojinniao.data.response.BaseResponse;
import com.qixiu.common.zhuojinniao.data.response.LoginResponse;
import com.qixiu.common.zhuojinniao.manager.PreferenceManager;
import com.qixiu.common.zhuojinniao.manager.UserMananger;
import com.qixiu.common.zhuojinniao.manager.BaseHttpManager.BaseCallListener;

public class ViewPagerAdapter extends PagerAdapter {

	private Context mContext;
	// 界面列表
	private List<View> views;

	public ViewPagerAdapter(Context context, List<View> views) {
		this.mContext = context;
		this.views = views;
	}

	// 销毁arg1位置的界面
	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView(views.get(arg1));
	}

	@Override
	public void finishUpdate(View arg0) {

	}

	@Override
	public int getCount() {
		if (views != null) {
			return views.size();
		}
		return 0;
	}

	@Override
	public Object instantiateItem(View container, int position) {
		View view = views.get(position);
		((ViewPager) container).addView(view, 0);
		if (position == views.size() - 1) {
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					//((WelcomeActivity) mContext).login();

//					 Intent intent = new Intent(mContext,
//					 MainActivity.class); 
//					 PreferenceManager.saveFirst(1);
//					 mContext.startActivity(intent); 
					login();
					((Activity) mContext).finish();
					// LiverApplication.hasStarted();// 不再启动引导页
				}
			});
		}
		return views.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) { 

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {

	}
	
	public void login() {

		UserMananger.login(mContext, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				final Intent intent;

				LoginResponse response = (LoginResponse) pResponse;

				intent = new Intent(mContext, MainActivity.class);

				// intent.putExtra("test", response.o);

				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						mContext.startActivity(intent);
					}
				}, 1000);
			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}

}
