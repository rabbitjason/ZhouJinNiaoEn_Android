package com.qixiu.common.zhoujinniao.activity;

import java.util.ArrayList;
import java.util.Hashtable;

import com.qixiu.common.zhoujinniao.data.bean.DownloadBean;
import com.qixiu.common.zhoujinniao.ui.adapter.DownloadAdapter;

import com.qixiu.common.zhoujinniao.R;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJEarnedCurrencyListener;
import com.tapjoy.TJError;
import com.tapjoy.TJGetCurrencyBalanceListener;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementVideoListener;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyLog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.adxmi.android.os.OffersManager;

@SuppressLint("NewApi")
public class DownloadActivity extends BaseActivity implements
		View.OnClickListener, TJGetCurrencyBalanceListener,
		TJPlacementListener, TJPlacementVideoListener, OnItemClickListener {
	public static final String TAG = "TapjoyEasyApp";

	// UI elements
	private String displayText = "";
	private Button getCurrencyBalanceButton;
	private Button getDirectPlayVideoAd;

	private TextView outputTextView;

	// Tapjoy Placements
	private TJPlacement directPlayPlacement;
	private TJPlacement offerwallPlacement;

	private boolean earnedCurrency = false;

	private Button currentButton;

	ArrayList<DownloadBean> list = new ArrayList<DownloadBean>();

	ListView listview;

	DownloadAdapter adapter;

	RelativeLayout back, showlayout;
	Button show;

	TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);

		Initdata();
		Binddata();
		connectToTapjoy();


	}

	private void Binddata() {
		// TODO Auto-generated method stub

		// list.add(new DownloadBean());
		// list.add(new DownloadBean());
		// list.add(new DownloadBean());
		// list.add(new DownloadBean());
		// list.add(new DownloadBean());
		// list.add(new DownloadBean());
		list.add(new DownloadBean("Adxmi", "", R.drawable.icon_img1));
        list.add(new DownloadBean("Tapjoy", "", R.drawable.icon_img1));

		adapter = new DownloadAdapter();
		adapter.setData(list);
		adapter.getDownload(this);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(this);
		back.setOnClickListener(this);

	}

	private void Initdata() {
		// TODO Auto-generated method stub

		listview = (ListView) findViewById(R.id.listview);
		back = (RelativeLayout) findViewById(R.id.get_back);
		title = (TextView) findViewById(R.id.title);
		((Button) findViewById(R.id.buttonShowOffers)).setOnClickListener(this);
		((RelativeLayout) findViewById(R.id.show)).setOnClickListener(this);

	}

	/**
	 * Attempts to connect to Tapjoy
	 */
	private void connectToTapjoy() {
		// OPTIONAL: For custom startup flags.
		Hashtable<String, Object> connectFlags = new Hashtable<String, Object>();
		connectFlags.put(TapjoyConnectFlag.ENABLE_LOGGING, "true");

		// If you are not using Tapjoy Managed currency, you would set your own
		// user ID here.
		// connectFlags.put(TapjoyConnectFlag.USER_ID, "A_UNIQUE_USER_ID");
		// u6SfEbh_TA-WMiGqgQ3W8QECyiQIURFEeKm0zbOggubusy-o5ZfXp33sTXaD
		// Connect with the Tapjoy server. Call this when the application first
		// starts. 5fFevb2RTGy0v8cKd2TfOAECQDHsK119nAxRBssIvqfUZuDFSC3F_pSN_fiq
		// REPLACE THE SDK KEY WITH YOUR TAPJOY SDK Key.
		String tapjoySDKKey = "u6SfEbh_TA-WMiGqgQ3W8QECyiQIURFEeKm0zbOggubusy-o5ZfXp33sTXaD";

		// Tapjoy.setGcmSender(Secure.getString(this.getContentResolver(),
		// Secure.ANDROID_ID));

		Tapjoy.setGcmSender("34027022155");
		// 设置用户ID
		Tapjoy.setUserID(Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID));

		// NOTE: This is the only step required if you're an advertiser.
		Tapjoy.connect(this, tapjoySDKKey, connectFlags,
				new TJConnectListener() {
					@Override
					public void onConnectSuccess() {
						DownloadActivity.this.onConnectSuccess();
					}

					@Override
					public void onConnectFailure() {
						DownloadActivity.this.onConnectFail();
					}
				});
	}

	/**
	 * Handles a successful connect to Tapjoy. Pre-loads direct play placement
	 * and sets up Tapjoy listeners
	 */
	public void onConnectSuccess() {
		updateTextInUI("Tapjoy SDK connected");

		// Start preloading direct play event upon successful connect
		directPlayPlacement = Tapjoy.getPlacement("video_unit", this);

		// Set Video Listener to anonymous callback
		directPlayPlacement.setVideoListener(new TJPlacementVideoListener() {
			@Override
			public void onVideoStart(TJPlacement placement) {
				Log.i(TAG,
						"Video has started has started for: "
								+ placement.getName());
			}

			@Override
			public void onVideoError(TJPlacement placement, String message) {
				Log.i(TAG,
						"Video error: " + message + " for "
								+ placement.getName());
			}

			@Override
			public void onVideoComplete(TJPlacement placement) {
				Log.i(TAG, "Video has completed for: " + placement.getName());

				// Best Practice: We recommend calling getCurrencyBalance as
				// often as possible so the user�s balance is always up-to-date.
				Tapjoy.getCurrencyBalance(DownloadActivity.this);
			}

		});

		directPlayPlacement.requestContent();

		// NOTE: The get/spend/award currency methods will only work if your
		// virtual currency
		// is managed by Tapjoy.
		//
		// For NON-MANAGED virtual currency, Tapjoy.setUserID(...)
		// must be called after requestTapjoyConnect.

		// Setup listener for Tapjoy currency callbacks
		Tapjoy.setEarnedCurrencyListener(new TJEarnedCurrencyListener() {
			@Override
			public void onEarnedCurrency(String currencyName, int amount) {
				earnedCurrency = true;
				updateTextInUI("You've just earned " + amount + " "
						+ currencyName);
				showPopupMessage("You've just earned " + amount + " "
						+ currencyName);
			}
		});
	}

	/**
	 * Handles a failed connect to Tapjoy
	 */
	public void onConnectFail() {
		Log.e(TAG, "Tapjoy connect call failed");
		updateTextInUI("Tapjoy connect failed!");
	}

	/**
	 * Notify Tapjoy the start of this activity for session tracking
	 */
	@Override
	protected void onStart() {
		super.onStart();
		Tapjoy.onActivityStart(this);
        OffersManager.getInstance(this).onAppLaunch();
	}

	/**
	 * Notify Tapjoy the end of this activity for session tracking
	 */
	@Override
	protected void onStop() {
		super.onStop();
		Tapjoy.onActivityStop(this);
        OffersManager.getInstance(this).onAppExit();
	}

    @Override
    protected void onDestroy() {



        super.onDestroy();

    }

    /**
	 * Handles button clicks
	 */
	public void onClick(View v) {
//		if (v instanceof Button) {
//			currentButton = ((Button) v);
//			int id = currentButton.getId();
//
//			switch (id) {
//			case R.id.show:
//			case R.id.buttonShowOffers:
//				// Disable button
//				Log.d("TAG", id + "_____" + "单击了...");
//				currentButton.setEnabled(true);
//
//				// Show Offers Placement
//				//callTapjoyShowOffers();
//				break;
//
//			}
//		}

		switch (v.getId()) {
		case R.id.get_back:
			finish();
			break;

		default:
			break;
		}
	}

	private void callTapjoyShowOffers() {
		// Construct TJPlacement to show Offers web view from where users can
		// download the latest offers for virtual currency.
		offerwallPlacement = Tapjoy.getPlacement("offerwall_unit",
				new TJPlacementListener() {
					@Override
					public void onRequestSuccess(TJPlacement placement) {
						updateTextInUI("onRequestSuccess for placement "
								+ placement.getName());

						if (!placement.isContentAvailable()) {
							updateTextInUI("No Offerwall content available");
							Log.d("TAG", "No Offerwall content available");
						}

						//setButtonEnabledInUI(currentButton, true);
					}

					@Override
					public void onRequestFailure(TJPlacement placement,
							TJError error) {
						//setButtonEnabledInUI(currentButton, true);
						updateTextInUI("Offerwall error: " + error.message);
					}

					@Override
					public void onContentReady(TJPlacement placement) {
						TapjoyLog.i(TAG, "onContentReady for placement "
								+ placement.getName());

						updateTextInUI("Offerwall request success");
						placement.showContent();
					}

					@Override
					public void onContentShow(TJPlacement placement) {
						TapjoyLog.i(TAG, "onContentShow for placement "
								+ placement.getName());
					}

					@Override
					public void onContentDismiss(TJPlacement placement) {
						TapjoyLog.i(TAG, "onContentDismiss for placement "
								+ placement.getName());
					}

					@Override
					public void onPurchaseRequest(TJPlacement placement,
							TJActionRequest request, String productId) {
					}

					@Override
					public void onRewardRequest(TJPlacement placement,
							TJActionRequest request, String itemId, int quantity) {
					}
				});

		// Add this class as a video listener
		offerwallPlacement.setVideoListener(this);
		offerwallPlacement.requestContent();
	}

	// ================================================================================
	// TapjoyListener Methods
	// ================================================================================
	@Override
	public void onGetCurrencyBalanceResponse(String currencyName, int balance) {
		Log.i(TAG, "currencyName: " + currencyName);
		Log.i(TAG, "balance: " + balance);

		if (earnedCurrency) {
			updateTextInUI(displayText + "\n" + currencyName + ": " + balance);
			earnedCurrency = false;
		} else {
			updateTextInUI(currencyName + ": " + balance);
		}
		setButtonEnabledInUI(getCurrencyBalanceButton, true);
	}

	@Override
	public void onGetCurrencyBalanceResponseFailure(String error) {
		updateTextInUI("getCurrencyBalance error: " + error);
		setButtonEnabledInUI(getCurrencyBalanceButton, true);
	}

	/*
	 * TJPlacement callbacks
	 */
	@Override
	public void onRequestSuccess(TJPlacement placement) {
		// If content is not available you can note it here and act accordingly
		// as best suited for your app
		Log.i(TAG,
				"Tapjoy on request success, contentAvailable: "
						+ placement.isContentAvailable());
	}

	@Override
	public void onRequestFailure(TJPlacement placement, TJError error) {
		Log.i(TAG, "Tapjoy send event " + placement.getName()
				+ " failed with error: " + error.message);
	}

	@Override
	public void onContentReady(TJPlacement placement) {
	}

	@Override
	public void onContentShow(TJPlacement placement) {
	}

	@Override
	public void onContentDismiss(TJPlacement placement) {
		Log.i(TAG, "Tapjoy direct play content did disappear");

		setButtonEnabledInUI(getDirectPlayVideoAd, true);

		// Best Practice: We recommend calling getCurrencyBalance as often as
		// possible so the user's balance is always up-to-date.
		Tapjoy.getCurrencyBalance(DownloadActivity.this);

		// Begin preloading the next placement after the previous one is
		// dismissed
		directPlayPlacement = Tapjoy.getPlacement("video_unit", this);

		// Set Video Listener to anonymous callback
		directPlayPlacement.setVideoListener(new TJPlacementVideoListener() {
			@Override
			public void onVideoStart(TJPlacement placement) {
				Log.i(TAG,
						"Video has started has started for: "
								+ placement.getName());
			}

			@Override
			public void onVideoError(TJPlacement placement, String errorMessage) {
				Log.i(TAG,
						"Video error: " + errorMessage + " for "
								+ placement.getName());
			}

			@Override
			public void onVideoComplete(TJPlacement placement) {
				Log.i(TAG, "Video has completed for: " + placement.getName());

				// Best Practice: We recommend calling getCurrencyBalance as
				// often as possible so the user�s balance is always up-to-date.
				Tapjoy.getCurrencyBalance(DownloadActivity.this);
			}
		});

		directPlayPlacement.requestContent();
	}

	@Override
	public void onPurchaseRequest(TJPlacement placement,
			TJActionRequest request, String productId) {
	}

	@Override
	public void onRewardRequest(TJPlacement placement, TJActionRequest request,
			String itemId, int quantity) {
	}

	/**
	 * Video listener callbacks
	 */
	@Override
	public void onVideoStart(TJPlacement placement) {
		Log.i(TAG, "Video has started has started for: " + placement.getName());
	}

	@Override
	public void onVideoError(TJPlacement placement, String errorMessage) {
		Log.i(TAG,
				"Video error: " + errorMessage + " for " + placement.getName());
	}

	@Override
	public void onVideoComplete(TJPlacement placement) {
		Log.i(TAG, "Video has completed for: " + placement.getName());

		// Best Practice: We recommend calling getCurrencyBalance as often as
		// possible so the user�s balance is always up-to-date.
		Tapjoy.getCurrencyBalance(DownloadActivity.this);
	}

	/**
	 * Update the text view on the UI thread
	 * 
	 * @param text
	 *            text to display in UI
	 */
	private void updateTextInUI(final String text) {
		displayText = text;

		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (outputTextView != null) {
					outputTextView.setText(text);
				}
			}
		});
	}

	/**
	 * Re-enable button on the UI thread
	 * 
	 * @param button
	 *            button to enable
	 * @param enabled
	 *            whether to enable the button or not
	 */
	private void setButtonEnabledInUI(final Button button, final Boolean enabled) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				button.setEnabled(enabled);
			}
		});
	}

	/**
	 * Helper function to show a Toast to the user
	 * 
	 * @param text
	 *            text that you want to display in the Toast
	 */
	private void showPopupMessage(final String text) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});
	}

	// For trackPurchase. WARNING! Not for production use
	private String getDummySkuDetails() {
		return "{\"title\":\"TITLE\",\"price\":\"$3.33\",\"type\":\"inapp\",\"description\":\"DESC\",\"price_amount_micros\":3330000,\"price_currency_code\":\"USD\",\"productId\":\"3\"}";
	}

	// For trackPurchase. WARNING! Not for production use
	private Intent getDummyResponseIntent() {
		return new Intent();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                // 积分墙通用版配置检查（使用“通过 Receiver 来获取积分订单”功能）：
                boolean isSuccess = OffersManager.getInstance(this).checkOffersAdConfig(false);
                // Show Adxmi offers Wall
                OffersManager.getInstance(this).showOffersWall();
                break;

            case 1:
                // Show Offers Placement
                callTapjoyShowOffers();

                break;

            default:
                break;
        }
	}


}
