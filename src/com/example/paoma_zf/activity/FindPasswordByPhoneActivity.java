package com.example.paoma_zf.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.config.ZfConfig;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.TimeCountUtil;

/**
 * 
 * @ClassName FindPasswordByPhoneActivity
 * @Description TODO 找回密码
 * @author 李苜菲
 * @date 2015-7-16 下午5:18:27
 */
public class FindPasswordByPhoneActivity extends BaseActivity {
	private EditText editText_phone, editText_identynum;
	private LinearLayout layout_next;
	private Button btn;

	private String phoneNum = "", identityNum = "";
	private ProgressDialog xh_pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpassword_phone);

		init();
	}

	private void init() {
		editText_phone = (EditText) findViewById(R.id.editText_findpassword_phone);
		editText_identynum = (EditText) findViewById(R.id.editText_findpassword_identitynum);
		btn = (Button) findViewById(R.id.button_findpassword_identitynum);
		layout_next = (LinearLayout) findViewById(R.id.layout_findpassword_next);
		editText_phone.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (s.toString().trim().length() == 11) {
					btn.setClickable(true);
					btn.setBackgroundResource(R.drawable.style_identy_button_frame);
				}
			}
		});

		btn.setOnClickListener(new theOnClickListener());
		btn.setClickable(false);
		btn.setBackgroundColor(Color.GRAY);
		layout_next.setOnClickListener(new theOnClickListener());

		xh_pDialog = new ProgressDialog(FindPasswordByPhoneActivity.this);
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
			// TODO 自动生成的方法存根
			switch (v.getId()) {
			case R.id.button_findpassword_identitynum:
				System.out.println("获取验证码");
				getIdentityNum();
				break;
			case R.id.layout_findpassword_next:
				next();
				break;
			}

		}
	}

	private void getIdentityNum() {
		phoneNum = editText_phone.getText().toString().trim();
		// if (0 == phoneNum.length()) {
		// AlertDialog.Builder builder = new Builder(
		// FindPasswordByPhoneActivity.this);
		// builder.setMessage("请输入手机号码");
		// builder.setTitle("提示");
		// builder.setPositiveButton("确认", new Dialog.OnClickListener() {
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// dialog.dismiss();
		// }
		// });
		// builder.create().show();
		// return;
		// }
		// if (11 != phoneNum.length()) {
		// AlertDialog.Builder builder = new Builder(
		// FindPasswordByPhoneActivity.this);
		// builder.setMessage("手机号码长度不对");
		// builder.setTitle("提示");
		// builder.setPositiveButton("确认", new Dialog.OnClickListener() {
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// dialog.dismiss();
		// }
		// });
		// builder.create().show();
		// return;
		// }
		TimeCountUtil timeCountUtil = new TimeCountUtil(FindPasswordByPhoneActivity.this, 60000, 1000, btn);
		timeCountUtil.start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message message = new Message();
				if (Zfnet.getIdentitynumber(phoneNum, ZfConfig.getUserList))
					message.what = 1;
				else
					message.what = 2;
				myHandler.sendMessage(message);
			}
		}).start();
	}

	public void next() {
		identityNum = editText_identynum.getText().toString().trim();
		xh_pDialog.show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message msg = new Message();
				try {
					if (Zfnet.checkcode(phoneNum, identityNum, ZfConfig.getUserList))
						msg.arg1 = 1;
					else
						msg.arg1 = 2;
				} catch (Exception e) {
				} finally {
					msg.what = 3;
					myHandler.sendMessage(msg);
				}
			}
		}).start();
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1)
				Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_LONG).show();
			if (msg.what == 2)
				Toast.makeText(getApplicationContext(), "发送失败", Toast.LENGTH_LONG).show();

			if (msg.what == 3) {
				xh_pDialog.cancel();
				if (msg.arg1 != 1) {
					Toast.makeText(FindPasswordByPhoneActivity.this, "验证码不正确！", Toast.LENGTH_SHORT).show();
					return;
				}

				Intent next = new Intent(FindPasswordByPhoneActivity.this, ResetPasswordActivity.class);
				next.putExtra("tel", phoneNum);
				startActivity(next);
				finish();
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
