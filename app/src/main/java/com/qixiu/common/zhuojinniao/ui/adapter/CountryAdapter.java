package com.qixiu.common.zhuojinniao.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.qixiu.common.zhuojinniao.data.bean.JavaBean;
import com.qixiu.common.zhuojinniao.manager.PreferenceManager;
import com.qixiu.common.zhuojinniao.R;


public class CountryAdapter extends MyBaseAdapter {

	TextView countryname;
	ImageView countryimg;

	@Override
	protected int getLayoutResID() {
		// TODO Auto-generated method stub
		return R.layout.item_country;
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub
		countryimg = (ImageView) findViewById(R.id.countryimg);
		countryname = (TextView) findViewById(R.id.countryname);

	}

	@Override
	protected void dataBindView() {
		// TODO Auto-generated method stub

		JavaBean bean = (JavaBean) list.get(position);
		countryname.setText(bean.countryname + "");
		countryimg.setImageResource(bean.countryimg);
		if (bean.is) {
			
			convertView.setBackgroundColor(0xFFFF4200);
			
			//PreferenceManager.savename(bean.countryname + "");
			//Log.d("aa", PreferenceManager.getname()+"__name");
			PreferenceManager.saveimg(String.valueOf(position));
		} else {
			convertView.setBackgroundColor(0xFFFFFFFF);
		}

	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

}
