package com.qixiu.common.zhoujinniao.ui.adapter;

import java.util.Calendar;

import android.content.Context;
import android.renderscript.Element;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qixiu.common.zhoujinniao.activity.SignInActivity;
import com.qixiu.common.zhoujinniao.manager.PreferenceManager;
import com.qixiu.common.zhoujinniao.R;

public class SignInAdapter extends MyBaseAdapter {

	RelativeLayout rlt, rlt1;
	ImageView sign_in;
	TextView text_num, line;
	Context context;
	public int sign;

	// private OnClickListener l;
	//
	// public interface Selecor {
	//
	// void aaa(OnClickListener l, int position);
	// }
	//
	// private Selecor aa;
	//
	// public void abc(Selecor aa) {
	// this.aa = aa;
	// }

	public void getContext(Context context) {
		this.context = context;
	}

	@Override
	protected int getLayoutResID() {
		// TODO Auto-generated method stub
		return R.layout.item_sign_in;
	}

	@Override
	protected void findViews() {
		// TODO Auto-generated method stub

		rlt = (RelativeLayout) findViewById(R.id.rlt);
		// rlt1 = (RelativeLayout) findViewById(R.id.rlt1);
		sign_in = (ImageView) findViewById(R.id.sign_in);
		text_num = (TextView) findViewById(R.id.text_num);
		line = (TextView) findViewById(R.id.line);

	}

	@Override
	protected void dataBindView() {
		// TODO Auto-generated method stub

		if (position < list.size()) {

			if (((position + 1) % 7 == 0 && position != 0)
					|| position == list.size() - 1) {

				line.setVisibility(View.GONE);
			} else {

				line.setVisibility(View.VISIBLE);

			}

			text_num.setText(position + 1 + "");

			position++;
		}
		//
		// if (SignInActivity.sign != null
		// && !SignInActivity.sign.equals("已签到")) {
		//
		// sign = Integer.parseInt(SignInActivity.sign);
		//
		// Log.d("aa", sign + "_______________");
		//
		// if (position < sign) {
		//
		// sign_in.setImageResource(R.drawable.icon_yes_sign_in);
		//
		// } else {
		// sign_in.setImageResource(R.drawable.icon_no_sign_id);
		// }
		// } else {
		//
		// if (position < Integer.parseInt(PreferenceManager.getSign())) {
		//
		// sign_in.setImageResource(R.drawable.icon_yes_sign_in);
		// }
		// }
		// text_num.setText(position + 1 + "");
		// sign_in.setOnClickListener(l);
		// // aa.aaa(l, position);
		// position++;
		// }

	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

}
