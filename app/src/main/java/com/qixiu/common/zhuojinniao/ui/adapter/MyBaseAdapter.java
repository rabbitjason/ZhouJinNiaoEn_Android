package com.qixiu.common.zhuojinniao.ui.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**s
 * 
 * 作者:蔡名雨<br>
 * 功能:继承于BaseAdapter，简化<br>
 * 创建时间:下午10:34:50<br>
 * 参与者:<br>
 */
public abstract class MyBaseAdapter extends BaseAdapter {

	/** ItemView */
	protected View convertView;

	/** 数据列表 */
	protected ArrayList list;

	/** 整合ViewHolder */
	protected ViewHolder holder;

	/** 列表对应的下标 */
	protected int position;

	// --------------------------
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// -----------
		this.position = position;
		holder = ViewHolder.get(parent.getContext(), convertView, parent,
				getLayoutResID(), position);
		this.convertView = holder.getConvertView();
		findViews();
		dataBindView();
		addListeners();
		return holder.getConvertView();
	}

	/** 布局控件初始化 */
	protected View findViewById(int id) {
		if (holder != null) {
			return holder.getView(id);
		}
		return null;

	}

	// /------------------------

	/** 设置数据 */
	public void setData(ArrayList list) {
		this.list = list;
	}

	/** 获取布局ID */
	protected abstract int getLayoutResID();

	/** 布局初始化 */
	protected abstract void findViews();

	/** 数据绑定视图 */
	protected abstract void dataBindView();

	/** 事件监听设置 */
	protected abstract void addListeners();

}
