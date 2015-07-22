package com.example.paoma_zf.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.paoma_zf.R;

@SuppressLint({ "ResourceAsColor", "NewApi" })
public class HomestyleDialog extends Activity {

	TextView textView_dialoghomestyle_buxian, textView_dialoghomestyle_yiju,
			textView_dialoghomestyle_erju, textView_dialoghomestyle_sanju;
	TextView textView_dialoghomestyle_biesu, textView_dialoghomestyle_kezhan,
			textView_dialoghomestyle_siheyuan,
			textView_dialoghomestyle_linjianxiaowu;
	String roomtype, housetype;
	RelativeLayout relativeLayout_dialoghomestyle_quxiao,
			relativeLayout_dialoghomestyle_queding;
	int js;
	int ts;

	public static HomestyleDialog instance = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ////设置为true点击区域外消失
		setFinishOnTouchOutside(true);//
		setContentView(R.layout.dialog_homestyle);
		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // 为获取屏幕宽、高
		android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
		p.height = (int) (d.getHeight() * 0.7); // 高度设置为屏幕的0.8
		p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.7
		p.alpha = 0.8f; // 设置本身透明度
		p.dimAmount = 0.1f; // 设置黑暗度
		getWindow().setAttributes(p);

		init();
	}

	public void init() {

		js = 0;
		ts = 0;
		instance = this;
		roomtype = "";
		housetype = "";
		textView_dialoghomestyle_buxian = (TextView) findViewById(R.id.textView_dialoghomestyle_buxian);
		textView_dialoghomestyle_buxian.setTag("不限");
		textView_dialoghomestyle_buxian
				.setOnClickListener(new HomeDialogonClickListener());

		textView_dialoghomestyle_yiju = (TextView) findViewById(R.id.textView_dialoghomestyle_yiju);
		textView_dialoghomestyle_yiju.setTag("一居");
		textView_dialoghomestyle_yiju
				.setOnClickListener(new HomeDialogonClickListener());

		textView_dialoghomestyle_erju = (TextView) findViewById(R.id.textView_dialoghomestyle_erju);
		textView_dialoghomestyle_erju.setTag("二居");
		textView_dialoghomestyle_erju
				.setOnClickListener(new HomeDialogonClickListener());

		textView_dialoghomestyle_sanju = (TextView) findViewById(R.id.textView_dialoghomestyle_sanju);
		textView_dialoghomestyle_sanju.setTag("三居以上");
		textView_dialoghomestyle_sanju
				.setOnClickListener(new HomeDialogonClickListener());

		textView_dialoghomestyle_biesu = (TextView) findViewById(R.id.textView_dialoghomestyle_biesu);
		textView_dialoghomestyle_biesu.setTag("独栋别墅");
		textView_dialoghomestyle_biesu
				.setOnClickListener(new HomeDialogonClickListener());

		textView_dialoghomestyle_kezhan = (TextView) findViewById(R.id.textView_dialoghomestyle_kezhan);
		textView_dialoghomestyle_kezhan.setTag("客栈");
		textView_dialoghomestyle_kezhan
				.setOnClickListener(new HomeDialogonClickListener());

		textView_dialoghomestyle_siheyuan = (TextView) findViewById(R.id.textView_dialoghomestyle_siheyuan);
		textView_dialoghomestyle_siheyuan.setTag("四合院");
		textView_dialoghomestyle_siheyuan
				.setOnClickListener(new HomeDialogonClickListener());

		textView_dialoghomestyle_linjianxiaowu = (TextView) findViewById(R.id.textView_dialoghomestyle_linjianxiaowu);
		textView_dialoghomestyle_linjianxiaowu.setTag("林间小屋");
		textView_dialoghomestyle_linjianxiaowu
				.setOnClickListener(new HomeDialogonClickListener());

		relativeLayout_dialoghomestyle_quxiao = (RelativeLayout) findViewById(R.id.relativeLayout_dialoghomestyle_quxiao);
		relativeLayout_dialoghomestyle_quxiao
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});

		relativeLayout_dialoghomestyle_queding = (RelativeLayout) findViewById(R.id.relativeLayout_dialoghomestyle_queding);
		relativeLayout_dialoghomestyle_queding
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// Intent i=new Intent();
						// i.putExtra("roomtype", roomtype);
						// i.putExtra("housetype", housetype);
						// i.setClass(HomestyleDialog.this,
						// LookhomeActivity.class);
						// startActivity(i);

						SharedPreferences mySharedPreferences = getSharedPreferences(
								"searchcommit", Activity.MODE_PRIVATE);
						// 实例化SharedPreferences.Editor对象（第二步）
						SharedPreferences.Editor editor = mySharedPreferences
								.edit();

						editor.putString("roomtype", roomtype);
						editor.putString("housetype", housetype);
						if (js == 0) {
							editor.putString("roomtype", "不限");
						}

						if (ts == 0) {
							editor.putString("housetype", "");
						}

						// 提交当前数据
						editor.commit();

						finish();
						// Intent i=new Intent();
						// i.putExtra("roomtype", roomtype);
						// i.putExtra("housetype", housetype);
						// i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
						// i.setClass(HomestyleDialog.this,
						// LookhomeActivity.class);
						// startActivityForResult(i,0);
						// startActivity(i);

						//
						// overridePendingTransition(0, 0);
						// finish();

					}
				});
	}

	public void checkroomtype() {

		textView_dialoghomestyle_buxian.setBackgroundResource(0);

		textView_dialoghomestyle_yiju.setBackgroundResource(0);
		textView_dialoghomestyle_erju.setBackgroundResource(0);
		textView_dialoghomestyle_sanju.setBackgroundResource(0);
		js = 1;
	}

	public void checkhousetype() {

		textView_dialoghomestyle_biesu.setBackgroundResource(0);

		textView_dialoghomestyle_kezhan.setBackgroundResource(0);
		textView_dialoghomestyle_siheyuan.setBackgroundResource(0);
		textView_dialoghomestyle_linjianxiaowu.setBackgroundResource(0);
		ts = 1;
	}

	class HomeDialogonClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			if (v.getTag().equals("不限")) {
				// textView_dialoghomestyle_buxian.setTextColor(R.color.green);
				checkroomtype();
				textView_dialoghomestyle_buxian
						.setBackgroundResource(R.drawable.ic_select);
				roomtype = "";
			}

			if (v.getTag().equals("一居")) {
				// textView_dialoghomestyle_yiju.setTextColor(R.color.red);
				checkroomtype();
				textView_dialoghomestyle_yiju
						.setBackgroundResource(R.drawable.ic_select);
				roomtype = "一居室";
			}

			if (v.getTag().equals("二居")) {
				// textView_dialoghomestyle_erju.setTextColor(R.color.red);
				checkroomtype();
				textView_dialoghomestyle_erju
						.setBackgroundResource(R.drawable.ic_select);
				roomtype = "二居室";
			}

			if (v.getTag().equals("三居以上")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);
				checkroomtype();
				textView_dialoghomestyle_sanju
						.setBackgroundResource(R.drawable.ic_select);
				roomtype = "三居室以上";
			}

			if (v.getTag().equals("独栋别墅")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);
				checkhousetype();
				textView_dialoghomestyle_biesu
						.setBackgroundResource(R.drawable.ic_select);

				housetype = "独栋别墅";
			}

			if (v.getTag().equals("客栈")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);
				checkhousetype();
				textView_dialoghomestyle_kezhan
						.setBackgroundResource(R.drawable.ic_select);

				housetype = "客栈";
			}

			if (v.getTag().equals("四合院")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);
				checkhousetype();
				textView_dialoghomestyle_siheyuan
						.setBackgroundResource(R.drawable.ic_select);

				housetype = "四合院";
			}

			if (v.getTag().equals("林间小屋")) {
				// textView_dialoghomestyle_sanju.setTextColor(R.color.red);
				checkhousetype();
				textView_dialoghomestyle_linjianxiaowu
						.setBackgroundResource(R.drawable.ic_select);

				housetype = "林间小屋";
			}

		}

	}

}
