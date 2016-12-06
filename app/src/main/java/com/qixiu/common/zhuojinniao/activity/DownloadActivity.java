package com.qixiu.common.zhuojinniao.activity;

import java.util.ArrayList;
import java.util.Hashtable;

import com.adscendmedia.sdk.ui.OffersActivity;
import com.baidu.mapapi.map.Text;
import com.nativex.monetization.MonetizationManager;
import com.nativex.monetization.business.reward.Reward;
import com.nativex.monetization.communication.RedeemRewardData;
import com.nativex.monetization.enums.AdEvent;
import com.nativex.monetization.enums.NativeXAdPlacement;
import com.nativex.monetization.listeners.OnAdEventV2;
import com.nativex.monetization.listeners.RewardListener;
import com.nativex.monetization.listeners.SessionListener;
import com.nativex.monetization.mraid.AdInfo;
import com.qixiu.common.zhuojinniao.data.bean.DownloadBean;
import com.qixiu.common.zhuojinniao.main.Config;
import com.qixiu.common.zhuojinniao.ui.adapter.DownloadAdapter;

import com.qixiu.common.zhuojinniao.R;
import com.supersonic.mediationsdk.logger.SupersonicError;
import com.supersonic.mediationsdk.sdk.OfferwallListener;
import com.supersonic.mediationsdk.sdk.Supersonic;
import com.supersonic.mediationsdk.sdk.SupersonicFactory;
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
	public static final String TAG = "DownloadActivity";

    private Supersonic mSupersonicInstance;

	// UI elements
	private String displayText = "";
	private Button getCurrencyBalanceButton;
	private Button getDirectPlayVideoAd;

	private TextView outputTextView;

	// Tapjoy Placements
	private TJPlacement directPlayPlacement;
	private TJPlacement offerwallPlacement;

	private boolean earnedCurrency = false;
	ArrayList<DownloadBean> list = new ArrayList<DownloadBean>();

	ListView listview;
	DownloadAdapter adapter;
	RelativeLayout back;

	TextView title;
    private Boolean _canShowAds = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download);

		Initdata();
		Binddata();

        connectToTapjoy();
        initSupersonic();
        initNativeX();
	}

    private void initSupersonic() {
        //Get the mediation publisher instance
        mSupersonicInstance = SupersonicFactory.getInstance();

        //Set the Offerwall Listener
        mSupersonicInstance.setOfferwallListener(mOfferwallListener);

        //Init Offerwall
        mSupersonicInstance.initOfferwall(this, Config.SUPERSONIC_APP_ID, Secure.getString(this.getContentResolver(),
				Secure.ANDROID_ID));



    }

    private void initNativeX() {
        // create Session
        MonetizationManager.createSession(getApplicationContext(), Config.NATIVEX_APP_ID,
				Secure.getString(this.getContentResolver(), Secure.ANDROID_ID),
				sessionListener);

        // setting the currency listener
        // It is recommended that you implement this even if you do not plan to use
        // rewarded ads. You can then simply enable rewarded ads from Self Service without
        // any code changes or a new release.
        MonetizationManager.setRewardListener(new RewardListener() {
            @Override
            public void onRedeem(RedeemRewardData redeemRewardData) {
                //Take possession of the balances returned here.
                int totalRewardAmount = 0;
                for (Reward reward : redeemRewardData.getRewards()) {
                    Log.d("SampleApp", "Reward: rewardName:" + reward.getRewardName()
                            + " rewardId:" + reward.getRewardId()
                            + " amount:" + Double.toString(reward.getAmount()));
                    // add the reward amount to the total
                    totalRewardAmount += reward.getAmount();
                }
                redeemRewardData.showAlert(DownloadActivity.this);
            }
        });
    }

	private void Binddata() {
		// TODO Auto-generated method stub

		list.add(new DownloadBean("Adxmi", "", R.drawable.icon_img1));
        list.add(new DownloadBean("Tapjoy", "", R.drawable.icon_img1));
        list.add(new DownloadBean("Supersonic", "", R.drawable.icon_img1));
        list.add(new DownloadBean("NativeX", "", R.drawable.icon_img1));
        list.add(new DownloadBean("Adscend", "", R.drawable.icon_img1));

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
        if (mSupersonicInstance != null)
            mSupersonicInstance.onResume(this);

	}

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
	 * Notify Tapjoy the end of this activity for session tracking
	 */
	@Override
	protected void onStop() {
		Tapjoy.onActivityStop(this);
		OffersManager.getInstance(this).onAppExit();
		if (mSupersonicInstance != null)
			mSupersonicInstance.onPause(this);

		super.onStop();
	}

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
	 * Handles button clicks
	 */
	public void onClick(View v) {

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
            case 2:
                //show offer wall when user clicks the offer wall button
                mSupersonicInstance.showOfferwall();
                break;

            case 3:
            {
                if(_canShowAds == true)
                    //shows an ad that is already fetched and ready to show instantly
                    //NOTE: if the ad has not been fetched yet this method will not do anything
                    MonetizationManager.showReadyAd(DownloadActivity.this, NativeXAdPlacement.Main_Menu_Screen, onAdEventListener);
                else
                    android.util.Log.d("ShowAd","Can't show ads but still button clicked");

            }
                break;

            case 4:
                Intent intent = OffersActivity.getIntentForOfferWall(this, Config.ADSCEND_PUB_ID,
                        Config.ADSCEND_OFFERWALL_ID, Secure.getString(this.getContentResolver(),
                                Secure.ANDROID_ID));
                startActivity(intent);
                break;
            default:
                break;
        }
	}
//////////////////  NATIVEX  BEGIN/////////////////////////////////////////////////////////
    private SessionListener sessionListener = new SessionListener() {
        @Override
        public void createSessionCompleted(boolean success, boolean isOfferWallEnabled, String sessionId) {
            if (success) {
                // a session with our servers was established successfully.
                // the app is now ready to show ads.
                System.out.println("Wahoo! Now I'm ready to show an ad.");
                MonetizationManager.fetchAd(DownloadActivity.this, NativeXAdPlacement.Main_Menu_Screen, onAdEventListener);
                _canShowAds = true;
            } else {
                // establishing a session with our servers failed;
                // the app will be unable to show ads until a session is established
                System.out.println("Oh no! Something isn't set up correctly - re-read the documentation or ask customer support for some help - https://selfservice.nativex.com/Help");
                _canShowAds = false;
            }
        }
    };

    private OnAdEventV2 onAdEventListener = new OnAdEventV2() {
        @Override
        public void onEvent(AdEvent event, AdInfo adInfo, String message) {
            System.out.println("Placement: " + adInfo.getPlacement());
            switch (event) {
                case ALREADY_FETCHED:
                    // fetchAd() is called with an Ad Name and there is already a fetched ad with the same name ready to be shown.
                    break;
                case ALREADY_SHOWN:
                    // showAd() is called with an Ad Name and there is an ad already being shown with the same name at this moment.
                    break;
                case BEFORE_DISPLAY:
                    // Just before the Ad is displayed on the screen.
                    break;
                case DISMISSED:
                    // The ad is dismissed by the user or by the application.
                    break;
                case DISPLAYED:
                    // The ad is shown on the screen. For fetched ads this event will fire when the showAd() method is called.
                    break;
                case DOWNLOADING:
                    // fetchAd() is called with an Ad Name and there is an ad already being fetched with the same name at this moment.
                    break;
                case ERROR:
                    // An error has occurred and the ad is going to be closed.
                    // More information about the error is passed in the "message" parameter.
                    break;
                case EXPIRED:
                    // A fetched ad expires. All fetched ads will expire after a certain time period if not shown.
                    break;
                case FETCHED:
                    // The ad is ready to be shown. For fetched ads this method means that the ad is fetched successfully.
                    // You may want to initially put the showReadyAd() call here when you're doing your initial testing,
                    // but for production you should move it to a more appropriate place, as described in the Show an Ad section.
                    break;
                case NO_AD:
                    // The device contacts the server, but there is no ad ready to be shown at this time.
                    break;
                case USER_NAVIGATES_OUT_OF_APP:
                    // The user clicks on a link or a button in the ad and is going to navigate out of the app
                    // to the Google Play or a browser applications.
                    break;
                case IMPRESSION_CONFIRMED:
                    // ad has its impression event fired
                    break;
                case AD_CONVERTED:
                    // rewarded video ad has converted, and rewards will be given
                    break;
                default:
                    break;
            }
        }
    };
//////////////////  NATIVEX  END/////////////////////////////////////////////////////////////



    OfferwallListener mOfferwallListener = new OfferwallListener() {

        /**
         * Invoked when the Offerwall is prepared and ready to be shown to the user
         */
        @Override
        public void onOfferwallInitSuccess() {

        }

        /**
         * Invoked when the Offerwall does not load
         */
        @Override
        public void onOfferwallInitFail(SupersonicError supersonicError) {

        }

        /**
         * Invoked when the Offerwall successfully loads for the user, after calling the 'showOfferwall' method
         */
        @Override
        public void onOfferwallOpened() {

        }

        /**
         * Invoked when the method 'showOfferWall' is called and the OfferWall fails to load.  //@param supersonicError - A SupersonicError Object which represents the reason of 'showOfferwall' failure.
         */
        @Override
        public void onOfferwallShowFail(SupersonicError supersonicError) {

        }

        /**
         * Invoked each time the user completes an Offer.
         * Award the user with the credit amount corresponding to the value of the ‘credits’
         * parameter.
         * @param credits - The number of credits the user has earned.
         * @param totalCredits - The total number of credits ever earned by the user.
         * @param totalCreditsFlag - In some cases, we won’t be able to provide the exact
         * amount of credits since the last event (specifically if the user clears
         * the app’s data). In this case the ‘credits’ will be equal to the ‘totalCredits’,
         * and this flag will be ‘true’.
         * @return boolean - true if you received the callback and rewarded the user,
         * otherwise false.
         */
        @Override
        public boolean onOfferwallAdCredited(int credits, int totalCredits, boolean totalCreditsFlag) {
            return false;
        }


        /**
         * Invoked when the method 'getOfferWallCredits' fails to retrieve
         * the user's credit balance info.
         * @param supersonicError - A SupersonicError object which represents the reason of 'getOffereallCredits' failure.
         * If using client-side callbacks to reward users, it is mandatory to return true on this event
         */
        @Override
        public void onGetOfferwallCreditsFail(SupersonicError supersonicError) {

        }


        /**
         * Invoked when the user is about to return to the application after closing
         * the Offerwall.
         */
        @Override
        public void onOfferwallClosed() {

        }


    };
}
