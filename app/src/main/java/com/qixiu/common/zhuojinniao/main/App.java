package com.qixiu.common.zhuojinniao.main;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.multidex.MultiDexApplication;

import cn.sharesdk.framework.ShareSDK;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.tapjoy.TJPlacement;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import net.adxmi.android.AdManager;
import net.adxmi.android.os.OffersManager;

public class App extends MultiDexApplication {
	private static WeakReference<App> instance;
	public static Activity mCurrentActivity;
	public static IWXAPI wxApi;

	public static double lat;
	public static double lng;
	public static float radius;
	public static String locationName;

	// -----------------
	public static final String TAG = "App";

	// Tapjoy Placements
	private TJPlacement directPlayPlacement;

	@Override
	public void onCreate() {
		super.onCreate();

		// 设置微信Config
		// 设置百度地图，manifest
		instance = new WeakReference<App>(this);

		wxApi = WXAPIFactory.createWXAPI(this, Config.WX_APP_ID, false);
		wxApi.registerApp(Config.WX_APP_ID);

		//Set Admxi config
        //AdManager.getInstance(getApplicationContext()).setEnableDebugLog(false);
		OffersManager.getInstance(this).setCustomUserId(
				Uri.encode(
						Settings.Secure.getString(this.getContentResolver(), "utf-8"),
						Settings.Secure.ANDROID_ID));
		//有米Android SDK v4.10之后的sdk还需要配置下面代码，以告诉sdk使用了服务器回调
		OffersManager.getInstance(this).setUsingServerCallBack(true);
		AdManager.getInstance(getApplicationContext()).init(Config.ADXMI_APP_ID, Config.ADXMI_APP_SECRET);

//		File cacheDir = StorageUtils.getOwnCacheDirectory(
//				getApplicationContext(), "imageloader/Cache");
//
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//				this)
//				.memoryCacheExtraOptions(480, 800)
//				// max width, max height，即保存的每个缓存文件的最大长宽
//				.discCacheExtraOptions(480, 800, null)
//				// Can slow ImageLoader, use it carefully (Better don't use
//				// it)/设置缓存的详细信息，最好不要设置这个
//				.threadPoolSize(3)
//				// 线程池内加载的数量
//				.threadPriority(Thread.NORM_PRIORITY - 2)
//				.denyCacheImageMultipleSizesInMemory()
//				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
//				// You can pass your own memory cache
//				// implementation/你可以通过自己的内存缓存实现
//				.memoryCacheSize(2 * 1024 * 1024)
//				.discCacheSize(50 * 1024 * 1024)
//				.discCacheFileNameGenerator(new Md5FileNameGenerator())
//				// 将保存的时候的URI名称用MD5 加密
//				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				.discCacheFileCount(100)
//				// 缓存的文件数量
//				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//				.imageDownloader(
//						new BaseImageDownloader(this, 5 * 1000, 30 * 1000)) // connectTimeout
//																			// (5
//																			// s),
//																			// readTimeout
//																			// (30
//																			// s)超时时间
//				.writeDebugLogs() // Remove for release app
//				.build();// 开始构建
//		ImageLoader.getInstance().init(config);
		// ImageLoader.getInstance().init(
		// ImageLoaderConfiguration.createDefault(this));

		initImageLoader(getApplicationContext());

		SDKInitializer.initialize(getApplicationContext());
		LocationClient mLocClient = new LocationClient(this);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); // 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		option.setIsNeedAddress(true);
		option.setIgnoreKillProcess(true);

		option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
		option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得
												// 到

		mLocClient.setLocOption(option);

		mLocClient.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation arg0) {
				lat = arg0.getLatitude();
				lng = arg0.getLongitude();
				locationName = arg0.getLocationDescribe();
				if (arg0.getPoiList() != null && arg0.getPoiList().size() > 0) {
					locationName = arg0.getPoiList().get(0).getName();
				}
			}
		});
		mLocClient.start();

		ShareSDK.initSDK(this);
	}

	public static App getContext() {
		return (App) instance.get();
	}

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public static String getVersion() {
		try {
			PackageManager manager = App.mCurrentActivity.getPackageManager();
			PackageInfo info = manager.getPackageInfo(
					App.mCurrentActivity.getPackageName(), 0);
			String version = info.versionName;
			return version;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration
				.Builder(context)
				.threadPoolSize(3)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new WeakMemoryCache())
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
		ImageLoader.getInstance().init(config);
	}

}
