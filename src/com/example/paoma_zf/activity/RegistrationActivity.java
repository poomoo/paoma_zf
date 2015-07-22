package com.example.paoma_zf.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.config.ZfConfig;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.TimeCountUtil;

public class RegistrationActivity extends BaseActivity {

	LinearLayout LinearLayout_registration_phone,
			LinearLayout_registration_mail, linearLayout_spinner;
	EditText editText_registration_phonenum,
			editText_registartion_numverificationphone,
			editText_registartion_phonepassword,
			editText_registartion_phonepassword_again,
			editText_registartion_licensenum;
	Button button_registration_verificationphone;
	TextView textView_registration_next, textView_registration_isUsed;
	Spinner spinner;

	String uesrtype;
	String RegistrationMsg;
	String checkCodeMsg;
	String phonenum = "", identicode = "", password1 = "", password2 = "",
			licensenum = "", levelId = "";

	private String levelName;

	public static RegistrationActivity instance = null;
	private List<Map<String, Object>> data = null;
	private boolean phoneIsUsed = false;// false--不可用 true--可用
	private ProgressDialog xh_pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.activity_registration_new);

		instance = this;

		init();
	}

	public void init() {
		editText_registration_phonenum = (EditText) findViewById(R.id.editText_registration_phonenum_new);
		editText_registartion_numverificationphone = (EditText) findViewById(R.id.editText_registartion_numverificationphone_new);
		editText_registartion_phonepassword = (EditText) findViewById(R.id.editText_registartion_phonepassword_new);
		editText_registartion_phonepassword_again = (EditText) findViewById(R.id.editText_registartion_phonepasswordagain);
		editText_registartion_licensenum = (EditText) findViewById(R.id.editText_registartion_licensenum);
		textView_registration_next = (TextView) findViewById(R.id.textView_registartion_next);
		textView_registration_isUsed = (TextView) findViewById(R.id.textView_registration_isUsed);
		spinner = (Spinner) findViewById(R.id.spinner_registration);
		button_registration_verificationphone = (Button) findViewById(R.id.button_registration_verificationphone_new);

		editText_registration_phonenum
				.setOnFocusChangeListener(new OnFocusChangeListener() {
					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						if (hasFocus) {
							editText_registration_phonenum.setText("");
							textView_registration_isUsed.setText("");
						}
						if (!hasFocus
								&& editText_registration_phonenum.getText()
										.toString().trim().length() == 11)
							new Thread(new Runnable() {
								@Override
								public void run() {
									Message message = new Message();
									try {
										phoneIsUsed = Zfnet.checkPhoneIsUsed(
												editText_registration_phonenum
														.getText().toString()
														.trim(),
												ZfConfig.getUserList);
										message.what = 4;
									} catch (Exception e) {
										message.what = 4;
										message.arg1 = 1;
									} finally {
										myHandler.sendMessage(message);
									}
								}
							}).start();
					}
				});

		textView_registration_next.setTag("login");
		textView_registration_next
				.setOnClickListener(new RegistartionClickListener());

		button_registration_verificationphone.setTag("getphonenum");
		button_registration_verificationphone
				.setOnClickListener(new RegistartionClickListener());
		button_registration_verificationphone.setClickable(false);
		button_registration_verificationphone.setBackgroundColor(Color.GRAY);
		button_registration_verificationphone.setTextColor(Color.BLACK);

		linearLayout_spinner = (LinearLayout) findViewById(R.id.layout_registration_spinner);
		linearLayout_spinner.setTag("spinner");
		linearLayout_spinner
				.setOnClickListener(new RegistartionClickListener());

		uesrtype = "";
		RegistrationMsg = "";
		checkCodeMsg = "";

		// 测试
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message message = new Message();
				try {
					data = getspinner();
					message.what = 2;
				} catch (Exception e) {
					message.what = 2;
					message.arg1 = 1;
				} finally {
					myHandler.sendMessage(message);
				}
			}
		}).start();

		xh_pDialog = new ProgressDialog(RegistrationActivity.this);
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

	class RegistartionClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v.getTag().equals("login")) {
				if (checklogin()) {
					xh_pDialog.show();
					new Thread(new Runnable() {
						@Override
						public void run() {
							Message message = new Message();
							try {
								if (Zfnet.checkcode(phonenum, identicode,
										ZfConfig.getUserList)) {
									if (licensenum.length() > 0) {
										if (Zfnet.checkLicenseNum(licensenum,
												ZfConfig.getUserList))
											message.arg1 = 1;
										else
											message.arg1 = 3;
									} else
										message.arg1 = 1;
								} else
									message.arg1 = 2;

							} catch (Exception e) {
								message.arg1 = 4;
							} finally {
								message.what = 5;
								myHandler.sendMessage(message);
							}
						}
					}).start();
				}
			}

			if (v.getTag().equals("getphonenum")) {
				phonenum = editText_registration_phonenum.getText().toString()
						.trim();
				if (!phonenum.equals("")) {
					TimeCountUtil timeCountUtil = new TimeCountUtil(
							RegistrationActivity.this, 60000, 1000,
							button_registration_verificationphone);
					timeCountUtil.start();
					new Thread(new Runnable() {
						public void run() {
							Message message = new Message();
							try {
								if (Zfnet.getIdentitynumber(phonenum,
										ZfConfig.getUserList)) {
									message.arg1 = 1;
								}
							} catch (Exception e1) {
								message.arg1 = 2;
							} finally {
								message.what = 1;
								myHandler.sendMessage(message);
							}
						}
					}).start();
				} else {
					Toast.makeText(getApplicationContext(), "请输入电话号码",
							Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	public boolean checklogin() {
		Log.i("checklogin", "进入checklogin！");
		if (!phonenum.equals("") && !phoneIsUsed) {
			AlertDialog.Builder builder = new Builder(RegistrationActivity.this);
			builder.setMessage("该手机已注册，请重新输入！");
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
		phonenum = editText_registration_phonenum.getText().toString().trim();
		if (phonenum.equals("")) {
			AlertDialog.Builder builder = new Builder(RegistrationActivity.this);
			builder.setMessage("请输入手机号码");
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

		identicode = editText_registartion_numverificationphone.getText()
				.toString().trim();
		if (identicode.equals("")) {
			AlertDialog.Builder builder = new Builder(RegistrationActivity.this);
			builder.setMessage("请输入 验证码");
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
		}

		password1 = editText_registartion_phonepassword.getText().toString()
				.trim();
		if (password1.equals("")) {
			AlertDialog.Builder builder = new Builder(RegistrationActivity.this);
			builder.setMessage("请输入 密码");
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
		}

		password2 = editText_registartion_phonepassword_again.getText()
				.toString().trim();
		if (password2.equals("")) {
			AlertDialog.Builder builder = new Builder(RegistrationActivity.this);
			builder.setMessage("请重复输入 密码");
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
		}

		if (!password1.equals(password2)) {
			AlertDialog.Builder builder = new Builder(RegistrationActivity.this);
			builder.setMessage("两次输入的密码不一样，请重新输入");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					editText_registartion_phonepassword.setFocusable(true);
					editText_registartion_phonepassword
							.setFocusableInTouchMode(true);
					editText_registartion_phonepassword.requestFocus();
					editText_registartion_phonepassword.setText("");
					editText_registartion_phonepassword_again.setText("");
				}
			});
			builder.create().show();

			return false;
		}

		if (editText_registartion_licensenum.isFocusable()) {
			licensenum = editText_registartion_licensenum.getText().toString()
					.trim();
			if (licensenum.equals("")) {
				AlertDialog.Builder builder = new Builder(
						RegistrationActivity.this);
				builder.setMessage("请输入许可号");
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
			}
		}

		return true;
	}

	public List<Map<String, Object>> getspinner() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(ZfConfig.getUserLevelList);

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("method", "2001");
		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));

		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

		// TODO 状态处理 500 200
		HttpResponse httpResponse = httpClient.execute(httpPost);
		StringBuilder builder = new StringBuilder();
		BufferedReader bufferedReader2 = new BufferedReader(
				new InputStreamReader(httpResponse.getEntity().getContent()));
		for (String s = bufferedReader2.readLine(); s != null; s = bufferedReader2
				.readLine()) {
			builder.append(s);
		}

		JSONObject result = new JSONObject(builder.toString());// 转换为JSONObject

		JSONArray nameList = result.getJSONArray("resultList");// 获取JSONArray
		int length = nameList.length();

		Log.i("Registration userlevellist lenth", Integer.toString(length));

		data = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < length; i++) {// 遍历JSONArray
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("levelName", nameList.getJSONObject(i).get("levelName"));
			map.put("levelId", nameList.getJSONObject(i).get("levelId"));
			data.add(map);

			Log.i("Registration userlevellist",
					nameList.getJSONObject(i).get("levelName").toString());
			Log.i("Registration userlevellist",
					nameList.getJSONObject(i).get("levelId").toString());
		}
		return data;
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				if (msg.arg1 == 1)
					Toast.makeText(getApplicationContext(), "发送成功",
							Toast.LENGTH_LONG).show();
				else
					Toast.makeText(getApplicationContext(), "发送失败",
							Toast.LENGTH_LONG).show();
				button_registration_verificationphone.setClickable(false);
				button_registration_verificationphone
						.setBackgroundColor(Color.GRAY);
			}
			if (msg.what == 2) {
				Log.i("Registration", "msg.arg1:" + msg.arg1 + "");
				if (msg.arg1 == 1) {
					Toast.makeText(RegistrationActivity.this, "获取用户级别列表失败！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				int len = data.size();
				String[] level = new String[len];
				for (int i = 0; i < len; i++) {
					level[i] = data.get(i).get("levelName").toString();
				}
				levelName = level[0];
				if (levelName.equals("合伙人")) {
					editText_registartion_licensenum.setFocusable(false);
					editText_registartion_licensenum.setHint("合伙人不需要许可号");
				} else {
					editText_registartion_licensenum.setFocusable(true);
					editText_registartion_licensenum.setHint("许可号");
				}

				ArrayAdapter<String> ada = new ArrayAdapter<String>(
						RegistrationActivity.this,
						android.R.layout.simple_spinner_item, level);
				ada.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
				spinner.setAdapter(ada);
				spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						TextView tv = (TextView) arg1;
						tv.setTextSize(15);
						tv.setTextColor(Color.parseColor("#b2b2b2"));
						levelId = data.get(arg2).get("levelId").toString();
						levelName = data.get(arg2).get("levelName").toString();
						arg0.setVisibility(View.VISIBLE);

						if (levelName.equals("合伙人")) {
							editText_registartion_licensenum
									.setFocusable(false);
							editText_registartion_licensenum
									.setText("合伙人不需要许可号");
							licensenum = "";
						} else {
							editText_registartion_licensenum.setFocusable(true);
							editText_registartion_licensenum
									.setFocusableInTouchMode(true);
							editText_registartion_licensenum.setHint("许可号");
							new Thread(new Runnable() {
								Message message = new Message();

								public void run() {
									try {
										licensenum = Zfnet
												.getLicenseNumber(ZfConfig.getUserList);
										message.what = 3;
									} catch (Exception e) {
										message.what = 3;
										message.arg1 = 1;
									} finally {
										myHandler.sendMessage(message);
									}
								}
							}).start();
						}
					}

					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
			}
			if (msg.what == 3) {
				if (msg.arg1 == 1) {
					Toast.makeText(RegistrationActivity.this, "获取随机许可号失败！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				editText_registartion_licensenum.setText(licensenum);
			}
			if (msg.what == 4) {
				if (msg.arg1 == 1) {
					Toast.makeText(RegistrationActivity.this, "手机号校验失败！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (!phoneIsUsed) {
					textView_registration_isUsed.setText("*该手机已注册");
				} else {
					button_registration_verificationphone.setClickable(true);
					button_registration_verificationphone
							.setBackgroundResource(R.drawable.style_identy_button_frame);
					button_registration_verificationphone.setTextColor(Color
							.parseColor("#0079ff"));
				}
			}
			if (msg.what == 5) {
				xh_pDialog.dismiss();
				if (msg.arg1 == 4) {
					Toast.makeText(RegistrationActivity.this, "通讯失败！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (msg.arg1 == 2) {
					Toast.makeText(RegistrationActivity.this, "验证码不正确！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (msg.arg1 == 3) {
					Toast.makeText(RegistrationActivity.this, "许可号不正确！",
							Toast.LENGTH_SHORT).show();
					return;
				}
				Intent next = new Intent(RegistrationActivity.this,
						AuthenticationActivity.class);
				next.putExtra("phonenum", phonenum);
				next.putExtra("password", password1);
				next.putExtra("licensenum", licensenum);
				next.putExtra("levelid", levelId);
				startActivity(next);
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
