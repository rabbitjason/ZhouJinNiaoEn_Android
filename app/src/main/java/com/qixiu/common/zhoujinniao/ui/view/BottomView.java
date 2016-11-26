package com.qixiu.common.zhoujinniao.ui.view;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qixiu.common.zhoujinniao.R;


public class BottomView extends BaseView {

	private RelativeLayout mBottomLayout[];

	private ImageView icon[];

	private TextView text[];

	private OnItemClickListener mItemClickListener;

	public BottomView(Context context) {
		super(context);
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		mItemClickListener = onItemClickListener;
	}

	@Override
	protected int getLayoutID() {
		return R.layout.view_bottom;
	}

	@Override
	protected void initView() {
		mBottomLayout = new RelativeLayout[2];
		mBottomLayout[0] = (RelativeLayout) mView.findViewById(R.id.b1_layout);
		mBottomLayout[1] = (RelativeLayout) mView.findViewById(R.id.b2_layout);

		icon = new ImageView[2];
		icon[0] = (ImageView) mView.findViewById(R.id.icon1);
		icon[1] = (ImageView) mView.findViewById(R.id.icon2);

		text = new TextView[2];
		text[0] = (TextView) mView.findViewById(R.id.text1);
		text[1] = (TextView) mView.findViewById(R.id.text2);

		for (int i = 0; i < mBottomLayout.length; i++) {
			final int p = i;
			mBottomLayout[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onSelect(p);
					if (mItemClickListener != null)
						mItemClickListener.onItemClick(null, null, p, 0);
				}
			});
		}
		onSelect(0);
	}

	public void onSelect(int position) {
		// 选中的文字和图标变颜色
	}

}
