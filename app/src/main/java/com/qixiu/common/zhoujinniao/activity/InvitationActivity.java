package com.qixiu.common.zhoujinniao.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
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

import com.qixiu.common.zhoujinniao.data.response.BaseResponse;
import com.qixiu.common.zhoujinniao.data.response.InvitationResponse;
import com.qixiu.common.zhoujinniao.data.responsedata.InvitationData;
import com.qixiu.common.zhoujinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhoujinniao.manager.PreferenceManager;
import com.qixiu.common.zhoujinniao.manager.UserMananger;
import com.qixiu.common.zhoujinniao.R;


public class InvitationActivity extends BaseActivity implements OnClickListener {

	TextView friends_num;
	TextView get_gold;
	TextView no_gold;
	RelativeLayout get_back;
	ImageView friend, space, wechat, weibo;
	ImageView faceback, tuite, email, message;
	TextView num_code, code;
	String str1, str2, str3;
	// LinearLayout share_en, share_zh;
	TextView share;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invitation);

		InitData();
		BindData();
		//getData();

	}

	private void InitData() {
		// TODO Auto-generated method stub

		friends_num = (TextView) findViewById(R.id.friend_num);
		get_gold = (TextView) findViewById(R.id.get_gold);
		no_gold = (TextView) findViewById(R.id.no_gold);
		get_back = (RelativeLayout) findViewById(R.id.get_back);
		friend = (ImageView) findViewById(R.id.friend);
		space = (ImageView) findViewById(R.id.space);
		wechat = (ImageView) findViewById(R.id.wechat);
		weibo = (ImageView) findViewById(R.id.weibo);
		code = (TextView) findViewById(R.id.code);
		num_code = (TextView) findViewById(R.id.number_code);
		// share_zh = (LinearLayout) findViewById(R.id.share_zh);
		// share_en = (LinearLayout) findViewById(R.id.share_en);
		faceback = (ImageView) findViewById(R.id.faceback);
		tuite = (ImageView) findViewById(R.id.tuite);
		email = (ImageView) findViewById(R.id.email);
		message = (ImageView) findViewById(R.id.message);
		share = (TextView) findViewById(R.id.share);

		str1 = getIntent().getStringExtra("invited_num");
		str2 = getIntent().getStringExtra("invited_money");
		str3 = getIntent().getStringExtra("inviting_money");

		Log.d("aa", str1 + str2 + str3);
		friends_num.setText(str1);
		get_gold.setText(str2);
		no_gold.setText(str3);

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

			}
		});

	}

	private void setData(InvitationData data) {
		// TODO Auto-generated method stub

		if (!friends_num.getText().toString().equals(data.count)) {

			friends_num.setText(data.count);
		}

		if (!get_gold.getText().toString().equals(data.invited_money)) {

			get_gold.setText(data.invited_money);
		}
		if (!no_gold.getText().toString().equals(data.inviting_money)) {

			no_gold.setText(data.inviting_money);
		}

	}

	private void BindData() {
		// TODO Auto-generated method stub

		if (!TextUtils.isEmpty(PreferenceManager.getTicket())) {

			num_code.setText(PreferenceManager.getTicket());

			SpannableString str = new SpannableString("你的邀请码是"
					+ PreferenceManager.getTicket()
					+ ",邀请成功,你和被邀请人都将获得20金币,以后每次邀请人完成任务,你都将获得10%作为奖励");
			str.setSpan(new MyClickText(this), 6, 11,
					Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			// 当然这里也可以通过setSpan来设置哪些位置的文本哪些颜色
			code.setText(str);
			// code.setMovementMethod(LinkMovementMethod.getInstance());//
			// 不设置没有点击事件
			code.setHighlightColor(Color.TRANSPARENT); // 设置点击后的颜色为透明

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

		// ---------------------------------

		// if
		// (getResources().getConfiguration().locale.getLanguage().equals("zh"))
		// {
		//
		// share_zh.setVisibility(View.VISIBLE);
		// share_en.setVisibility(View.GONE);
		// } else if (getResources().getConfiguration().locale.getLanguage()
		// .equals("en")) {
		//
		// share_en.setVisibility(View.VISIBLE);
		// share_zh.setVisibility(View.GONE);
		// }

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.get_back:

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
		oks.setTitle("标题");
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://sharesdk.cn");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我是分享文本");
		// 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
		oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		// oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
		// url仅在微信（包括好友和朋友圈）中使用
		oks.setUrl("http://sharesdk.cn");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("我是测试评论文本");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite("ShareSDK");
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://sharesdk.cn");

		// 启动分享GUI
		oks.show(context);
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


