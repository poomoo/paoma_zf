package com.example.paoma_zf.activity;

import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.view.RoundImageView;

public class MythingActivity extends Activity {

	RoundImageView imageView_mything_uesr;
	TextView textView_mything_uesrname, textView_mything_uesrtype,
			textView_mything_userscores, textView_mything_login_relogin;
	LinearLayout linearLayout_mything_coupons, linearLayout_mything_collection,
			linearLayout_mything_recentbrowse, LinearLayout_mything_call,
			LinearLayout_mything_fenxiang, LinearLayout_mything_feedback,
			LinearLayout_mything_aboutus, LinearLayout_mything_message,
			LinearLayout_mything_orderformlist, linearLyaout_mything_admin;

	RelativeLayout relativeLayout_mything_cancellation;
	ZfApplication Zfapp;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mything);
		init();

	}

	private void init() {
		LinearLayout_mything_feedback = (LinearLayout) findViewById(R.id.LinearLayout_mything_feedback);
		LinearLayout_mything_feedback.setTag("feedback");
		LinearLayout_mything_feedback
				.setOnClickListener(new mythingclicklisten());

		LinearLayout_mything_aboutus = (LinearLayout) findViewById(R.id.LinearLayout_mything_aboutus);
		LinearLayout_mything_aboutus.setTag("aboutus");
		LinearLayout_mything_aboutus
				.setOnClickListener(new mythingclicklisten());

		imageView_mything_uesr = (RoundImageView) findViewById(R.id.imageView_mything_uesr);
		imageView_mything_uesr.setTag("photo");
		imageView_mything_uesr.setOnClickListener(new mythingclicklisten());

		LinearLayout_mything_call = (LinearLayout) findViewById(R.id.LinearLayout_mything_call);
		LinearLayout_mything_call.setTag("call");
		LinearLayout_mything_call.setOnClickListener(new mythingclicklisten());

		textView_mything_uesrname = (TextView) findViewById(R.id.textView_mything_uesrname);
		textView_mything_uesrtype = (TextView) findViewById(R.id.textView_mything_uesrtype);
		textView_mything_userscores = (TextView) findViewById(R.id.textView_mything_userscores);
		// textView_mything_uesrname.setOnClickListener(new
		// mythingclicklisten());
		// textView_mything_uesrname.setTag("userInfo");

		textView_mything_login_relogin = (TextView) findViewById(R.id.textView_mything_login_relogin);

		linearLayout_mything_coupons = (LinearLayout) findViewById(R.id.linearLayout_mything_coupons);

		linearLayout_mything_collection = (LinearLayout) findViewById(R.id.linearLayout_mything_collection);
		linearLayout_mything_collection.setTag("collect");
		linearLayout_mything_collection
				.setOnClickListener(new mythingclicklisten());

		linearLayout_mything_recentbrowse = (LinearLayout) findViewById(R.id.linearLayout_mything_recentbrowse);
		linearLayout_mything_recentbrowse.setTag("recentbrowse");
		linearLayout_mything_recentbrowse
				.setOnClickListener(new mythingclicklisten());

		LinearLayout_mything_fenxiang = (LinearLayout) findViewById(R.id.LinearLayout_mything_fenxiang);
		LinearLayout_mything_fenxiang.setTag("fenxiang");
		LinearLayout_mything_fenxiang
				.setOnClickListener(new mythingclicklisten());

		LinearLayout_mything_orderformlist = (LinearLayout) findViewById(R.id.LinearLayout_mything_orderformlist);
		LinearLayout_mything_orderformlist.setTag("orderform");
		LinearLayout_mything_orderformlist
				.setOnClickListener(new mythingclicklisten());

		LinearLayout_mything_message = (LinearLayout) findViewById(R.id.LinearLayout_mything_message);
		LinearLayout_mything_message.setTag("message");
		LinearLayout_mything_message
				.setOnClickListener(new mythingclicklisten());

		relativeLayout_mything_cancellation = (RelativeLayout) findViewById(R.id.relativeLayout_mything_cancellation);
		relativeLayout_mything_cancellation
				.setOnClickListener(new mythingclicklisten());

		Zfapp = (ZfApplication) getApplication();
		imageView_mything_uesr.setImageBitmap(Zfapp.getBm());
		if (Zfapp.getUserName().toString().trim().equals("")) {
			textView_mything_login_relogin.setText("登录");
			relativeLayout_mything_cancellation.setTag("login");
			textView_mything_uesrname.setText("未登录");
			textView_mything_uesrtype.setText("游客");
			textView_mything_userscores.setText("积分：0分");
		}
		if (!Zfapp.getUserName().toString().trim().equals("")) {
			System.out.println("name" + Zfapp.getUserName().toString());
			textView_mything_login_relogin.setText("退 出 登 录");
			relativeLayout_mything_cancellation.setTag("exit");
			textView_mything_uesrname.setText(Zfapp.getUserName().toString());
			textView_mything_uesrtype.setText(Zfapp.getLevelName());
			if (Zfapp.getLevelName().toString().trim().equals("管家")) {
				linearLyaout_mything_admin = (LinearLayout) findViewById(R.id.LinearLayout_mything_admin);
				linearLyaout_mything_admin.setVisibility(View.VISIBLE);
				linearLyaout_mything_admin.setTag("admin");
				linearLyaout_mything_admin
						.setOnClickListener(new mythingclicklisten());
			}
			textView_mything_userscores.setText("积分：0分");
		}
	}

	class mythingclicklisten implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v.getTag().equals("orderform")) {
				if (checklogin()) {
					Intent i = new Intent();
					i.setClass(getApplicationContext(),
							OrderformlistActivity.class);
					startActivity(i);
				}
			}
			if (v.getTag().equals("collect")) {
				if (checklogin()) {
					Intent i = new Intent();
					i.setClass(getApplicationContext(),
							CollectListActivity.class);
					startActivity(i);
				}
			}

			if (v.getTag().equals("message")) {
				if (checklogin()) {
					Intent i = new Intent();
					i.setClass(getApplicationContext(),
							MessageListActivity.class);
					startActivity(i);
				}
			}

			if (v.getTag().equals("photo")) {
				if (checklogin()) {
					Intent i = new Intent();
					i.setClass(MythingActivity.this, MyInfoActivity.class);
					startActivityForResult(i, 101);

					// Intent i = new Intent();
					// i.setClass(getApplicationContext(), PhotoDialog.class);
					// startActivityForResult(i, 101);
				}
			}
			if (v.getTag().equals("login")) {
				Intent i = new Intent();
				i.setClass(getApplicationContext(), LoginActivity.class);
				startActivity(i);
				finish();
			}

			if (v.getTag().equals("exit")) {
				AlertDialog.Builder builder = new Builder(MythingActivity.this);
				builder.setMessage("确认退出？");
				builder.setTitle("提示");
				builder.setNegativeButton("取消", new Dialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.setPositiveButton("确认", new Dialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();

						Intent i = new Intent();
						i.setClass(getApplicationContext(),
								HomepageActivity.class);
						InputStream is = getResources().openRawResource(
								R.drawable.ic_launcher);
						Bitmap mBitmap = BitmapFactory.decodeStream(is);
						Zfapp.setLevelId("");
						Zfapp.setUserName("");
						Zfapp.setUserId("");
						Zfapp.setBm(mBitmap);
						startActivity(i);
					}
				});
				builder.create().show();

			}
			if (v.getTag().equals("recentbrowse")) {
				if (checklogin()) {
					Intent i = new Intent();
					i.setClass(getApplicationContext(),
							BrowserecordActivity.class);
					startActivity(i);
				}
			}

			if (v.getTag().equals("fenxiang")) {
				AlertDialog.Builder builder = new Builder(MythingActivity.this);
				builder.setMessage("此项功能正在开发中");
				builder.setTitle("提示");
				builder.setPositiveButton("确认", new Dialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.create().show();
			}

			if (v.getTag().equals("call")) {
				Intent i = new Intent();
				i.setAction(Intent.ACTION_CALL);
				i.setData(Uri.parse("tel:4006910851"));
				startActivity(i);
			}

			if (v.getTag().equals("aboutus")) {
				Intent i = new Intent();
				i.setClass(getApplicationContext(), AboutusActivity.class);
				startActivity(i);
			}

			if (v.getTag().equals("feedback")) {
				if (checklogin()) {
					Intent i = new Intent();
					i.setClass(getApplicationContext(), FeedbackActivity.class);
					startActivity(i);
				}
			}

			if (v.getTag().equals("admin")) {
				Intent i = new Intent();
				i.setClass(MythingActivity.this, HousekeeperActivity.class);
				startActivity(i);
			}

			// if (v.getTag().equals("userInfo")) {
			// if (!Zfapp.getUserName().toString().trim().equals("")) {
			// Intent i = new Intent();
			// i.setClass(MythingActivity.this, MyInfoActivity.class);
			// i.putExtra("id", Zfapp.getUserId());
			// i.putExtra("name", Zfapp.getUserName());
			// i.putExtra("mail", Zfapp.getEmail());
			// // i.putExtra("address", Zfapp.get);
			// startActivity(i);
			// } else {
			// AlertDialog.Builder builder = new Builder(
			// MythingActivity.this);
			// builder.setMessage("请先登录");
			// builder.setTitle("提示");
			// builder.setPositiveButton("确认",
			// new Dialog.OnClickListener() {
			// @Override
			// public void onClick(DialogInterface dialog,
			// int which) {
			// // TODO Auto-generated method stub
			// dialog.dismiss();
			// }
			// });
			// builder.create().show();
			// }
			//
			// }
		}
	}

	public Boolean checklogin() {
		if (Zfapp.getUserName().toString().trim().equals("")) {
			AlertDialog.Builder builder = new Builder(MythingActivity.this);
			builder.setMessage("请先登录");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			builder.create().show();
			return false;
		} else {

			return true;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 101 && resultCode == 101) {
			if (data.getBooleanExtra("isTrue", false)) {
				Zfapp = (ZfApplication) getApplication();
				imageView_mything_uesr.setImageBitmap(Zfapp.getBm());
				textView_mything_uesrname.setText(Zfapp.getUserName());
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
