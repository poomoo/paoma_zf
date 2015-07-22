package com.example.paoma_zf.activity;

import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.ClearEditText;
import com.example.paoma_zf.view.ListViewForScrollView;
import com.example.paoma_zf.view.RoundImageView;

@SuppressLint("ResourceAsColor")
public class HousekeeperActivity extends Activity {

	List<Map<String, Object>> housekeeperlist;
	List<Map<String, Object>> discussionlist;
	ListViewForScrollView listViewForScrollView_housekeeper_rank,
			listViewForScrollView_housekeeper_discussion;

	ScrollView scrollView_housekeeper_rank, scrollView_housekeeper_discussion;
	RelativeLayout RelativeLayout_housekeeper_rank,
			RelativeLayout_housekeeper_rankcolor,
			RelativeLayout_housekeeper_discussion,
			RelativeLayout_housekeeper_discussioncolor;
	LinearLayout linearLayout_housekeeper_rank,
			LinearLayout_housekeeper_comment,
			linearLayout_housekeeper_discussion;
	AutoCompleteTextView AutoCompleteTextView_housekeeper_comment;
	Button button_housekeeper_comment;
	ImageView imageView_housekeeper_comment, imageView_housekeeper_return;

	ClearEditText ClearEditText_housekeeper_comment;
	Zfnet zfnet;
	ProgressDialog xh_pDialog;
	ZfApplication zfapp;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_housekeeper);

		init();

	}

	private void init() {

		zfnet = new Zfnet();
		zfapp = (ZfApplication) getApplication();

		listViewForScrollView_housekeeper_rank = (ListViewForScrollView) findViewById(R.id.listViewForScrollView_housekeeper_rank);

		listViewForScrollView_housekeeper_discussion = (ListViewForScrollView) findViewById(R.id.listViewForScrollView_housekeeper_discussion);

		RelativeLayout_housekeeper_rank = (RelativeLayout) findViewById(R.id.RelativeLayout_housekeeper_rank);

		RelativeLayout_housekeeper_rankcolor = (RelativeLayout) findViewById(R.id.RelativeLayout_housekeeper_rankcolor);

		RelativeLayout_housekeeper_discussion = (RelativeLayout) findViewById(R.id.RelativeLayout_housekeeper_discussion);

		RelativeLayout_housekeeper_discussioncolor = (RelativeLayout) findViewById(R.id.RelativeLayout_housekeeper_discussioncolor);

		scrollView_housekeeper_rank = (ScrollView) findViewById(R.id.scrollView_housekeeper_rank);

		scrollView_housekeeper_discussion = (ScrollView) findViewById(R.id.scrollView_housekeeper_discussion);

		linearLayout_housekeeper_rank = (LinearLayout) findViewById(R.id.linearLayout_housekeeper_rank);

		linearLayout_housekeeper_discussion = (LinearLayout) findViewById(R.id.linearLayout_housekeeper_discussion);

		AutoCompleteTextView_housekeeper_comment = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView_housekeeper_comment);

		ClearEditText_housekeeper_comment = (ClearEditText) findViewById(R.id.ClearEditText_housekeeper_comment);

		listViewForScrollView_housekeeper_rank
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub

						TextView managerId = (TextView) arg1
								.findViewById(R.id.textView_lvhouesekeeper_managerId);
						Intent intent = new Intent(HousekeeperActivity.this,
								HousekeeperintroductionActivity.class);
						intent.putExtra("managerId", managerId.getText()
								.toString());
						startActivity(intent);
					}
				});

		RelativeLayout_housekeeper_rank
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						// RelativeLayout_housekeeper_rankcolor.setBackgroundColor(0);
						RelativeLayout_housekeeper_rankcolor
								.setBackgroundColor(R.color.lightblue);
						RelativeLayout_housekeeper_discussioncolor
								.setBackgroundColor(0);
						// RelativeLayout_housekeeper_discussioncolor.setBackgroundColor(R.color.white);
						linearLayout_housekeeper_rank
								.setVisibility(View.VISIBLE);
						linearLayout_housekeeper_discussion
								.setVisibility(View.GONE);

					}
				});

		RelativeLayout_housekeeper_discussion
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						RelativeLayout_housekeeper_rankcolor
								.setBackgroundColor(0);
						// RelativeLayout_housekeeper_rankcolor.setBackgroundColor(R.color.white);
						// RelativeLayout_housekeeper_discussioncolor.setBackgroundColor(0);
						RelativeLayout_housekeeper_discussioncolor
								.setBackgroundColor(R.color.lightblue);
						linearLayout_housekeeper_rank.setVisibility(View.GONE);
						linearLayout_housekeeper_discussion
								.setVisibility(View.VISIBLE);

					}
				});

		listViewForScrollView_housekeeper_discussion
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub

						ImageView dianzan = (ImageView) arg1
								.findViewById(R.id.imageView_lvhousekeeper_disdianzan);

						ImageView huifu = (ImageView) arg1
								.findViewById(R.id.imageView_lvhousekeeper_dishuifu);
						TextView dynamicId = (TextView) arg1
								.findViewById(R.id.textView_lvhousekeeper_disId);
						String dynamicIdss = dynamicId.getText().toString();

						Intent i = new Intent();
						i.putExtra("dynamicId", dynamicIdss);
						i.setClass(getApplicationContext(),
								DiscussdetailsActivity.class);
						startActivity(i);

					}
				});

		// LinearLayout_housekeeper_comment = (LinearLayout)
		// findViewById(R.id.LinearLayout_housekeeper_comment);
		// LinearLayout_housekeeper_comment.setTag("close");

		imageView_housekeeper_comment = (ImageView) findViewById(R.id.imageView_housekeeper_comment);
		imageView_housekeeper_comment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Toast.makeText(getApplicationContext(), "怎么查找",
						Toast.LENGTH_SHORT).show();

			}
		});
		imageView_housekeeper_return = (ImageView) findViewById(R.id.imageView_housekeeper_return);

		imageView_housekeeper_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		button_housekeeper_comment = (Button) findViewById(R.id.button_housekeeper_comment);

		button_housekeeper_comment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new Thread(new Runnable() {
					public void run() {

						try {

							zfnet.addCommentDiscussion(zfapp.getUserId(),
									ClearEditText_housekeeper_comment.getText()
											.toString());

							Message message = new Message();
							message.what = 3;
							myHandler.sendMessage(message);

							discussionlist = zfnet
									.getDiscussionList(getApplicationContext());

							if (discussionlist.size() > 0) {

								Message messagess = new Message();
								messagess.what = 2;
								myHandler222.sendMessage(messagess);
							}

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}).start();

			}
		});

		xh_pDialog = new ProgressDialog(HousekeeperActivity.this);
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

		// 让ProgressDialog显示
		xh_pDialog.show();

	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(),
						housekeeperlist,
						R.layout.style_lvhousekeeper_rank,
						new String[] { "managerName", "avgHousekeeperScore",
								"houseName", "myIntro", "managerId", "headIcon" },
						new int[] { R.id.textView_lvhouesekeeper_name,
								R.id.textView_lvhouesekeeper_socer,
								R.id.textView_lvhouesekeeper_home,
								R.id.textView_lvhouesekeeper_monologue,
								R.id.textView_lvhouesekeeper_managerId,
								R.id.roundImageView_lvhouesekeeper_img

						});

				adapter.setViewBinder(new ViewBinder() {

					@Override
					public boolean setViewValue(View view, Object data,
							String str) {
						// TODO Auto-generated method stub

						if (view instanceof RoundImageView
								&& data instanceof Bitmap) {
							RoundImageView iv = (RoundImageView) view;
							Bitmap ss = (Bitmap) data;
							iv.setImageBitmap(ss);

							return true;
						} else
							return false;
					}

				});

				listViewForScrollView_housekeeper_rank.setAdapter(adapter);
				xh_pDialog.cancel();
			}

			if (msg.what == 2) {
				SimpleAdapter adapterdic = new SimpleAdapter(
						getApplicationContext(), discussionlist,
						R.layout.style_lvhousekeeper_discussion, new String[] {
								"userName", "userId", "dynamicId", "insertDt",
								"content", "levelName", "praiseTotal",
								"commentTotal", "headPic" }, new int[] {
								R.id.textView_lvhousekeeper_disname,
								R.id.textView_lvhousekeeper_disNameId,
								R.id.textView_lvhousekeeper_disId,
								R.id.textView_lvhousekeeper_distime,
								R.id.textView_lvhousekeeper_content,
								R.id.textView_lvhousekeeper_distype,
								R.id.textView_lvhousekeeper_disdianzancount,
								R.id.textView_lvhousekeeper_dishuifucount,
								R.id.roundImageView_lvhousekeeper_dispic });

				adapterdic.setViewBinder(new ViewBinder() {

					@Override
					public boolean setViewValue(View view, Object data,
							String str) {
						// TODO Auto-generated method stub

						if (view instanceof RoundImageView
								&& data instanceof Bitmap) {
							RoundImageView iv = (RoundImageView) view;

							Bitmap ss = (Bitmap) data;
							iv.setImageBitmap(ss);

							return true;
						}
						else {
							return false;
						}
					}

				});
				listViewForScrollView_housekeeper_discussion
						.setAdapter(adapterdic);

			}

			if (msg.what == 3) {
				AutoCompleteTextView_housekeeper_comment.setText("");
			}

			super.handleMessage(msg);
		}
	};

	Handler myHandler222 = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 2) {
				SimpleAdapter adapterdic = new SimpleAdapter(
						getApplicationContext(), discussionlist,
						R.layout.style_lvhousekeeper_discussion, new String[] {
								"userName", "userId", "dynamicId", "insertDt",
								"content", "levelName", "praiseTotal",
								"commentTotal", "headPic" }, new int[] {
								R.id.textView_lvhousekeeper_disname,
								R.id.textView_lvhousekeeper_disNameId,
								R.id.textView_lvhousekeeper_disId,
								R.id.textView_lvhousekeeper_distime,
								R.id.textView_lvhousekeeper_content,
								R.id.textView_lvhousekeeper_distype,
								R.id.textView_lvhousekeeper_disdianzancount,
								R.id.textView_lvhousekeeper_dishuifucount,
								R.id.roundImageView_lvhousekeeper_dispic });

				adapterdic.setViewBinder(new ViewBinder() {

					@Override
					public boolean setViewValue(View view, Object data,
							String str) {
						// TODO Auto-generated method stub

						if (view instanceof RoundImageView
								&& data instanceof Bitmap) {
							RoundImageView iv = (RoundImageView) view;

							Bitmap ss = (Bitmap) data;
							//

							//

							iv.setImageBitmap(ss);

							return true;
						}

						else {
							return false;
						}
					}

				});
				listViewForScrollView_housekeeper_discussion
						.setAdapter(adapterdic);

			}

			super.handleMessage(msg);
		}
	};

	@Override
	protected void onStart() {
		super.onStart();

		new Thread(new Runnable() {
			public void run() {

				try {
					housekeeperlist = zfnet
							.getHouseKeeper(getApplicationContext());
					discussionlist = zfnet
							.getDiscussionList(getApplicationContext());

					if (housekeeperlist.size() > 0) {
						Message message = new Message();
						message.what = 1;
						myHandler.sendMessage(message);
					}

					if (discussionlist.size() > 0) {
						Message message = new Message();
						message.what = 2;
						myHandler.sendMessage(message);
					}

				} catch (Exception e1) {
				}

			}
		}).start();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
