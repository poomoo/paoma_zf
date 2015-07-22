package com.example.paoma_zf.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.config.ZfConfig;
import com.example.paoma_zf.net.Zfnet;

public class ResetPasswordActivity extends BaseActivity {
	private EditText editText_password1, editText_password2;
	private LinearLayout layout;
	private String password1 = "", password2 = "", tel = "";
	private ProgressDialog xh_pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resetpassword);

		tel = getIntent().getStringExtra("tel");

		init();
	}

	private void init() {
		editText_password1 = (EditText) findViewById(R.id.editText_resetpassword_password1);
		editText_password2 = (EditText) findViewById(R.id.editText_resetpassword_password2);
		layout = (LinearLayout) findViewById(R.id.layout_resetpassword_confirm);

		layout.setOnClickListener(new theOnClickListener());

		xh_pDialog = new ProgressDialog(ResetPasswordActivity.this);
		// 设置进度条风格，风格为圆形，旋转的
		xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// 设置ProgressDialog提示信息
		xh_pDialog.setMessage("请稍后....");
		// 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
		xh_pDialog.setIndeterminate(false);
		// 设置ProgressDialog 是否可以按退回键取消
		xh_pDialog.setCancelable(false);
		xh_pDialog.setCanceledOnTouchOutside(false);
	}

	public class theOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (checkInfo()) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						Message msg = new Message();
						try {
							if (Zfnet.resetpassword(tel, password1,
									ZfConfig.getUserList))
								msg.arg1 = 1;
							else
								msg.arg1 = 2;

						} catch (Exception e) {
						} finally {
							msg.what = 1;
							myHandler.sendMessage(msg);
						}
					}
				}).start();
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	private boolean checkInfo() {
		password1 = editText_password1.getText().toString().trim();
		password2 = editText_password2.getText().toString().trim();

		if (0 == password1.length()) {
			AlertDialog.Builder builder = new Builder(
					ResetPasswordActivity.this);
			builder.setMessage("请输入密码");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			return false;
		}

		if (0 == password2.length()) {
			AlertDialog.Builder builder = new Builder(
					ResetPasswordActivity.this);
			builder.setMessage("请输入确定密码");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			return false;
		}

		if (!password1.equals(password2)) {
			AlertDialog.Builder builder = new Builder(
					ResetPasswordActivity.this);
			builder.setMessage("两次输入的密码不一致，请重新输入!");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					editText_password1.setText("");
					editText_password2.setText("");
					editText_password1.setFocusable(true);
					editText_password1.setFocusableInTouchMode(true);
					editText_password1.requestFocus();
				}
			});
			builder.create().show();
			return false;
		}
		return true;
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {
				xh_pDialog.cancel();
				if (msg.arg1 != 1) {
					Toast.makeText(ResetPasswordActivity.this, "修改密码失败！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Toast.makeText(ResetPasswordActivity.this, "修改密码成功！",
						Toast.LENGTH_SHORT).show();
				finish();
			}

			super.handleMessage(msg);
		}
	};
}
