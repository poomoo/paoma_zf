package com.example.paoma_zf.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.adapter.LookhomeListviewAdapter;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.ListViewForScrollView;

public class LookhomeActivity extends BaseActivity {

	ListViewForScrollView lv_lookhome;
	ScrollView scrollView_lookhome;
	LinearLayout LinearLayout_riqi, LinearLayout_fangxing, LinearLayout_paixu,
			LinearLayout_quyu;
	private TextView tv_in, tv_out;
	SharedPreferences sp;
	String inday, outday, currPage, pageSize, roomType, houseType,
			orderByValue, orderType, cityName, areaName;
	List<Map<String, Object>> HouseList;
	Intent getCondition;

	SharedPreferences smit;
	SharedPreferences getaddress;
	public static LookhomeActivity instance = null;
	Zfnet zz;
	ZfApplication zfapp;
	ProgressDialog xh_pDialog;
	int total;

	// private LookhomeListviewAdapter listViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_lookhome);
		init();

	}

	public void init() {
		if (LoginActivity.instance != null) {
			LoginActivity.instance.finish();
		}
		instance = this;
		zz = new Zfnet();
		zfapp = (ZfApplication) getApplication();
		getCondition = new Intent();
		total = 0;
		sp = getSharedPreferences("date", Context.MODE_PRIVATE);

		SharedPreferences.Editor speditor = sp.edit();

		speditor.putString("dateIn", "");
		speditor.putString("dateOut", "");
		speditor.commit();

		getaddress = getSharedPreferences("address", Context.MODE_PRIVATE);

		SharedPreferences.Editor getaddresseditor = getaddress.edit();

		getaddresseditor.putString("cityName", "");
		getaddresseditor.putString("hadAreas", "");
		getaddresseditor.commit();

		smit = getSharedPreferences("searchcommit", Context.MODE_PRIVATE);
		SharedPreferences.Editor smiteditor = smit.edit();

		smiteditor.putString("roomtype", "");
		smiteditor.putString("housetype", "");
		smiteditor.commit();

		tv_in = (TextView) findViewById(R.id.tv_in);
		tv_out = (TextView) findViewById(R.id.tv_out);
		currPage = "1";
		pageSize = "15";
		roomType = "";
		houseType = "";
		orderByValue = "0";
		orderType = "2";
		cityName = "";
		areaName = "";
		HouseList = new ArrayList<Map<String, Object>>();
		LinearLayout_riqi = (LinearLayout) findViewById(R.id.LinearLayout_riqi);

		LinearLayout_riqi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LookhomeActivity.this,
						CalendarActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
			}
		});

		LinearLayout_fangxing = (LinearLayout) findViewById(R.id.LinearLayout_fangxing);
		LinearLayout_fangxing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LookhomeActivity.this,
						HomestyleDialog.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
			}
		});

		LinearLayout_paixu = (LinearLayout) findViewById(R.id.LinearLayout_paixu);
		LinearLayout_paixu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LookhomeActivity.this,
						SortstyleDialog.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
			}
		});

		LinearLayout_quyu = (LinearLayout) findViewById(R.id.LinearLayout_quyu);
		LinearLayout_quyu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LookhomeActivity.this,
						AddressDialog.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
			}
		});

		lv_lookhome = (ListViewForScrollView) findViewById(R.id.lv_lookhome);

		lv_lookhome.setOnItemClickListener(new Lvitemclicklistener());

		scrollView_lookhome = (ScrollView) findViewById(R.id.scrollView_lookhome);

		scrollView_lookhome.smoothScrollTo(0, 0);

	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(), HouseList,
						R.layout.style_lvlookhome, new String[] {
								"currentPrice", "name", "avgScore",
								"totalOrdersDay", "roomType", "addressDetails",
								"id", "headPic", "collect", "collectInfo" },
						new int[] { R.id.textView_stylelookhome_money,
								R.id.textView_stylelookhome_title,
								R.id.textView_stylelookhome_score,
								R.id.textView_stylelookhome_info,
								R.id.textView_stylelookhome_roomtype,
								R.id.textView_stylelookhome_Position,
								R.id.textView_stylelookhome_Id,
								R.id.imageView_stylelookhome_bg,
								R.id.textView_stylelookhome_collect,
								R.id.imageView_stylelookhome_collect });

				adapter.setViewBinder(new ViewBinder() {
					@Override
					public boolean setViewValue(View view, final Object data,
							final String str) {
						if (view instanceof ImageView && data instanceof Bitmap
								&& view.getTag().toString().equals("headPic")) {
							ImageView iv = (ImageView) view;
							iv.setImageBitmap((Bitmap) data);
							return true;
						}

						if (view instanceof ImageView
								&& view.getTag().toString().equals("iscollect")) {
							final ImageView iv = (ImageView) view;

							String[] temp = data.toString().split(",");
							final String id = temp[0];
							String collect = temp[1];
							System.out.println("collect" + collect);
							System.out.println("id" + id);
							if (collect.equals("true")) {
								iv.setBackgroundResource(R.drawable.ic_collection_down);
								iv.setTag("true");

							} else {
								iv.setBackgroundResource(R.drawable.ic_collection_up);
								iv.setTag("false");
							}
							iv.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									if (zfapp.getUserId().length() > 0) {
										int i = 0;
										if (v.getTag().equals("true")) {
											Toast.makeText(
													getApplicationContext(),
													"取消收藏", Toast.LENGTH_LONG)
													.show();

											iv.setBackgroundResource(R.drawable.ic_collection_up);
											iv.setTag("false");
											i = 1;

											new Thread(new Runnable() {
												public void run() {

													try {
														zz.addcollection(zfapp
																.getUserId(),
																id);
													} catch (Exception e1) {
														e1.printStackTrace();
													}

												}
											}).start();

										} else {
											Toast.makeText(
													getApplicationContext(),
													"收藏成功", Toast.LENGTH_LONG)
													.show();

											iv.setBackgroundResource(R.drawable.ic_collection_down);
											iv.setTag("true");

											new Thread(new Runnable() {
												public void run() {
													try {
														zz.addcollection(zfapp
																.getUserId(),
																id);
													} catch (Exception e1) {
														e1.printStackTrace();
													}
												}
											}).start();

										}
									} else {
										AlertDialog.Builder builder = new Builder(
												LookhomeActivity.this);
										builder.setMessage("登录后才能收藏");
										builder.setTitle("提示");
										builder.setPositiveButton("确认",
												new Dialog.OnClickListener() {
													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														dialog.dismiss();
													}
												});
										builder.create().show();
									}
								}
							});
							return true;
						}
						return false;
					}
				});

				lv_lookhome.setAdapter(adapter);
				xh_pDialog.cancel();
			}
			super.handleMessage(msg);
		}
	};

	class Lvitemclicklistener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			final TextView Id = (TextView) arg1
					.findViewById(R.id.textView_stylelookhome_Id);
			final TextView collect = (TextView) arg1
					.findViewById(R.id.textView_stylelookhome_collect);

			new Thread(new Runnable() {
				public void run() {
					try {
						if (zfapp.getUserId().length() > 0) {
							zz.addBrowserecord(zfapp.getUserId().toString(), Id
									.getText().toString());
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}).start();

			Intent intent = new Intent(LookhomeActivity.this,
					InformationActivity.class);
			intent.putExtra("Id", Id.getText().toString());
			intent.putExtra("collect", collect.getText().toString());
			startActivity(intent);
		}

	}

	private OnClickListener itemsOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (zfapp.getUserId().length() > 0) {
				final ImageView iv = (ImageView) v;
				final String id = "";
				if (v.getTag().equals("true")) {
					Toast.makeText(getApplicationContext(), "取消收藏",
							Toast.LENGTH_LONG).show();

					iv.setBackgroundResource(R.drawable.ic_collection_up);
					iv.setTag("false");

					new Thread(new Runnable() {
						public void run() {
							try {
								zz.addcollection(zfapp.getUserId(), id);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}).start();

				} else {
					Toast.makeText(getApplicationContext(), "收藏成功",
							Toast.LENGTH_LONG).show();

					iv.setBackgroundResource(R.drawable.ic_collection_down);
					iv.setTag("true");

					new Thread(new Runnable() {
						public void run() {
							try {
								zz.addcollection(zfapp.getUserId(), id);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}).start();

				}
			} else {
				AlertDialog.Builder builder = new Builder(LookhomeActivity.this);
				builder.setMessage("登录后才能收藏");
				builder.setTitle("提示");
				builder.setPositiveButton("确认", new Dialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.create().show();
			}
		}
	};

	@Override
	protected void onStart() {
		super.onStart();

		inday = sp.getString("dateIn", "");
		outday = sp.getString("dateOut", "");

		roomType = smit.getString("roomtype", "");
		houseType = smit.getString("housetype", "");

		orderByValue = smit.getString("orderByValue", "");
		orderType = smit.getString("orderType", "");

		cityName = getaddress.getString("cityName", "");
		areaName = getaddress.getString("hadAreas", "");

		if (!"".equals(inday)) {
			tv_in.setText(inday);
		}
		if (!"".equals(outday)) {
			tv_out.setText(outday);
		}

		if (total == 0) {
			Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
			ca.setTime(new Date()); // 设置时间为当前时间
			Date nowday = ca.getTime(); // 结果

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowdate = sdf.format(nowday);
			tv_in.setText(nowdate);
			tv_out.setText(nowdate);
		}

		total = total + 1;

		xh_pDialog = new ProgressDialog(LookhomeActivity.this);

		// 设置进度条风格，风格为圆形，旋转的
		xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		// 设置ProgressDialog 标题
		// xh_pDialog.setTitle("提示");

		// 设置ProgressDialog提示信息
		xh_pDialog.setMessage("请稍后....");

		// 设置ProgressDialog标题图标
		// xh_pDialog.setIcon(R.drawable.ic_launcher);

		// 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
		xh_pDialog.setIndeterminate(false);

		// 设置ProgressDialog 是否可以按退回键取消
		xh_pDialog.setCancelable(true);

		// 设置ProgressDialog 的一个Button

		// 让ProgressDialog显示
		xh_pDialog.show();

		new Thread(new Runnable() {
			public void run() {
				try {
					if (zfapp.getUserId().length() > 0) {
						HouseList = zz.getHouseList(zfapp.getUserId(), inday,
								outday, roomType, houseType, orderByValue,
								orderType, cityName, areaName,
								getApplicationContext());

					} else {
						HouseList = zz.getHouseList("", inday, outday,
								roomType, houseType, orderByValue, orderType,
								cityName, areaName, getApplicationContext());
					}

					Message message = new Message();
					message.what = 1;
					myHandler.sendMessage(message);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
