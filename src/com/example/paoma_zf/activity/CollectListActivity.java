package com.example.paoma_zf.activity;

import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.ListViewForScrollView;

public class CollectListActivity extends BaseActivity {

	List<Map<String, Object>> collectList;
	ListViewForScrollView listViewForScrollView_collectlist_collectlist;
	ScrollView scrollView_collectlist_scroll;
	Zfnet zz;
	ProgressDialog xh_pDialog;
	ZfApplication zfapp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_collectlist);
		init();

	}

	private void init() {
		listViewForScrollView_collectlist_collectlist = (ListViewForScrollView) findViewById(R.id.listViewForScrollView_collectlist_collectlist);
		listViewForScrollView_collectlist_collectlist
				.setOnItemClickListener(new Lvitemclicklistener());

		scrollView_collectlist_scroll = (ScrollView) findViewById(R.id.scrollView_collectlist_scroll);
		scrollView_collectlist_scroll.smoothScrollTo(0, 0);
		zz = new Zfnet();
		zfapp = (ZfApplication) getApplication();

		xh_pDialog = new ProgressDialog(CollectListActivity.this);
		xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		xh_pDialog.setMessage("请稍后....");
		xh_pDialog.setIndeterminate(false);
		// 设置ProgressDialog 是否可以按退回键取消
		xh_pDialog.setCancelable(true);
		// 让ProgressDialog显示
		xh_pDialog.show();

		new Thread(new Runnable() {
			public void run() {

				try {

					collectList = zz.getCollectlist(zfapp.getUserId(),
							getApplicationContext());

					if (collectList.size() > 0) {
						Message message = new Message();

						message.what = 1;

						myHandler.sendMessage(message);
					} else {
						xh_pDialog.cancel();
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}).start();
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			System.out.println("myHandler");
			if (msg.what == 1) {

				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(), collectList,
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

				// R.id.imageView_collection
				adapter.setViewBinder(new ViewBinder() {

					@Override
					public boolean setViewValue(View view, final Object data,
							final String str) {
						// TODO Auto-generated method stub

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
									// TODO Auto-generated method stub
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

														collectList = zz
																.getCollectlist(
																		zfapp.getUserId(),
																		getApplicationContext());

														Intent intent = new Intent(
																CollectListActivity.this,
																CollectListActivity.class);
														startActivity(intent);
														overridePendingTransition(
																0, 0);
														finish();
														// if
														// (collectList.size() >
														// 0) {
														// xh_pDialog.show();
														// Message message = new
														// Message();
														//
														// message.what = 1;
														//
														// myHandler2222.sendMessage(message);
														// } else {
														// xh_pDialog.cancel();
														// }
														//

													} catch (Exception e1) {
														// TODO Auto-generated
														// catch block
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

														collectList = zz
																.getCollectlist(
																		zfapp.getUserId(),
																		getApplicationContext());

														Intent intent = new Intent(
																CollectListActivity.this,
																CollectListActivity.class);
														startActivity(intent);
														overridePendingTransition(
																0, 0);
														finish();

														// if
														// (collectList.size() >
														// 0) {
														// xh_pDialog.show();
														// Message message = new
														// Message();
														//
														// message.what = 1;
														//
														// myHandler2222.sendMessage(message);
														// } else {
														// xh_pDialog.cancel();
														// }

													} catch (Exception e1) {
														// TODO Auto-generated
														// catch block
														e1.printStackTrace();
													}

												}
											}).start();

										}
									} else {
										AlertDialog.Builder builder = new Builder(
												CollectListActivity.this);
										builder.setMessage("登录后才能收藏");

										builder.setTitle("提示");

										builder.setPositiveButton("确认",
												new Dialog.OnClickListener() {

													@Override
													public void onClick(
															DialogInterface dialog,
															int which) {
														// TODO Auto-generated
														// method stub

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

				listViewForScrollView_collectlist_collectlist
						.setAdapter(adapter);
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}).start();

			Intent intent = new Intent(CollectListActivity.this,
					InformationActivity.class);
			intent.putExtra("Id", Id.getText().toString());
			intent.putExtra("collect", collect.getText().toString());
			startActivity(intent);
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			finish();

		}
		return super.onKeyDown(keyCode, event);
	}

	Handler myHandler2222 = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {
				Intent intent = new Intent(CollectListActivity.this,
						CollectListActivity.class);
				startActivity(intent);
				finish();

			}

			super.handleMessage(msg);
		}
	};

}
