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
		Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
		android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
		p.height = (int) (d.getHeight() * 1); // 高度设置为屏幕的0.8
		p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.7
		p.alpha = 0.8f; // 设置本身透明度
		p.dimAmount = 0.1f; // 设置黑暗度
		getWindow().setAttributes(p);
		init();
	}

	public void init() {
		orderByValue = "";
		orderType = "";
		textView_dialogsortstyle_delault = (TextView) findViewById(R.id.textView_dialogsortstyle_delault);
		textView_dialogsortstyle_delault.setTag("默认");
		textView_dialogsortstyle_delault
				.setOnClickListener(new SortstyleDialogClickListener());

		textView_dialogsortstyle_moneyup = (TextView) findViewById(R.id.textView_dialogsortstyle_moneyup);
		textView_dialogsortstyle_moneyup.setTag("价格最高");
		textView_dialogsortstyle_moneyup
				.setOnClickListener(new SortstyleDialogClickListener());

		textView_dialogsortstyle_moneydown = (TextView) findViewById(R.id.textView_dialogsortstyle_moneydown);
		textView_dialogsortstyle_moneydown.setTag("价格最低");
		textView_dialogsortstyle_moneydown
				.setOnClickListener(new SortstyleDialogClickListener());

		textView_dialogsortstyle_scoreup = (TextView) findViewById(R.id.textView_dialogsortstyle_scoreup);
		textView_dialogsortstyle_scoreup.setTag("评分");
		textView_dialogsortstyle_scoreup
				.setOnClickListener(new SortstyleDialogClickListener());

		textView_dialogsortstyle_commentup = (TextView) findViewById(R.id.textView_dialogsortstyle_commentup);
		textView_dialogsortstyle_commentup.setTag("评论");
		textView_dialogsortstyle_commentup
				.setOnClickListener(new SortstyleDialogClickListener());

		textView_dialogsortstyle_bookup = (TextView) findViewById(R.id.textView_dialogsortstyle_bookup);
		textView_dialogsortstyle_bookup.setTag("预定");
		textView_dialogsortstyle_bookup
				.setOnClickListener(new SortstyleDialogClickListener());

		mySharedPreferences = getSharedPreferences("searchcommit",
				Activity.MODE_PRIVATE);
		// 实例化SharedPreferences.Editor对象（第二步）
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
			if (v.getTag().equals("默认")) {
				checkselect();
				textView_dialogsortstyle_delault
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "0");
				editor.putString("orderType", "2");
				// 提交当前数据
				editor.commit();

				finish();
			}

			if (v.getTag().equals("价格最高")) {
				checkselect();
				textView_dialogsortstyle_moneyup
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "1");
				editor.putString("orderType", "2");
				// 提交当前数据
				editor.commit();

				finish();
			}

			if (v.getTag().equals("价格最低")) {

				checkselect();
				textView_dialogsortstyle_moneydown
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "1");
				editor.putString("orderType", "1");
				// 提交当前数据
				editor.commit();

				finish();

			}

			if (v.getTag().equals("评分")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);

				checkselect();
				textView_dialogsortstyle_scoreup
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "3");
				editor.putString("orderType", "2");
				// 提交当前数据
				editor.commit();

				finish();

			}

			if (v.getTag().equals("评论")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);

				checkselect();
				textView_dialogsortstyle_commentup
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "2");
				editor.putString("orderType", "2");
				// 提交当前数据
				editor.commit();

				finish();
			}

			if (v.getTag().equals("预定")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);

				checkselect();
				textView_dialogsortstyle_bookup
						.setBackgroundResource(R.drawable.ic_select);

				editor.putString("orderByValue", "4");
				editor.putString("orderType", "2");
				// 提交当前数据
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
