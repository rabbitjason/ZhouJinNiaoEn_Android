package com.qixiu.common.zhuojinniao.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qixiu.common.zhuojinniao.activity.DetailActivity;

import com.qixiu.common.zhuojinniao.activity.InvitationActivity;
import com.qixiu.common.zhuojinniao.activity.MainActivity;
import com.qixiu.common.zhuojinniao.activity.MarketUtils;
import com.qixiu.common.zhuojinniao.activity.MessageActivity;
import com.qixiu.common.zhuojinniao.activity.RankingActivity;
import com.qixiu.common.zhuojinniao.activity.SignInActivity;
import com.qixiu.common.zhuojinniao.activity.DownloadActivity;
import com.qixiu.common.zhuojinniao.activity.WithdrawOneActivity;
import com.qixiu.common.zhuojinniao.data.bean.ImageTextBean;
import com.qixiu.common.zhuojinniao.data.bean.TaskBean;
import com.qixiu.common.zhuojinniao.data.response.BaseResponse;
import com.qixiu.common.zhuojinniao.data.response.GetRanksResponse;
import com.qixiu.common.zhuojinniao.data.response.ProfileResponse;
import com.qixiu.common.zhuojinniao.data.responsedata.ProfileData;
import com.qixiu.common.zhuojinniao.data.responsedata.UserData;
import com.qixiu.common.zhuojinniao.manager.BaseHttpManager.BaseCallListener;
import com.qixiu.common.zhuojinniao.manager.PreferenceManager;
import com.qixiu.common.zhuojinniao.manager.UserMananger;
import com.qixiu.common.zhuojinniao.manager.WebManager;
import com.qixiu.common.zhuojinniao.ui.adapter.ImgeTextAdapter;
import com.qixiu.common.zhuojinniao.ui.adapter.TaskAdapter;
import com.qixiu.common.zhuojinniao.ui.view.LoadingView;
import com.qixiu.common.zhuojinniao.R;


public class MainFragment extends Fragment implements OnItemClickListener, View.OnClickListener {
	View mView;
	boolean hasInit;
	ImageView set;
	RelativeLayout interCity;

	ArrayList<ImageTextBean> itList = new ArrayList<ImageTextBean>();
	ImgeTextAdapter adapter;
	GridView gridView;
	ArrayList<TaskBean> list = new ArrayList<TaskBean>();
	TaskAdapter taskAdapter;
	ListView listView;
	String total;
	// RelativeLayout rl_layout1;
	TextView earnings, balance, total_earnings, recvMsg;
    String recvStr = "";
	// ImageView user_head;
	String invited_num, invited_money, inviting_money;

    PopupWindow popupWindow;
    Context context;
    boolean isshow;
    public static String sign;

    private RelativeLayout rlMsg, rlTotalRevenue, rlBalance;
    private ImageView ivArw;
    private LinearLayout llGoGold;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mView = inflater.inflate(R.layout.fragment_main, container, false);

        context = getActivity();

        hasInit = true;

        initView();

        initData();

        addListeners();

		return mView;
	}

    private void initData() {
        // TODO Auto-generated method stub
        if (MainActivity.data != null) {
            setData(MainActivity.data);
        }

    }

	private void addListeners() {
		// TODO Auto-generated method stub
		itList.add(new ImageTextBean());
		itList.add(new ImageTextBean());
		itList.add(new ImageTextBean());
		itList.add(new ImageTextBean());

		adapter = new ImgeTextAdapter();
		adapter.setData(itList);
		gridView.setAdapter(adapter);

		// --------------
		list.add(new TaskBean());
		list.add(new TaskBean());
		list.add(new TaskBean());
		list.add(new TaskBean());

		taskAdapter = new TaskAdapter();
		taskAdapter.setData(list);
		listView.setAdapter(taskAdapter);
		listView.setOnItemClickListener(this);

        llGoGold.setOnClickListener(this);
        rlMsg.setOnClickListener(this);
        rlTotalRevenue.setOnClickListener(this);
        rlBalance.setOnClickListener(this);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.d("aa", "gridview" + arg2);
				final Intent intent;
				switch (arg2) {
				case 0: // 下载任务
					intent = new Intent(getContext(), DownloadActivity.class);
					startActivity(intent);
					break;

				case 1: // 签到
					intent = new Intent(getContext(), SignInActivity.class);
					startActivity(intent);
					break;

				case 2:
                    MarketUtils.launchAppDetail("com.qixiu.common.zhuojinniao",
                            "", getContext());
					break;
				case 3:

					intent = new Intent(getContext(), WithdrawOneActivity.class);
					startActivity(intent);

					break;

				}
			}
		});
	}

	private void initView() {

		gridView = (GridView) mView.findViewById(R.id.gridview);
		listView = (ListView) mView.findViewById(R.id.listview);
		// rl_layout1 = (RelativeLayout) mView.findViewById(R.id.rl_layout1);
		earnings = (TextView) mView.findViewById(R.id.earnings);
		balance = (TextView) mView.findViewById(R.id.balance);
		total_earnings = (TextView) mView.findViewById(R.id.total_earnings);

		recvMsg = (TextView) mView.findViewById(R.id.recvMsg);

        rlMsg = (RelativeLayout) mView.findViewById(R.id.rlMsg);
        ivArw = (ImageView) mView.findViewById(R.id.ivArw);

        llGoGold = (LinearLayout) mView.findViewById(R.id.llGoGold);

        rlBalance = (RelativeLayout) mView.findViewById(R.id.rlBalance);

        rlTotalRevenue = (RelativeLayout) mView.findViewById(R.id.rlTotalRevenue);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (hasInit)
			loadData();

		if (PreferenceManager.getFirst() == 0) {
			InitPopuWindow(mView);
			PreferenceManager.saveFirst(1);
		}

		profile();

	}

    private void setData(UserData data) {
        // TODO Auto-generated method stub

        if (!TextUtils.isEmpty(data.invited_num)) { // 邀请好友数量

            invited_num = data.invited_num;

        } else {

            invited_num = "0";
        }

        if (!TextUtils.isEmpty(data.invited_money)) { // 邀请获得金币

            invited_money = data.invited_money;

        } else {

            invited_num = "0";
        }

        if (!TextUtils.isEmpty(data.inviting_money)) {// 未到账的金币

            inviting_money = data.inviting_money;

        } else {

            invited_num = "0";
        }

        sign = data.continue_sgntimes;

    }

	public void profile() {

		UserMananger.profile(getContext(), new BaseCallListener() {

			@Override
			public void onSuccess(BaseResponse pResponse) {
				// TODO Auto-generated method stub

				ProfileResponse response = (ProfileResponse) pResponse;

				setProfileData(response.o);

			}

			@Override
			public void onFail(BaseResponse pResponse) {
				// TODO Auto-generated method stub

			}
		});

        UserMananger.getRanks(getContext(), new BaseCallListener() {

            @Override
            public void onSuccess(BaseResponse pResponse) {
                // TODO Auto-generated method stub
                recvStr = "";
                GetRanksResponse response = (GetRanksResponse) pResponse;
                for (int i = 0; i < response.o.toplist.size(); i++) {
                    recvStr = recvStr + response.o.toplist.get(i) + "        ";
                }
                recvMsg.setText(recvStr);

            }

            @Override
            public void onFail(BaseResponse pResponse) {
                // TODO Auto-generated method stub

            }
        });
	}

	private void setProfileData(ProfileData data) {
		// TODO Auto-generated method stub

		if (!TextUtils.isEmpty(data.today_profit)) {
			total = data.total_profit;
			earnings.setText("" + data.today_profit);
		} else {

			earnings.setText("暂无收益");
		}

		if (!TextUtils.isEmpty(data.credit)) {

			balance.setText(data.credit);
		} else {

			balance.setText("0");
		}

		if (!TextUtils.isEmpty(data.total_profit)) {

			total_earnings.setText(data.total_profit);

		} else {

			total_earnings.setText("0");
		}

		if (data.msg.equals("0")) {

			ivArw.setVisibility(View.GONE);

		} else if (data.msg.equals("1")) {

			ivArw.setVisibility(View.VISIBLE);
		}

	}

	// ----------------------------------------------------

	private void loadData() {

	}

	RelativeLayout loadingLayout;
	LoadingView loadingView;

	public void showLoading() {
		loadingLayout = (RelativeLayout) mView.findViewById(R.id.loading);
		if (loadingView == null) {
			loadingView = new LoadingView(getActivity());
			loadingLayout.addView(loadingView.getView());
		}
		loadingLayout.setVisibility(View.VISIBLE);
	}

	public void hideLoading() {
		if (loadingLayout != null)
			loadingLayout.setVisibility(View.GONE);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intent;

		switch (arg2) {
		case 0:// 收徒

			intent = new Intent(getContext(), InvitationActivity.class);
			intent.putExtra("invited_num", invited_num);
			intent.putExtra("invited_money", invited_money);
			intent.putExtra("inviting_money", inviting_money);
			startActivity(intent);

			break;

		case 1: // 排名

			intent = new Intent(getContext(), RankingActivity.class);
			startActivity(intent);

			break;
		case 2: // 任务明细
			intent = new Intent(getContext(), DetailActivity.class);
			startActivity(intent);
			break;
		case 3:// 新用户手册

			WebManager.startWebView(getActivity(), "帮助", WebManager.help(), 0);

			//Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
			break;
		}
	}

	/** 邀请码界面 */
	public void InitPopuWindow(final View v) {
		View view;
		final EditText Invitation_code;

		popupWindow = new PopupWindow(view = LayoutInflater.from(getActivity())
				.inflate(R.layout.popu_view, null), RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);

		popupWindow.setFocusable(true);

		popupWindow.setBackgroundDrawable(new BitmapDrawable());

		Invitation_code = (EditText) view.findViewById(R.id.Invitation_code);

		view.findViewById(R.id.qq).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Log.d("aa", "111111111111111111111111111111111");
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				// 2.调用hideSoftInputFromWindow方法隐藏软键盘
				imm.hideSoftInputFromWindow(arg0.getWindowToken(), 0); // 强制隐藏键盘
			}
		});

		view.findViewById(R.id.submit).setOnClickListener(

				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (Invitation_code.getText().toString().length() == 5) {
							UserMananger.referTicket(getContext(), Invitation_code
											.getText().toString().trim(),
									new BaseCallListener() {

										@Override
										public void onSuccess(BaseResponse pResponse) {
											// TODO Auto-generated
											// method stub
											Toast.makeText(getContext(), "已提交",
													Toast.LENGTH_SHORT).show();
											isshow = true;

											// profile();
											earnings.setText("20");
											balance.setText("20");
											total_earnings.setText("20");

											Log.d("aa", Invitation_code.getText()
													.toString().trim());
											popupWindow.dismiss();

										}

										@Override
										public void onFail(BaseResponse pResponse) {
											// TODO Auto-generated
											// method stub

										}
									});
						} else {
							Toast.makeText(getContext(), "请输入验证码", Toast.LENGTH_SHORT)
									.show();
						}

					}
				});

		// ------------------------------------
		view.findViewById(R.id.delete).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						isshow = true;
						popupWindow.dismiss();
					}
				});

		if (getActivity() != null && !getActivity().isFinishing()) {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
				}
			}, 100);

		}

	}

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rlMsg:
                intent = new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
                break;

            case R.id.llGoGold:
                intent = new Intent(getContext(), DownloadActivity.class);
                startActivity(intent);
                break;

            case R.id.rlBalance:
            case R.id.rlTotalRevenue:
                intent = new Intent(getContext(), WithdrawOneActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
