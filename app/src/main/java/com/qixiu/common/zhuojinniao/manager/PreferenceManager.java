package com.qixiu.common.zhuojinniao.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.qixiu.common.zhuojinniao.main.App;

public class PreferenceManager {

	static SharedPreferences sharedPreferences;

	static String firstIn = "first";
	static String userId = "userId";
	static String nickName = "nickName";
	static String phone = "phone";
	static String platForm = "platForm";
	static String head = "head";
	static String ticket = "ticket";
	static String sign = "sign";
	static String name = null;
	static String img = null;
	static String device = "device";
	static String award = "award";
	static String money = "money";
	static String credit = "credit";
	static String check = "check";
	static String num = "num";
	static String taskmoney = "taskmoney";
	static String text = "text";

	private static SharedPreferences getSharedPreferences() {
		if (sharedPreferences == null)
			sharedPreferences = App.getContext().getSharedPreferences(
					"ehomesafe", Context.MODE_APPEND);
		return sharedPreferences;
	}

	public static void clearData() {
		saveFirst(0);
		saveUserId("");
		saveNickName("");
		savePhone("");
		savePlatForm("");
	}

	public static void savecheck(int value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putInt(firstIn, value);
		editor.commit();
	}

	public static int getcheck() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getInt(firstIn, 0);
	}

	public static void saveUserId(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(userId, value);
		editor.commit();
	}

	public static String getUserId() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(userId, "");
	}

	// ----------------------------------------

	public static void savetext(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(text, value);
		editor.commit();
	}

	public static String gettext() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(text, "");
	}
	public static void savetaskmoney(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(taskmoney, value);
		editor.commit();
	}

	public static String gettaskmoney() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(taskmoney, "0");
	}

	public static void savenum(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(num, value);
		editor.commit();
	}

	public static String getnum() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(num, "0");
	}


	public static void saveFirst(int value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putInt(firstIn, value);
		editor.commit();
	}

	public static int getFirst() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getInt(firstIn, 0);
	}


	public static void savecredit(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(credit, value);
		editor.commit();
	}

	public static String getcredit() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(credit, "");
	}

	public static void savemoney(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(money, value);
		editor.commit();
	}

	public static String getmoney() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(money, "0");
	}


	public static void saveaward(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(award, value);
		editor.commit();
	}

	public static String getaward() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(award, "");
	}
	public static void savename(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(name, value);
		editor.commit();
	}

	public static String getname() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(name, "");
	}

	public static void saveimg(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(img, value);
		editor.commit();
	}

	public static String getimg() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(img, "");
	}

	public static void saveSign(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(sign, value);
		editor.commit();
	}

	public static String getSign() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(sign, "");
	}

	public static void saveNickName(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(nickName, value);
		editor.commit();
	}

	public static String getNickName() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(nickName, "");
	}

	public static void savePhone(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(phone, value);
		editor.commit();
	}

	public static String getPhone() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(phone, "");
	}

	public static void savePlatForm(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(platForm, value);
		editor.commit();
	}

	public static String getPlatForm() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(platForm, "");
	}

	public static void saveHead(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(head, value);
		editor.commit();
	}

	public static String getHead() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(head, "");
	}

	public static void saveTicket(String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(ticket, value);
		editor.commit();
	}

	public static String getTicket() {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(ticket, "");
	}

	public static void saveHXUserPhoto(Context context, String userId,
									   String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(userId + "_userPhoto", value);
		editor.commit();
	}

	public static String getHXUserPhoto(Context context, String userId) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(userId + "_userPhoto", "");
	}

	public static void saveHXUserName(Context context, String userId,
									  String value) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();
		editor.putString(userId + "_userName", value);
		editor.commit();
	}

	public static String getHXUserName(Context context, String userId) {
		SharedPreferences sharedPreferences = getSharedPreferences();
		return sharedPreferences.getString(userId + "_userName", "");
	}

}
