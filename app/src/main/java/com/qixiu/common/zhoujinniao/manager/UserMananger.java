package com.qixiu.common.zhoujinniao.manager;

import android.content.Context;
import android.provider.Settings.Secure;
import android.util.Log;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

import com.qixiu.common.zhoujinniao.data.response.BaseResponse;
import com.qixiu.common.zhoujinniao.data.response.GetRanksResponse;
import com.qixiu.common.zhoujinniao.data.response.GetTasksResponse;
import com.qixiu.common.zhoujinniao.data.response.InvitationResponse;
import com.qixiu.common.zhoujinniao.data.response.LoginResponse;
import com.qixiu.common.zhoujinniao.data.response.ProfileResponse;
import com.qixiu.common.zhoujinniao.main.Config;
import com.qixiu.common.zhoujinniao.manager.BaseHttpManager.BaseCallListener;
import com.loopj.android.http.RequestParams;

public class UserMananger {

	private static BaseCallListener pListener;

	/** 登录 */
	public static void login(Context context, final BaseCallListener pListener) {

		// http://115.28.94.239/Goldpecker/App/User/androidSignInByDevice?device=DEVICE

		String url = Config.hostString
				+ "App/User/signInByDevice?device="
				+ Secure.getString(context.getContentResolver(),
						Secure.ANDROID_ID) + "&device_type=" + 2;

		BaseHttpManager.get(context, url, null, LoginResponse.class,
				new BaseCallListener() {

					@Override
					public void onSuccess(BaseResponse pResponse) {
						// TODO Auto-generated method stub
						LoginResponse response = (LoginResponse) pResponse;
						PreferenceManager.saveNickName(response.o.nickname);
						PreferenceManager.savePhone(response.o.mobile);
						PreferenceManager.saveUserId(response.o.uid);
						PreferenceManager.saveHead(response.o.head);
						PreferenceManager.saveTicket(response.o.ticket);
						PreferenceManager
								.saveSign(response.o.continue_sgntimes);

						pListener.onSuccess(pResponse);
					}

					@Override
					public void onFail(BaseResponse pResponse) {
						// TODO Auto-generated method stub

						pListener.onFail(pResponse);

					}
				});
	}

	/** 邀请码界面 */
	public static void referTicket(Context context, String ticket,
			BaseCallListener plListener) {

		// http://115.28.94.239/Goldpecker/App/User/referTicket?uid=UID&$ticket=TICKET

		String url = Config.hostString + "App/User/referTicket?uid="
				+ PreferenceManager.getUserId() + "&ticket=" + ticket;

		BaseHttpManager.get(context, url, null, BaseResponse.class, plListener);

	}

	/** 攻略详情 */
	public static void profile(Context context, BaseCallListener pListener) {

		// http://115.28.94.239/Goldpecker/App/User/profile?uid=UID

		String url = Config.hostString + "App/User/profile?uid="
				+ PreferenceManager.getUserId();

		BaseHttpManager.get(context, url, null, ProfileResponse.class,
				pListener);
	}

	/** 任务明细 */
	public static void getTasks(Context context, int pageNo,
			BaseCallListener pListener) {

		// http://115.28.94.239/Goldpecker/App/User/getTasks?uid=UID
		// &page_number=PAGE_NUMBER&page_sum=PAGE_SUM
		String url = Config.hostString + "App/User/getTasks?uid="
				+ PreferenceManager.getUserId() + "&page_number=" + pageNo
				+ "&page_sum=" + Config.listCount;

		BaseHttpManager.get(context, url, null, GetTasksResponse.class,
				pListener);

	}

	/** 收入排名 */
	public static void getRanks(Context context, BaseCallListener pListener) {

		// http://115.28.94.239/Goldpecker/App/Rank/getRankks?uid=UID

		String url = Config.hostString + "App/Rank/getRanks?uid="
				+ PreferenceManager.getUserId();

		BaseHttpManager.get(context, url, null, GetRanksResponse.class,
				pListener);

	}

	/** 签到 */
	public static void Sign(Context context, BaseCallListener pListener) {

		// http://115.28.94.239/Goldpecker/App/Sign/sign

		RequestParams data = new RequestParams();

		String url = Config.hostString + "App/Sign/sign";

		data.put("uid", Integer.parseInt(PreferenceManager.getUserId()));
		// data.put("task_type", "Sign");

		Log.d("aa", url + "_____________" + data);

		BaseHttpManager.post(context, url, data, BaseResponse.class, pListener);

	}

	/** 收徒信息 */
	public static void getInvites(Context context, BaseCallListener pListener) {

		// http://localhost/Goldpecker/App/User/getInvites?uid=UID

		String url = Config.hostString + "App/User/getInvites?uid="
				+ PreferenceManager.getUserId();

		BaseHttpManager.get(context, url, null, InvitationResponse.class,
				pListener);
	}

	// 分享
	public static void share(Context context, String title, String platName,
			String url) {
		OnekeyShare oks = new OnekeyShare();
		oks.setSilent(true);
		oks.setPlatform(platName);
		oks.setTheme(OnekeyShareTheme.CLASSIC);
		oks.setDialogMode();
		oks.disableSSOWhenAuthorize();
		oks.setTitle(title);
		oks.setTitleUrl(url);
		oks.setText(url);
		oks.setImageUrl("http://www.mengxiaoapp.com/Public/Uploads/20160729/579b0ce8b3fbb.png");
		oks.setUrl(url); // 微信不绕过审核分享链接
		oks.setComment(""); // 我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
		oks.setSite("全名秀"); // QZone分享完之后返回应用时提示框上显示的名称
		oks.setSiteUrl(url);// QZone分享参数
		oks.setVenueName("全名秀");
		oks.setVenueDescription("");
		// oks.setCallback(callback);
		oks.show(context);
	}

}
