package com.example.paoma_zf.activity;

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
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.config.ZfConfig;
import com.example.paoma_zf.net.Zfnet;

public class LoginActivity extends BaseActivity {

	EditText editText_login_username, editText_login_password;
	TextView textView_login_Registration, textView_login_Forgetpassword;
	RelativeLayout relativeLayout_login;

	public static LoginActivity instance = null;
	public static String json;
	String rsCode = "";
	String msgg = "";
	List<Map<String, Object>> UserList;
	ZfApplication Zfapp;
	ProgressDialog xh_pDialog;

	private SharedPreferences sp;
	private Editor editor = null;
	private String name = "", password = "";
	private Login_PopupWindow login_PopupWindow = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.activity_login);
		sp = getSharedPreferences("login", Context.MODE_PRIVATE);
		Log.i("LoginSp", sp.getString("name", "123"));
		editor = sp.edit();
		name = sp.getString("name", "");
		password = sp.getString("password", "");

		init();
	}

	public void init() {
		instance = this;
		Zfapp = (ZfApplication) getApplication();
		editText_login_username = (EditText) findViewById(R.id.editText_login_username);
		editText_login_password = (EditText) findViewById(R.id.editText_login_password);

		relativeLayout_login = (RelativeLayout) findViewById(R.id.relativeLayout_login);

		textView_login_Registration = (TextView) findViewById(R.id.textView_login_Registration);
		textView_login_Forgetpassword = (TextView) findViewById(R.id.textView_login_Forgetpassword);

		relativeLayout_login.setTag("login");
		relativeLayout_login.setOnClickListener(new Loginclicklisten());

		textView_login_Registration.setTag("Registration");
		textView_login_Registration.setOnClickListener(new Loginclicklisten());

		textView_login_Forgetpassword.setTag("Forgetpassword");
		textView_login_Forgetpassword
				.setOnClickListener(new Loginclicklisten());

		if (!name.equals("") && !password.equals("")) {
			editText_login_username.setText(name);
			editText_login_password.setText(password);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	class Loginclicklisten implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v.getTag().equals("login")) {
				name = editText_login_username.getText().toString().trim();
				password = editText_login_password.getText().toString().trim();
				if (name.equals("")) {

					AlertDialog.Builder builder = new Builder(
							LoginActivity.this);
					builder.setMessage("�������û���");
					builder.setTitle("��ʾ");
					builder.setPositiveButton("ȷ��",
							new Dialog.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
					builder.create().show();

					return;
				}

				if (password.equals("")) {
					AlertDialog.Builder builder = new Builder(
							LoginActivity.this);
					builder.setMessage("����������");
					builder.setTitle("��ʾ");
					builder.setPositiveButton("ȷ��",
							new Dialog.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
					builder.create().show();

					return;
				}

				editor.putString("name", name);
				editor.putString("password", password);
				editor.commit();

				xh_pDialog = new ProgressDialog(LoginActivity.this);

				// ���ý�������񣬷��ΪԲ�Σ���ת��
				xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

				// ����ProgressDialog ����
				// xh_pDialog.setTitle("��ʾ");

				// ����ProgressDialog��ʾ��Ϣ
				xh_pDialog.setMessage("��¼��....");

				// ����ProgressDialog����ͼ��
				// xh_pDialog.setIcon(R.drawable.ic_launcher);

				// ����ProgressDialog �Ľ������Ƿ���ȷ false ���ǲ�����Ϊ����ȷ
				xh_pDialog.setIndeterminate(false);

				// ����ProgressDialog �Ƿ���԰��˻ؼ�ȡ��
				xh_pDialog.setCancelable(false);
				xh_pDialog.setCanceledOnTouchOutside(false);

				// ����ProgressDialog ��һ��Button

				// ��ProgressDialog��ʾ
				xh_pDialog.show();

				new Thread(new Runnable() {
					public void run() {

						try {
							Message message = new Message();

							Zfnet netcon = new Zfnet();
							String UserId = netcon.login(
									editText_login_username.getText()
											.toString().trim(),
									editText_login_password.getText()
											.toString().trim(),
									ZfConfig.getUserList);
							Log.i("Login", "UserId:" + UserId);

							if (!UserId.equals("0")) {
								UserList = netcon.getUserInfo(UserId,
										ZfConfig.getUserList,
										getApplicationContext());
								Log.i("Login", "UserList:" + UserList);
								if (UserList.size() > 0) {
									Zfapp.setUserId(UserList.get(0)
											.get("userId").toString());

									Zfapp.setUserName(UserList.get(0)
											.get("userName").toString());
									Zfapp.setBm((Bitmap) UserList.get(0).get(
											"headIcon"));
									Zfapp.setLevelId(UserList.get(0)
											.get("levelId").toString());
									Zfapp.setLevelName(UserList.get(0)
											.get("levelName").toString());
									Zfapp.setEmail(UserList.get(0).get("email")
											.toString());
									Zfapp.setTel(UserList.get(0).get("tel")
											.toString());
								}
								message.what = 1;
								myHandler.sendMessage(message);
							} else {
								message.what = 0;
								myHandler.sendMessage(message);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}).start();
			}

			if (v.getTag().equals("Registration")) {
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, RegistrationActivity.class);
				startActivity(intent);
			}
			if (v.getTag().equals("Forgetpassword")) {
				// ʵ����SelectPicPopupWindow
				login_PopupWindow = new Login_PopupWindow(LoginActivity.this,
						itemsOnClick);
				// ��ʾ����
				login_PopupWindow.showAtLocation(LoginActivity.this
						.findViewById(R.id.activity_login_layout),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // ����layout��PopupWindow����ʾ��λ��
				return;
			}
		}
	}

	// Ϊ��������ʵ�ּ�����
	private OnClickListener itemsOnClick = new OnClickListener() {

		@Override
		public void onClick(View view) {
			login_PopupWindow.dismiss();
			switch (view.getId()) {

			case R.id.popup_login_byphone:
				Intent out = new Intent(LoginActivity.this,FindPasswordByPhoneActivity.class);
				startActivity(out);
				break;

			case R.id.popup_login_bymail:
				Intent out1 = new Intent(LoginActivity.this,FindPasswordByEmailActivity.class);
				startActivity(out1);
				break;
			}
		}
	};

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				xh_pDialog.cancel();
				Toast.makeText(getApplicationContext(), "��¼�ɹ�",
						Toast.LENGTH_LONG).show();
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this, LookhomeActivity.class);
				startActivity(intent);
				finish();

			} else {
				xh_pDialog.cancel();
				Toast.makeText(LoginActivity.this, "�û������������",
						Toast.LENGTH_LONG).show();

			}
			super.handleMessage(msg);
		}
	};
}
