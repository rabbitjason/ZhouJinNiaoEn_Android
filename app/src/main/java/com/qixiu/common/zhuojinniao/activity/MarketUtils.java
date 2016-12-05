package com.qixiu.common.zhuojinniao.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

/**
 * ��ת��Ӧ���̵깤����
 * **/
public class MarketUtils {
	/**
	 * ��ȡ�Ѱ�װӦ���̵�İ����б�
	 * **/
	public static ArrayList<String> queryInstalledMarketPkgs(Context context) {
		ArrayList<String> pkgs = new ArrayList<String>();
		if (context == null)
			return pkgs;
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.APP_MARKET");
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
		if (infos == null || infos.size() > 0)
			return pkgs;
		int size = infos.size();
		for (int i = 0; i < size; i++) {
			String pkgName = "";
			try {
				ActivityInfo activityInfo = infos.get(i).activityInfo;
				pkgName = activityInfo.packageName;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!TextUtils.isEmpty(pkgName))
				pkgs.add(pkgName);
		}

		return pkgs;
	}

	/**
	 * ���˳��Ѿ���װ�İ�������
	 **/
	public static ArrayList<String> filterInstalledPkgs(Context context,
			ArrayList<String> pkgs) {
		ArrayList<String> empty = new ArrayList<String>();
		if (context == null || pkgs == null || pkgs.size() == 0)
			return empty;
		PackageManager pm = context.getPackageManager();
		List<PackageInfo> installedPkgs = pm.getInstalledPackages(0);
		int li = installedPkgs.size();
		int lj = pkgs.size();
		for (int j = 0; j < lj; j++) {
			for (int i = 0; i < li; i++) {
				String installPkg = "";
				String checkPkg = pkgs.get(j);
				try {
					installPkg = installedPkgs.get(i).applicationInfo.packageName;
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (TextUtils.isEmpty(installPkg))
					continue;
				if (installPkg.equals(checkPkg)) {
					empty.add(installPkg);
					break;
				}

			}
		}
		return empty;
	}

	/**
	 * ������app�������
	 * 
	 * @param appPkg
	 *            App�İ���
	 * @param marketPkg
	 *            Ӧ���̵���� ,���Ϊ""����ϵͳ����Ӧ���̵��б��û�ѡ��,�����ת��Ŀ���г���Ӧ��������棬ĳЩӦ���̵���ܻ�ʧ��
	 */
	public static void launchAppDetail(String appPkg, String marketPkg , Context context) {
		try {
			if (TextUtils.isEmpty(appPkg))
				return;
			Uri uri = Uri.parse("market://details?id=" + appPkg);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			if (!TextUtils.isEmpty(marketPkg))
				intent.setPackage(marketPkg);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// AppUtils.getAppContext().startActivity(intent);
			context.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/*
 * ����Ӧ���̵��Ӧ�İ������£� 
 * com.qihoo.appstore 360�ֻ�����
 *  com.taobao.appcenter �Ա��ֻ�����
 * com.tencent.android.qqdownloader Ӧ�ñ� 
 * com.hiapk.marketpho ��׿�г� 
 * cn.goapk.market �����г�
 */

