package com.example.paoma_zf.activity;

import java.util.ArrayList;
import java.util.List;

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
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.config.ZfConfig;

/**
 * 
 * @ClassName AuthenticationActivity
 * @Description TODO 立即注册
 * @author 李苜菲
 * @date 2015-7-15 上午9:15:06
 */
public class AuthenticationActivity extends BaseActivity {

	EditText editText_authentication_name, editText_authentication_mail,
			editText_authentication_idnum, editText_authentication_address;
	TextView textView_authentication_regist;
	Spinner spinner;
	// RadioGroup radioGroup;

	private ProgressDialog xh_pDialog;

	private String name = "", sex = "0", mail = "", idNum = "", address = "",
			phoneNum = "", password = "", licenseNum = "", levelId = "";

	private static final String[] strsex = { "男", "女" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.activity_authenticaiton);

		Intent in = getIntent();
		phoneNum = in.getStringExtra("phonenum");
		password = in.getStringExtra("password");
		licenseNum = in.getStringExtra("licensenum");
		levelId = in.getStringExtra("levelid");

		init();

	}

	public void init() {
		editText_authentication_name = (EditText) findViewById(R.id.editText_authentication_name);
		editText_authentication_mail = (EditText) findViewById(R.id.editText_authentication_mail);
		editText_authentication_idnum = (EditText) findViewById(R.id.editText_authentication_idnum);
		editText_authentication_address = (EditText) findViewById(R.id.editText_authentication_address);
		textView_authentication_regist = (TextView) findViewById(R.id.textView_authentication_regist);
		spinner = (Spinner) findViewById(R.id.spinner_authentication);

		xh_pDialog = new ProgressDialog(AuthenticationActivity.this);

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
		xh_pDialog.setCancelable(false);

		xh_pDialog.setCanceledOnTouchOutside(false);

		ArrayAdapter<String> ada = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, strsex);
		ada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(ada);
		spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView tv = (TextView) arg1;
				tv.setTextSize(14);
				tv.setTextColor(Color.parseColor("#b2b2b2"));
				if (arg2 == 1)
					sex = "0";
				else if (arg2 == 2)
					sex = "1";

				arg0.setVisibility(View.VISIBLE);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		// radioGroup = (RadioGroup)
		// findViewById(R.id.radioGroup_authentication);

		// radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		// @Override
		// public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// // 获取变更后的选中项的ID
		// int radioButtonId = arg0.getCheckedRadioButtonId();
		// if (radioButtonId == R.id.radioButton_authentication_man)
		// sex = "0";
		// else
		// sex = "1";
		// Log.i("radio", "sex:" + sex);
		// }
		// });

		textView_authentication_regist
				.setOnClickListener(new AuthenticationClickListener());
	}

	class AuthenticationClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (checklogin()) {
				// 让ProgressDialog显示
				xh_pDialog.show();
				new Thread(new Runnable() {
					@Override
					public void run() {
						Message message = new Message();
						try {
							if (Registration(phoneNum, name, sex, idNum,
									password, levelId, licenseNum, mail,
									address, ZfConfig.getUserList))
								message.what = 1;
							else
								message.what = 2;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							message.what = 3;
						}
						myHandler.sendMessage(message);
					}
				}).start();
			}
		}
	}

	public boolean checklogin() {
		name = editText_authentication_name.getText().toString().trim();
		if (name.equals("")) {
			AlertDialog.Builder builder = new Builder(
					AuthenticationActivity.this);
			builder.setMessage("请输入姓名");
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

		if (sex.equals("")) {
			AlertDialog.Builder builder = new Builder(
					AuthenticationActivity.this);
			builder.setMessage("请选择性别");
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

		idNum = editText_authentication_idnum.getText().toString().trim();
		if (idNum.equals("")) {
			AlertDialog.Builder builder = new Builder(
					AuthenticationActivity.this);
			builder.setMessage("请输入身份证号");
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
		mail = editText_authentication_mail.getText().toString().trim();
		address = editText_authentication_address.getText().toString().trim();
		return true;
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			xh_pDialog.dismiss();

			// 成功
			if (msg.what == 1) {
				RegistrationActivity.instance.finish();
				Intent out = new Intent(AuthenticationActivity.this,
						LoginActivity.class);
				startActivity(out);
				Toast.makeText(getApplicationContext(), "注册成功",
						Toast.LENGTH_SHORT).show();
				finish();
			}
			// 失败
			else if (msg.what == 2 || msg.what == 3) {
				AlertDialog.Builder builder = new Builder(
						AuthenticationActivity.this);
				builder.setMessage("注册失败");
				builder.setTitle("提示");
				builder.setPositiveButton("确认", new Dialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.create().show();
			}
			super.handleMessage(msg);
		}
	};

	public Boolean Registration(String userName, String realName, String sex,
			String idcardnum, String password, String levelId,
			String licenseNo, String email, String address, String url)
			throws Exception {

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("method", "1001");
		jsonObject.put("levelId", levelId);
		jsonObject.put("password", password);
		jsonObject.put("userName", userName);

		jsonObject.put("sex", sex);
		jsonObject.put("address", address);
		jsonObject.put("idCardNum", idcardnum);
		jsonObject.put("licenseNo", licenseNo);
		jsonObject.put("email", email);
		jsonObject.put("realName", realName);
		Log.i("Registration", jsonObject + "URL:" + url);

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));

		String result = null;
		// 获取HttpClient对象
		HttpClient httpClient = new DefaultHttpClient();
		// 新建HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		if (nameValuePair != null) {
			// 设置字符集
			HttpEntity entity = new UrlEncodedFormEntity(nameValuePair,
					HTTP.UTF_8);
			// 设置参数实体
			httpPost.setEntity(entity);
		}

		/*
		 * // 连接超时 httpClient.getParams().setParameter(
		 * CoreConnectionPNames.CONNECTION_TIMEOUT, 3000); // 请求超时
		 * httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
		 * 3000);
		 */
		// 获取HttpResponse实例
		HttpResponse httpResp = httpClient.execute(httpPost);
		// 判断是够请求成功
		if (httpResp.getStatusLine().getStatusCode() == 200) {
			// 获取返回的数据
			result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
		} else {
			Log.i("HttpPost", "HttpPost方式请求失败");
		}

		Log.i("Authentication result", result);

		JSONObject jsonObject2 = new JSONObject(result.toString());

		String rscode = jsonObject2.getString("rsCode");
		if (rscode.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
