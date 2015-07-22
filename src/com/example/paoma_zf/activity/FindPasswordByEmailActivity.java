package com.example.paoma_zf.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

/**
 * 
 * @ClassName FindPasswordByPhoneActivity
 * @Description TODO 找回密码
 * @author 李苜菲
 * @date 2015-7-16 下午5:18:27
 */
public class FindPasswordByEmailActivity extends BaseActivity {
	private EditText editText_eamil;
	private LinearLayout layout_confirm;

	private String email = "";
	private String result = "";
	private ProgressDialog xh_pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpassword_email);

		init();
	}

	private void init() {
		editText_eamil = (EditText) findViewById(R.id.editText_findpassword_email);
		layout_confirm = (LinearLayout) findViewById(R.id.layout_findpassword_email_confirm);

		layout_confirm.setOnClickListener(new theOnClickListener());

		xh_pDialog = new ProgressDialog(FindPasswordByEmailActivity.this);
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
			confirm();
		}
	}

	public void confirm() {
		email = editText_eamil.getText().toString().trim();
		if (0 == email.length()) {
			AlertDialog.Builder builder = new Builder(
					FindPasswordByEmailActivity.this);
			builder.setMessage("请输入邮箱");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			return;
		}
		Pattern p = Pattern
				.compile("^[a-zA-Z0-9]+([\\_|\\-|\\.]?[a-zA-Z0-9])*\\@[a-zA-Z0-9]+([\\_|\\-|\\.]?[a-zA-Z0-9])*\\.[a-zA-Z]{2,3}$");
		Matcher m = p.matcher(email);
		if (!m.find()) {
			AlertDialog.Builder builder = new Builder(
					FindPasswordByEmailActivity.this);
			builder.setMessage("邮箱格式不正确");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			return;
		}

		xh_pDialog.show();
		new Thread(new Runnable() {
			@Override
			public void run() {
				Message msg = new Message();
				try {
					result = Zfnet.sendEmail(email, ZfConfig.getUserList);
				} catch (Exception e) {
				} finally {
					msg.what = 1;
					myHandler.sendMessage(msg);
				}
			}
		}).start();
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				xh_pDialog.cancel();
				String[] re = result.split("\\|");
				if (re[0].equals("false")) {
					Toast.makeText(getApplicationContext(), re[1],
							Toast.LENGTH_LONG).show();
					return;
				}
				Toast.makeText(getApplicationContext(), "邮件发送成功",
						Toast.LENGTH_LONG).show();
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
