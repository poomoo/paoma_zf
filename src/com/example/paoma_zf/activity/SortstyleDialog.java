package com.example.paoma_zf.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.paoma_zf.R;

@SuppressLint("NewApi")
public class SortstyleDialog extends Activity {

	TextView textView_dialogsortstyle_delault,
			textView_dialogsortstyle_moneyup,
			textView_dialogsortstyle_moneydown,
			textView_dialogsortstyle_scoreup,
			textView_dialogsortstyle_commentup,
			textView_dialogsortstyle_bookup;

	String orderByValue, orderType;
	SharedPreferences mySharedPreferences;
	SharedPreferences.Editor editor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.dialog_sortstyle);
		setFinishOnTouchOutside(true);//
		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // Ϊ��ȡ��Ļ����
		android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
		p.height = (int) (d.getHeight() * 1); // �߶�����Ϊ��Ļ��0.8
		p.width = (int) (d.getWidth() * 0.9); // �������Ϊ��Ļ��0.7
		p.alpha = 0.8f; // ���ñ���͸����
		p.dimAmount = 0.1f; // ���úڰ���
		getWindow().setAttributes(p);
		init();
	}

	public void init() {
		orderByValue = "";
		orderType = "";
		textView_dialogsortstyle_delault = (TextView) findViewById(R.id.textView_dialogsortstyle_delault);
		textView_dialogsortstyle_delault.setTag("Ĭ��");
		textView_dialogsortstyle_delault
				.setOnClickListener(new SortstyleDialogClickListener());

		textView_dialogsortstyle_moneyup = (TextView) findViewById(R.id.textView_dialogsortstyle_moneyup);
		textView_dialogsortstyle_moneyup.setTag("�۸����");
		textView_dialogsortstyle_moneyup
				.setOnClickListener(new SortstyleDialogClickListener());

		textView_dialogsortstyle_moneydown = (TextView) findViewById(R.id.textView_dialogsortstyle_moneydown);
		textView_dialogsortstyle_moneydown.setTag("�۸����");
		textView_dialogsortstyle_moneydown
				.setOnClickListener(new SortstyleDialogClickListener());

		textView_dialogsortstyle_scoreup = (TextView) findViewById(R.id.textView_dialogsortstyle_scoreup);
		textView_dialogsortstyle_scoreup.setTag("����");
		textView_dialogsortstyle_scoreup
				.setOnClickListener(new SortstyleDialogClickListener());

		textView_dialogsortstyle_commentup = (TextView) findViewById(R.id.textView_dialogsortstyle_commentup);
		textView_dialogsortstyle_commentup.setTag("����");
		textView_dialogsortstyle_commentup
				.setOnClickListener(new SortstyleDialogClickListener());

		textView_dialogsortstyle_bookup = (TextView) findViewById(R.id.textView_dialogsortstyle_bookup);
		textView_dialogsortstyle_bookup.setTag("Ԥ��");
		textView_dialogsortstyle_bookup
				.setOnClickListener(new SortstyleDialogClickListener());

		mySharedPreferences = getSharedPreferences("searchcommit",
				Activity.MODE_PRIVATE);
		// ʵ����SharedPreferences.Editor���󣨵ڶ�����
		editor = mySharedPreferences.edit();

	}

	public void checkselect() {
		textView_dialogsortstyle_delault.setBackgroundResource(0);
		textView_dialogsortstyle_moneyup.setBackgroundResource(0);
		textView_dialogsortstyle_moneydown.setBackgroundResource(0);
		textView_dialogsortstyle_scoreup.setBackgroundResource(0);
		textView_dialogsortstyle_commentup.setBackgroundResource(0);
		textView_dialogsortstyle_bookup.setBackgroundResource(0);

	}

	class SortstyleDialogClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			if (v.getTag().equals("Ĭ��")) {
				checkselect();
				textView_dialogsortstyle_delault
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "0");
				editor.putString("orderType", "2");
				// �ύ��ǰ����
				editor.commit();

				finish();
			}

			if (v.getTag().equals("�۸����")) {
				checkselect();
				textView_dialogsortstyle_moneyup
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "1");
				editor.putString("orderType", "2");
				// �ύ��ǰ����
				editor.commit();

				finish();
			}

			if (v.getTag().equals("�۸����")) {

				checkselect();
				textView_dialogsortstyle_moneydown
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "1");
				editor.putString("orderType", "1");
				// �ύ��ǰ����
				editor.commit();

				finish();

			}

			if (v.getTag().equals("����")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);

				checkselect();
				textView_dialogsortstyle_scoreup
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "3");
				editor.putString("orderType", "2");
				// �ύ��ǰ����
				editor.commit();

				finish();

			}

			if (v.getTag().equals("����")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);

				checkselect();
				textView_dialogsortstyle_commentup
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "2");
				editor.putString("orderType", "2");
				// �ύ��ǰ����
				editor.commit();

				finish();
			}

			if (v.getTag().equals("Ԥ��")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);

				checkselect();
				textView_dialogsortstyle_bookup
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "4");
				editor.putString("orderType", "2");
				// �ύ��ǰ����
				editor.commit();

				finish();
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
