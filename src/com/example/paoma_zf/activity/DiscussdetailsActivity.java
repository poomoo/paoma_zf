package com.example.paoma_zf.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.config.ZfConfig;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.ListViewForScrollView;
import com.example.paoma_zf.view.RoundImageView;

public class DiscussdetailsActivity extends Activity {

	List<Map<String, Object>> discussionInfo;
	List<Map<String, Object>> praiselist;
	List<Map<String, Object>> replylist;
	TextView textView_discussdetails_userName,
			textView_discussdetails_levelName,
			textView_discussdetails_cityName, textView_discussdetails_time,
			textView_discussdetails_content,
			textView_discussdetails_praiseTotal,
			textView_discussdetails_replyTotal,
			textView_discussdetails_praiseTotal2,
			textView_discussdetails_replyTotal2;

	RoundImageView roundImageView_discussdetails_pic;
	//
	EditText editText_discussdetails_reply;
	Button button_discussdetails_reply;
	//
	LinearLayout LinearLayout_discussdetails_parise,
			LinearLayout_discussdetails_reply,
			LinearLayout_discussdetails_pariseBTN,
			LinearLayout_discussdetails_replyBTN,
			LinearLayout_discussdetails_replywindow;

	ListViewForScrollView listViewForScrollView_discussdetails_parise,
			listViewForScrollView_discussdetails_reply;

	ImageView imageView_discussdetails_praiseBtN,
			imageView_discussdetails_replyBTN;

	AutoCompleteTextView AutoCompleteTextView_discussdetails_reply;
	Zfnet zfnet;
	ZfApplication Zfapp;
	ProgressDialog   xh_pDialog;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.activity_discussdetails);

		init();
		new Thread(new Runnable() {
			public void run() {

				try {

					// discussionInfo = getDiscussionInfo(getIntent()
					// .getStringExtra("dynamicId"));

					discussionInfo=zfnet.getDiscussionInfo(
							getIntent().getStringExtra("dynamicId"),
							getApplicationContext());
				
			

					if (discussionInfo.size() > 0) {
						Message message = new Message();
						message.what = 1;
						myHandler.sendMessage(message);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}).start();

		new Thread(new Runnable() {
			public void run() {

				try {

					// praiselist = getpariselist(getIntent().getStringExtra(
					// "dynamicId"));
					praiselist = zfnet.getpariselist(getIntent()
							.getStringExtra("dynamicId"));

					if (praiselist.size() > 0) {
						Message message = new Message();
						message.what = 2;
						myHandler.sendMessage(message);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}).start();

		new Thread(new Runnable() {
			public void run() {

				try {

					// replylist = getreplylist(getIntent().getStringExtra(
					// "dynamicId"));
//------------------------------------
					replylist = zfnet.getreplylist(getIntent().getStringExtra(
							"dynamicId"));


					if (replylist.size() > 0) {
						Message message = new Message();
						message.what = 3;
						myHandler.sendMessage(message);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}).start();

	}

	private void init() {

		zfnet = new Zfnet();

		Zfapp = (ZfApplication) getApplication();
		
		
        xh_pDialog = new ProgressDialog(DiscussdetailsActivity.this);  
        // 设置进度条风格，风格为圆形，旋转的  
        xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  
        // 设置ProgressDialog 标题  
//        xh_pDialog.setTitle("提示");  
        // 设置ProgressDialog提示信息  
        xh_pDialog.setMessage("请稍后....");  
        // 设置ProgressDialog标题图标  
//        xh_pDialog.setIcon(R.drawable.ic_launcher);  
        // 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确  
        xh_pDialog.setIndeterminate(false);  
        // 设置ProgressDialog 是否可以按退回键取消  
        xh_pDialog.setCancelable(true);  
 
        // 让ProgressDialog显示  
        xh_pDialog.show(); 
		

		textView_discussdetails_userName = (TextView) findViewById(R.id.textView_discussdetails_userName);

		textView_discussdetails_levelName = (TextView) findViewById(R.id.textView_discussdetails_levelName);

		textView_discussdetails_cityName = (TextView) findViewById(R.id.textView_discussdetails_cityName);

		textView_discussdetails_time = (TextView) findViewById(R.id.textView_discussdetails_time);

		textView_discussdetails_content = (TextView) findViewById(R.id.textView_discussdetails_content);

		textView_discussdetails_praiseTotal = (TextView) findViewById(R.id.textView_discussdetails_praiseTotal);

		textView_discussdetails_replyTotal = (TextView) findViewById(R.id.textView_discussdetails_replyTotal);

		textView_discussdetails_praiseTotal2 = (TextView) findViewById(R.id.textView_discussdetails_praiseTotal2);

		textView_discussdetails_replyTotal2 = (TextView) findViewById(R.id.textView_discussdetails_replyTotal2);

		roundImageView_discussdetails_pic = (RoundImageView) findViewById(R.id.roundImageView_discussdetails_pic);

		AutoCompleteTextView_discussdetails_reply = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView_discussdetails_reply);
		//
		button_discussdetails_reply = (Button) findViewById(R.id.button_discussdetails_reply);

		LinearLayout_discussdetails_parise = (LinearLayout) findViewById(R.id.LinearLayout_discussdetails_parise);

		LinearLayout_discussdetails_reply = (LinearLayout) findViewById(R.id.LinearLayout_discussdetails_reply);

		LinearLayout_discussdetails_pariseBTN = (LinearLayout) findViewById(R.id.LinearLayout_discussdetails_pariseBTN);

		LinearLayout_discussdetails_replyBTN = (LinearLayout) findViewById(R.id.LinearLayout_discussdetails_replyBTN);

		LinearLayout_discussdetails_replywindow = (LinearLayout) findViewById(R.id.LinearLayout_discussdetails_replywindow);

		listViewForScrollView_discussdetails_parise = (ListViewForScrollView) findViewById(R.id.listViewForScrollView_discussdetails_parise);

		listViewForScrollView_discussdetails_reply = (ListViewForScrollView) findViewById(R.id.listViewForScrollView_discussdetails_reply);

		LinearLayout_discussdetails_parise.setVisibility(View.VISIBLE);
		LinearLayout_discussdetails_reply.setVisibility(View.GONE);

		LinearLayout_discussdetails_pariseBTN
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						LinearLayout_discussdetails_parise
								.setVisibility(View.VISIBLE);
						LinearLayout_discussdetails_reply
								.setVisibility(View.GONE);

					}
				});

		LinearLayout_discussdetails_replyBTN
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						LinearLayout_discussdetails_parise
								.setVisibility(View.GONE);
						LinearLayout_discussdetails_reply
								.setVisibility(View.VISIBLE);
					}
				});

		imageView_discussdetails_praiseBtN = (ImageView) findViewById(R.id.imageView_discussdetails_praiseBtN);

		imageView_discussdetails_praiseBtN.setTag("dianzan");

		imageView_discussdetails_praiseBtN
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						new Thread(new Runnable() {
							public void run() {

								try {

									// addparise(getIntent().getStringExtra(
									// "dynamicId"), "5");
									zfnet.addparise(
											getIntent().getStringExtra(
													"dynamicId"),
											Zfapp.getUserId());

									Message message = new Message();
									message.what = 4;
									myHandler.sendMessage(message);
									
									
									praiselist = zfnet.getpariselist(getIntent()
											.getStringExtra("dynamicId"));

									if (praiselist.size() > 0) {
										Message parisemessage = new Message();
										message.what = 1;
										refreshHandler.sendMessage(parisemessage);
									}
									
						
							

								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
						}).start();

					}

				});

		imageView_discussdetails_replyBTN = (ImageView) findViewById(R.id.imageView_discussdetails_replyBTN);

		imageView_discussdetails_replyBTN.setTag("huifu");

		imageView_discussdetails_replyBTN
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						Message message = new Message();
						message.what = 5;
						myHandler.sendMessage(message);

					}
				});

		button_discussdetails_reply.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new Thread(new Runnable() {
					public void run() {

						try {

							// addComment(getIntent().getStringExtra(
							// "dynamicId"), "5",
							// AutoCompleteTextView_discussdetails_reply.getText().toString());
							zfnet.addComment(
									getIntent().getStringExtra("dynamicId"),
									Zfapp.getUserId(),
									AutoCompleteTextView_discussdetails_reply
											.getText().toString());
							Message message = new Message();
							message.what = 6;
							myHandler.sendMessage(message);
							
							replylist = zfnet.getreplylist(getIntent().getStringExtra(
									"dynamicId"));


							if (replylist.size() > 0) {
								Message Commentmessage = new Message();
								message.what = 2;
								refreshHandler.sendMessage(Commentmessage);
							}
							
			
					

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}).start();

			}
		});

	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				textView_discussdetails_userName.setText(discussionInfo.get(0)
						.get("userName").toString());

				textView_discussdetails_levelName.setText(discussionInfo.get(0)
						.get("levelName").toString());

				textView_discussdetails_cityName.setText(discussionInfo.get(0)
						.get("cityName").toString());

				textView_discussdetails_time.setText(discussionInfo.get(0)
						.get("insertDt").toString());

				textView_discussdetails_content.setText(discussionInfo.get(0)
						.get("content").toString());

				textView_discussdetails_praiseTotal.setText(discussionInfo
						.get(0).get("praiseTotal").toString());

				textView_discussdetails_praiseTotal2.setText(discussionInfo
						.get(0).get("praiseTotal").toString());

				textView_discussdetails_replyTotal.setText(discussionInfo
						.get(0).get("commentTotal").toString());

				textView_discussdetails_replyTotal2.setText(discussionInfo
						.get(0).get("commentTotal").toString());

				roundImageView_discussdetails_pic
						.setImageBitmap((Bitmap) discussionInfo.get(0).get(
								"headPic"));
				xh_pDialog.cancel();
			}

			if (msg.what == 2) {
				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(), praiselist,
						R.layout.style_lvpraise, new String[] { "userName" },
						new int[] { R.id.textView_lvpraise_disname,

						});

				listViewForScrollView_discussdetails_parise.setAdapter(adapter);
			}

			if (msg.what == 3) {

				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(), replylist,
						R.layout.style_lvreply, new String[] { "userName",
								"content" }, new int[] {
								R.id.textView_lvreply_disname,
								R.id.textView_lvreply_content

						});

				listViewForScrollView_discussdetails_reply.setAdapter(adapter);

			}

			if (msg.what == 4) {
				if (imageView_discussdetails_praiseBtN.getTag().equals(
						"dianzan")) {

					
//					Resources r = getApplicationContext().getResources();
//					InputStream is = r.openRawResource(R.drawable.ic_1280_dianzandown);
//					BitmapDrawable bmpDraw = new BitmapDrawable(is);
//					Bitmap bm = bmpDraw.getBitmap();
//					imageView_discussdetails_praiseBtN.setImageBitmap(bm);
					imageView_discussdetails_praiseBtN
							.setBackgroundResource(R.drawable.ic_1280_dianzandown);
					
					imageView_discussdetails_praiseBtN.setTag("quxiao");
				} else {
	
					imageView_discussdetails_praiseBtN
							.setBackgroundResource(R.drawable.ic_1280_dianzan);
					imageView_discussdetails_praiseBtN.setTag("dianzan");
				}
			}

			if (msg.what == 5) {
				if (imageView_discussdetails_replyBTN.getTag().equals("huifu")) {
					LinearLayout_discussdetails_replywindow
							.setVisibility(View.VISIBLE);
					imageView_discussdetails_replyBTN.setTag("quxiao");
				} else {
					LinearLayout_discussdetails_replywindow
							.setVisibility(View.GONE);
					imageView_discussdetails_replyBTN.setTag("huifu");
				}

			}

			if (msg.what == 6) {

				LinearLayout_discussdetails_replywindow
						.setVisibility(View.GONE);
				imageView_discussdetails_replyBTN.setTag("huifu");
				AutoCompleteTextView_discussdetails_reply.setText("");

			}

			super.handleMessage(msg);
		}
	};
	
	
	Handler refreshHandler = new Handler() {
		public void handleMessage(Message msg) {

	

			if (msg.what == 1) {
				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(), praiselist,
						R.layout.style_lvpraise, new String[] { "userName" },
						new int[] { R.id.textView_lvpraise_disname,

						});

				listViewForScrollView_discussdetails_parise.setAdapter(adapter);
			}

			if (msg.what == 2) {

				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(), replylist,
						R.layout.style_lvreply, new String[] { "userName",
								"content" }, new int[] {
								R.id.textView_lvreply_disname,
								R.id.textView_lvreply_content

						});

				listViewForScrollView_discussdetails_reply.setAdapter(adapter);

			}

		

		

			super.handleMessage(msg);
		}
	};

	
	
//	@Override
//	protected void onStart() {
//		// TODO Auto-generated method stub
//		super.onStart();
//
//		new Thread(new Runnable() {
//			public void run() {
//
//				try {
//					praiselist = zfnet.getpariselist(getIntent()
//							.getStringExtra("dynamicId"));
//
//					if (praiselist.size() > 0) {
//						Message message = new Message();
//						message.what = 2;
//						myHandler.sendMessage(message);
//					}
//				
//
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		}).start();
//
//	}
	
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
