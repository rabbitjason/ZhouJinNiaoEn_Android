package com.qixiu.common.zhuojinniao.lib.circleview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.qixiu.common.zhuojinniao.data.bean.ADData;
import com.qixiu.common.zhuojinniao.lib.circleview.CycleViewPager.ImageCycleViewListener;
import com.qixiu.common.zhuojinniao.main.Config;
import com.qixiu.common.zhuojinniao.ui.view.BaseView;

import com.qixiu.common.zhuojinniao.R;

public class AdView extends BaseView {

	private List<ImageView> views = new ArrayList<ImageView>();
	private List<ADInfo> infos = new ArrayList<ADInfo>();
	private com.qixiu.common.zhuojinniao.lib.circleview.CycleViewPager cycleViewPager;

	public AdView(Context context) {
		super(context);
	}

	@Override
	protected int getLayoutID() {
		return R.layout.view_adview_layout;
	}

	@Override
	protected void initView() {
		cycleViewPager = (com.qixiu.common.zhuojinniao.lib.circleview.CycleViewPager) (((Activity) mContext)
				.getFragmentManager())
				.findFragmentById(R.id.fragment_cycle_viewpager_content);
	}

	public void setData(ArrayList<ADData> datas,
			ImageCycleViewListener pListener) {
		views.clear();
		infos.clear();
		for (int i = 0; i < datas.size(); i++) {
			ADInfo info = new ADInfo();
			info.setUrl(Config.hostString+datas.get(i).adimg);
			info.setContent("");
			infos.add(info);
		}
		views.add(ViewFactory.getImageView(mContext, infos
				.get(infos.size() - 1).getUrl()));
		for (int i = 0; i < infos.size(); i++) {
			views.add(ViewFactory.getImageView(mContext, infos.get(i).getUrl()));
		}
		views.add(ViewFactory.getImageView(mContext, infos.get(0).getUrl()));
		cycleViewPager.setCycle(true);
		cycleViewPager.setData(views, infos, pListener);
		cycleViewPager.setWheel(true);
		cycleViewPager.setTime(2000);
		cycleViewPager.setIndicatorCenter();
	}
}
