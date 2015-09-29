package com.example.paoma_zf.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;

public class HomepageActivity extends BaseActivity {

	RelativeLayout RelativeLayout_homepage_login;
	RelativeLayout RelativeLayout_homepage_lookhome;
	RelativeLayout RelativeLayout_homepage_housekeeper;
	// RelativeLayout RelativeLayout_first_shopping;
	ImageView imageView_homepage_shopping, imageView_homepage_help;
	private long mTime = 0;
	public static HomepageActivity instance = null;
	ZfApplication Zfapp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
		instance = this;

		Zfapp = (ZfApplication) getApplication();

		imageView_homepage_help = (ImageView) findViewById(R.id.imageView_homepage_help);

		imageView_homepage_help.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClass(HomepageActivity.this, AboutusActivity.class);
				startActivity(i);
			}
		});

		RelativeLayout_homepage_login = (RelativeLayout) findViewById(R.id.RelativeLayout_homepage_login);
		RelativeLayout_homepage_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Zfapp.getUserId().toString().equals("")) {
					Intent i = new Intent();
					i.setClass(HomepageActivity.this, LoginActivity.class);
					startActivity(i);
				} else {

					Intent intent = new Intent();
					intent.setClass(HomepageActivity.this,
							LookhomeActivity.class);
					startActivity(intent);
					// AlertDialog.Builder builder = new Builder(
					// HomepageActivity.this);
					// builder.setMessage("已登录");
					// builder.setTitle("提示");
					// builder.setPositiveButton("确认",
					// new Dialog.OnClickListener() {
					// @Override
					// public void onClick(DialogInterface dialog,
					// int which) {
					// dialog.dismiss();
					// }
					// });
					// builder.create().show();
				}
			}
		});

		RelativeLayout_homepage_lookhome = (RelativeLayout) findViewById(R.id.RelativeLayout_homepage_lookhome);
		RelativeLayout_homepage_lookhome
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent i = new Intent();
						i.setClass(HomepageActivity.this,
								LookhomeActivity.class);
						startActivity(i);
					}
				});

		imageView_homepage_shopping = (ImageView) findViewById(R.id.imageView_homepage_shopping);
		imageView_homepage_shopping.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				checklogin();
			}
		});

		RelativeLayout_homepage_housekeeper = (RelativeLayout) findViewById(R.id.RelativeLayout_homepage_housekeeper);
		RelativeLayout_homepage_housekeeper
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						checktype();
					}
				});

	}

	public Boolean checklogin() {
		if (Zfapp.getUserName().toString().trim().equals("")) {
			AlertDialog.Builder builder = new Builder(HomepageActivity.this);
			builder.setMessage("请先登录");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			return false;
		} else {
			Intent i = new Intent();
			i.setClass(HomepageActivity.this, OrderformlistActivity.class);
			startActivity(i);
			return true;
		}
	}

	public Boolean checktype() {
		if (!Zfapp.getType().toString().trim().equals("2")) {
			AlertDialog.Builder builder = new Builder(HomepageActivity.this);
			builder.setMessage("请先登录/或您不是管家");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			return false;
		} else {
			Intent i = new Intent();
			i.setClass(HomepageActivity.this, HousekeeperActivity.class);
			startActivity(i);
			return true;
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long time = System.currentTimeMillis();
			if (time - mTime <= 3000) {
				// ZfApplication.getInstance().getOperateDataBase().deleteOneOrAllBoard(-1);
				finish();
				return true;
			}
			Toast.makeText(HomepageActivity.this, "再按一次推出应用",
					Toast.LENGTH_SHORT).show();
			mTime = time;
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
