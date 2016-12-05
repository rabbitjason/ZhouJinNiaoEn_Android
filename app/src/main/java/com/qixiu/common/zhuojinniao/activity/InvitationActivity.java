package com.qixiu.common.zhuojinniao.activity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.qixiu.common.zhuojinniao.data.response.BaseResponse;
import com.qixiu.common.zhuojinniao.data.response.InvitationResponse;
import com.qixiu.common.zhuojinniao.data.response.OtherResponse;
import com.qixiu.common.zhuojinniao.data.response.ProfileResponse;
import com.qixiu.common.zhuojinniao.data.responsedata.InvitationData;
import com.qixiu.common.zhuojinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhuojinniao.manager.PreferenceManager;
import com.qixiu.common.zhuojinniao.manager.UserMananger;
import com.qixiu.common.zhuojinniao.R;
import com.qixiu.common.zhuojinniao.ui.dialog.MiddleDialog;

import java.io.IOException;


public class InvitationActivity extends BaseActivity implements OnClickListener {

	TextView friends_num;
	TextView get_gold;
	TextView no_gold;
	RelativeLayout get_back;
	ImageView friend, space, wechat, weibo;
	ImageView faceback, tuite, email, message;
	TextView num_code, yqm, code2;
	// String str1, str2, str3;
	String today_gold;
	// LinearLayout share_en, share_zh;
	TextView share;
	public MediaPlayer mPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invitation);

		InitData();
		BindData();
		showmusic();
		// get_gold.setText(PreferenceManager.getmoney());

		UserMananger.profile(this, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				ProfileResponse response = (ProfileResponse) pResponse;

				today_gold = response.o.today_profit;

			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub

			}
		});

		if (NetworkUtils.instance().isNetworkConnected()) {
			getData();
		} else {

			friends_num.setText(PreferenceManager.getnum());
			get_gold.setText(PreferenceManager.getmoney());
			no_gold.setText(PreferenceManager.gettaskmoney());

			// ----------------------

			SpannableString str = new SpannableString(
					PreferenceManager.gettext());
			str.setSpan(new MyClickText(InvitationActivity.this), 15, 19,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			str.setSpan(new MyClickText(InvitationActivity.this), 49, 55,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			// str.setSpan(new AbsoluteSizeSpan(25), string.indexOf("2"),
			// string.indexOf("币") + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			// str.setSpan(new AbsoluteSizeSpan(25), string.length() - 8,
			// string.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			// 当然这里也可以通过setSpan来设置哪些位置的文本哪些颜色
			code2.setText(str);
			// code.setMovementMethod(LinkMovementMethod.getInstance());//
			// 不设置没有点击事件
			code2.setHighlightColor(Color.TRANSPARENT); // 设置点击后的颜色为透明
		}

	}

	private void InitData() {
		// TODO Auto-generated method stub

		friends_num = (TextView) findViewById(R.id.friend_num);
		get_gold = (TextView) findViewById(R.id.get_gold);

		// Log.d("aa", "默认值："+get_gold.getText().toString());
		no_gold = (TextView) findViewById(R.id.no_gold);
		get_back = (RelativeLayout) findViewById(R.id.get_back);
		friend = (ImageView) findViewById(R.id.friend);
		space = (ImageView) findViewById(R.id.space);
		wechat = (ImageView) findViewById(R.id.wechat);
		weibo = (ImageView) findViewById(R.id.weibo);
		code2 = (TextView) findViewById(R.id.code2);
		yqm = (TextView) findViewById(R.id.yqm);
		num_code = (TextView) findViewById(R.id.number_code);
		// share_zh = (LinearLayout) findViewById(R.id.share_zh);
		// share_en = (LinearLayout) findViewById(R.id.share_en);
		faceback = (ImageView) findViewById(R.id.faceback);
		tuite = (ImageView) findViewById(R.id.tuite);
		email = (ImageView) findViewById(R.id.email);
		message = (ImageView) findViewById(R.id.message);
		share = (TextView) findViewById(R.id.share);

		// str1 = getIntent().getStringExtra("invited_num");
		// str2 = getIntent().getStringExtra("invited_money");
		// str3 = getIntent().getStringExtra("inviting_money");
		//
		// Log.d("aa", str1 + str2 + str3);
		// friends_num.setText(str1);
		// get_gold.setText(str2);
		// no_gold.setText(str3);

	}

	public void getData() {

		UserMananger.getInvites(this, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				InvitationResponse response = (InvitationResponse) pResponse;

				setData(response.o);

			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub

				Log.d("aa", "请求失败");

				// InvitationResponse response = (InvitationResponse) pResponse;
				//
				friends_num.setText(PreferenceManager.getnum());
				get_gold.setText(PreferenceManager.getmoney());
				no_gold.setText(PreferenceManager.gettaskmoney());
			}
		});

		UserMananger.dyios(this, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub
				OtherResponse response = (OtherResponse) pResponse;

				String string = "2.邀请成功后，你和他均可获得" + response.o.invite_gold
						+ "金币。同时，好友后续做任务过程中获得的奖励金币，你也可以得到额外"
						+ response.o.invite_percent + "的分成哦。";

				PreferenceManager.savetext(string);

				SpannableString str = new SpannableString(string);
				str.setSpan(new MyClickText(InvitationActivity.this), 15, 19,
						Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				str.setSpan(new MyClickText(InvitationActivity.this), 49, 55,
						Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

				// str.setSpan(new AbsoluteSizeSpan(25), string.indexOf("2"),
				// string.indexOf("币") + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				// str.setSpan(new AbsoluteSizeSpan(25), string.length() - 8,
				// string.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				// 当然这里也可以通过setSpan来设置哪些位置的文本哪些颜色
				code2.setText(str);
				// code.setMovementMethod(LinkMovementMethod.getInstance());//
				// 不设置没有点击事件
				code2.setHighlightColor(Color.TRANSPARENT); // 设置点击后的颜色为透明

			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void setData(InvitationData data) {
		// TODO Auto-generated method stub

		if (!friends_num.getText().toString().equals(data.count)) {

			friends_num.setText(data.count);
		}
		String str = data.invited_money;

		// get_gold.setText(PreferenceManager.getaward());

		if (!data.invited_money.equals("0")) {

			// Log.d("aa", str + "________" + str1);
			if (Integer.parseInt(today_gold) > Integer
					.parseInt(PreferenceManager.getaward())) {
				// Log.d("aa", str + "------------" + str1);
				get_gold.setText(data.invited_money);

				PreferenceManager.savemoney(data.invited_money);
				PreferenceManager.saveaward(today_gold);
				Log.d("aa", "收徒后:" + PreferenceManager.getmoney());
				show();
			} else {
				// Log.d("aa", str + "````````````" + str1);
				PreferenceManager.savemoney(data.invited_money);
				get_gold.setText(PreferenceManager.getmoney());
				Log.d("aa", "两个值相等:");
			}
		} else {
			// Log.d("aa", str + "__1234___" + str1);
			get_gold.setText("0");
		}
		if (!no_gold.getText().toString().equals(data.invited_task_money)) {

			no_gold.setText(data.invited_task_money);
		}

	}

	private void BindData() {
		// TODO Auto-generated method stub

		yqm.setText(PreferenceManager.getTicket()); // 邀请码
		if (!TextUtils.isEmpty(PreferenceManager.getTicket())) {

			// String string =
			// "2.邀请成功后，你和他均可获得20金币。同时，好友后续做任务过程中获得的奖励金币，你也可以得到额外10%的分成哦。";
			// SpannableString str = new SpannableString(string);
			// str.setSpan(new MyClickText(this), 15, 19,
			// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			// str.setSpan(new MyClickText(this), 49, 55,
			// Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

			// str.setSpan(new AbsoluteSizeSpan(25), string.indexOf("2"),
			// string.indexOf("币") + 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			// str.setSpan(new AbsoluteSizeSpan(25), string.length() - 8,
			// string.length() - 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			// 当然这里也可以通过setSpan来设置哪些位置的文本哪些颜色
			// code2.setText(str);
			// code.setMovementMethod(LinkMovementMethod.getInstance());//
			// 不设置没有点击事件
			// code2.setHighlightColor(Color.TRANSPARENT); // 设置点击后的颜色为透明

			SpannableString str = new SpannableString("你的邀请码:"
					+ PreferenceManager.getTicket());

			// num_code.setText(PreferenceManager.getTicket());
			str.setSpan(new MyClickText(this), 6, 11,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			num_code.setText(str);
			num_code.setHighlightColor(Color.TRANSPARENT);

		}

		get_back.setOnClickListener(this);
		friend.setOnClickListener(this);
		wechat.setOnClickListener(this);
		weibo.setOnClickListener(this);
		space.setOnClickListener(this);
		faceback.setOnClickListener(this);
		tuite.setOnClickListener(this);
		message.setOnClickListener(this);
		email.setOnClickListener(this);
		share.setOnClickListener(this);

	}

	public void showmusic() {
		AssetManager am = getAssets();// ��ø�Ӧ�õ�AssetManager

		try {
			AssetFileDescriptor fileDescriptor = am.openFd("coicoi.wav");
			mPlayer = new MediaPlayer();
			mPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
					fileDescriptor.getStartOffset(), fileDescriptor.getLength());
			mPlayer.prepare(); // ׼��
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.get_back:

				mPlayer.stop();
				finish();

				break;

			// case R.id.friend:
			//
			// Toast.makeText(InvitationActivity.this, "朋友圈", Toast.LENGTH_SHORT)
			// .show();
			//
			// showShare(InvitationActivity.this);
			// //UserMananger.share(this, "能赚钱的App", "Wechat", "www.baidu.com");
			// break;
			//
			// case R.id.wechat:
			// showShare(InvitationActivity.this);
			// //UserMananger.share(this, "能赚钱的App", "Wechat", "www.baidu.com");
			// break;
			// case R.id.space:
			// Toast.makeText(InvitationActivity.this, "QQ空间", Toast.LENGTH_SHORT)
			// .show();
			// showShare(InvitationActivity.this);
			// break;
			// case R.id.weibo:
			// Toast.makeText(InvitationActivity.this, "微博", Toast.LENGTH_SHORT)
			// .show();
			// showShare(InvitationActivity.this);
			// break;
			// case R.id.faceback:
			// showShare(InvitationActivity.this);
			// break;
			//
			// case R.id.tuite:
			// showShare(InvitationActivity.this);
			// break;
			//
			// case R.id.email:
			// showShare(InvitationActivity.this);
			// break;
			//
			// case R.id.message:
			//
			// break;
			case R.id.share:

				showShare(InvitationActivity.this);

				break;
		}
	}

	// ---------------------------
	private void showShare(Context context) {
		ShareSDK.initSDK(context);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字 2.5.9以后的版本不调用此方法
		// oks.setNotification(R.drawable.ic_launcher,
		// getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle("啄金鸟");
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://www.zhuojinniao.com/share/");
		// text是分享文本，所有平台都需要这个字段
		// oks.setText("你的邀请码是:" + PreferenceManager.getTicket() + "\t\n"
		// + "邀请成功后，你和他均可获得20金币。同时，好友后续做任务过程中获得的奖励金币，你也可以得到额外10%的分成哦。");
		oks.setText("手机做任务，就可以躺着赚钱的App!");
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl("http://139.224.220.166/Goldpecker/Public/Uploads/img/icon.png");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/assets/ic_launcher.png");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://www.zhuojinniao.com/share/");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite("ShareSDK");
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://www.zhuojinniao.com/share/");

		// 启动分享GUI
		oks.show(context);
	}

	/** 显示奖励框 */

	public void show() {

		UserMananger.profile(this, new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub

				final MiddleDialog dialog = new MiddleDialog(
						InvitationActivity.this, false);
				dialog.show();
				dialog.setAwardText("20");
				mPlayer.start();
				// dialog.time();
				dialog.setNoShowClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub

						dialog.dismiss();
					}
				});
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				}, 2000);
				// }
			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	class MyClickText extends ClickableSpan {
		private Context context;
		String userid;

		public MyClickText(Context context) {
			this.context = context;
			// this.userid = str;
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			super.updateDrawState(ds);
			// 设置文本的颜色
			ds.setColor(0xFFFF5019);
			// 超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
			ds.setUnderlineText(false);
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub

		}

	}
}

// ----------------------------------------


