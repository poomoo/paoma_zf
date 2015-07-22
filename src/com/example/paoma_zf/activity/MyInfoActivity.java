package com.example.paoma_zf.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.paoma_zf.R;

public class MyInfoActivity extends BaseActivity {
	private LinearLayout layout_name, layout_phone, layout_mail;
	private TextView textView_name, textView_phone, textView_mail;
	private String name, phone, mail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);

		name = getIntent().getStringExtra("name");
		phone = getIntent().getStringExtra("phone");
		mail = getIntent().getStringExtra("mail");

		init();
	}

	private void init() {
		layout_name = (LinearLayout) findViewById(R.id.layout_myinfo_name);
		layout_phone = (LinearLayout) findViewById(R.id.layout_myinfo_phone);
		layout_mail = (LinearLayout) findViewById(R.id.layout_myinfo_mail);

		layout_name.setOnClickListener(new theOnClickListener());
		layout_phone.setOnClickListener(new theOnClickListener());
		layout_mail.setOnClickListener(new theOnClickListener());

		textView_name = (TextView) findViewById(R.id.textView_myinfo_name);
		textView_phone = (TextView) findViewById(R.id.textView_myinfo_phone);
		textView_mail = (TextView) findViewById(R.id.textView_myinfo_mail);

		textView_name.setText(name);
		textView_phone.setText(phone);
		textView_mail.setText(mail);

	}

	public class theOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.layout_myinfo_name:

				break;
			case R.id.layout_myinfo_phone:

				break;
			case R.id.layout_myinfo_mail:

				break;

			default:
				break;
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
}
