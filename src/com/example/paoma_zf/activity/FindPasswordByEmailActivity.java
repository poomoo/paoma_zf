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
 * @Description TODO �һ�����
 * @author ���ٷ�
 * @date 2015-7-16 ����5:18:27
 */
public class FindPasswordByEmailActivity extends BaseActivity {
	private EditText editText_eamil;
	private LinearLayout layout_confirm;

	private String email = "";
	private String result = "";
	private ProgressDialog xh_pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_findpassword_email);

		init();
	}

	private void init() {
		editText_eamil = (EditText) findViewById(R.id.editText_findpassword_email);
		layout_confirm = (LinearLayout) findViewById(R.id.layout_findpassword_email_confirm);

		layout_confirm.setOnClickListener(new theOnClickListener());

		xh_pDialog = new ProgressDialog(FindPasswordByEmailActivity.this);
		// ���ý�������񣬷��ΪԲ�Σ���ת��
		xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// ����ProgressDialog��ʾ��Ϣ
		xh_pDialog.setMessage("���Ժ�....");
		// ����ProgressDialog �Ľ������Ƿ���ȷ false ���ǲ�����Ϊ����ȷ
		xh_pDialog.setIndeterminate(false);
		// ����ProgressDialog �Ƿ���԰��˻ؼ�ȡ��
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
			builder.setMessage("����������");
			builder.setTitle("��ʾ");
			builder.setPositiveButton("ȷ��", new Dialog.OnClickListener() {
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
			builder.setMessage("�����ʽ����ȷ");
			builder.setTitle("��ʾ");
			builder.setPositiveButton("ȷ��", new Dialog.OnClickListener() {
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
				Toast.makeText(getApplicationContext(), "�ʼ����ͳɹ�",
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
