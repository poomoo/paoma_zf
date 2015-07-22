package com.example.paoma_zf.activity;

import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.ClearEditText;

public class FeedbackActivity extends BaseActivity {

	int num = 100;
	EditText editText_feedback_message;
	TextView textView_feedback_cannum;
	Button button_feedback;
	ClearEditText ClearEditText_feedback_lxfs;
	Zfnet zz;
	ZfApplication zfapp;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		init();
	}

	public void init() {
		zz = new Zfnet();
		zfapp = (ZfApplication) getApplication();
		editText_feedback_message = (EditText) findViewById(R.id.editText_feedback_message);
		textView_feedback_cannum = (TextView) findViewById(R.id.textView_feedback_cannum);
		ClearEditText_feedback_lxfs = (ClearEditText) findViewById(R.id.ClearEditText_feedback_lxfs);
		button_feedback = (Button) findViewById(R.id.button_feedback);
		button_feedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new Thread(new Runnable() {
					public void run() {

						try {

							zz.addfeedback(zfapp.getUserId(),
									editText_feedback_message.getText()
											.toString(),
									ClearEditText_feedback_lxfs.getText()
											.toString());
							Message message = new Message();
							message.what = 1;
							myHandler.sendMessage(message);

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				}).start();

			}
		});

		textView_feedback_cannum.setText("还能输入" + num + "字");

		editText_feedback_message.addTextChangedListener(new TextWatcher() {

			private CharSequence temp;
			private int selectionStart;
			private int selectionEnd;

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				temp = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int number = num - s.length();
				// textView_publication_cannum.setText("" + number);
				textView_feedback_cannum.setText("还能输入" + number + "字");

				selectionStart = editText_feedback_message.getSelectionStart();
				selectionEnd = editText_feedback_message.getSelectionEnd();
				if (temp.length() > num) {
					s.delete(selectionStart - 1, selectionEnd);
					int tempSelection = selectionEnd;
					editText_feedback_message.setText(s);
					editText_feedback_message.setSelection(tempSelection);// 设置光标在最后
				}

			}
		});
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				Toast.makeText(getApplicationContext(), "提交成功",
						Toast.LENGTH_SHORT).show();
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
