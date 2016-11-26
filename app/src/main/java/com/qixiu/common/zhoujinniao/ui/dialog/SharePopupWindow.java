package com.qixiu.common.zhoujinniao.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.qixiu.common.zhoujinniao.R;


public class SharePopupWindow extends PopupWindow {

	private Button btn_cancel;
	private View mMenuView;
	private ImageView weibo, qq, weixin, friends;

	public SharePopupWindow(Activity context, final OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.dialog_share, null);

		weibo = (ImageView) mMenuView.findViewById(R.id.weibo);
		qq = (ImageView) mMenuView.findViewById(R.id.qq);
		weixin = (ImageView) mMenuView.findViewById(R.id.weixin);
		friends = (ImageView) mMenuView.findViewById(R.id.friends);

		btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);
		// ȡ��ť
		btn_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ��ٵ�����
				dismiss();
			}
		});
		// ���ð�ť����
		weibo.setOnClickListener(itemsOnClick);
		qq.setOnClickListener(itemsOnClick);
		weixin.setOnClickListener(itemsOnClick);
		friends.setOnClickListener(itemsOnClick);

//		weibo.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				dismiss();
//				if (itemsOnClick != null)
//					itemsOnClick.onClick(null);
//			}
//		});
//		qq.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				dismiss();
//				if (itemsOnClick != null)
//					itemsOnClick.onClick(null);
//			}
//		});
//		weixin.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				dismiss();
//				if (itemsOnClick != null)
//					itemsOnClick.onClick(null);
//			}
//		});
//		friends.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				dismiss();
//				if (itemsOnClick != null)
//					itemsOnClick.onClick(null);
//			}
//		});

		// ����SelectPicPopupWindow��View
		this.setContentView(mMenuView);
		// ����SelectPicPopupWindow��������Ŀ�
		this.setWidth(LayoutParams.MATCH_PARENT);
		// ����SelectPicPopupWindow��������ĸ�
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// ����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);
		// ����SelectPicPopupWindow�������嶯��Ч��
		this.setAnimationStyle(R.style.dialogBottomStyle);
		// ʵ��һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(0xB0000000);
		// ����SelectPicPopupWindow��������ı���
		this.setBackgroundDrawable(dw);
		// mMenuView���OnTouchListener�����жϻ�ȡ����λ�������ѡ�����������ٵ�����
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

	}

	/** �ӵײ����� */
	public void showAtLocation(View v) {
		this.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
	}

}
