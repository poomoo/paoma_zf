package com.example.paoma_zf.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.ClearEditText;

public class OrderformInfoActivity extends Activity {

	TextView textView_orderforminfo_orderformnum,
			textView_orderforminfo_orderformstate,
			textView_orderforminfo_orderformtime, text_orderforminfo_title,
			text_orderforminfo_style, text_orderforminfo_timein,
			text_orderforminfo_timeout, text_orderforminfo_daytotal,
			text_orderforminfo_roomtotal, text_orderforminfo_amountpaid,
			text_orderforminfo_totalorder, text_orderforminfo_prepaidamount,
			text_orderforminfo_availablevolumereduction,
			text_orderforminfo_housekeepername,
			text_orderforminfo_housekeeperphone, text_orderforminfo_paytotal,
			text_orderforminfo_name, text_orderforminfo_phoneNum,
			text_orderforminfo_idNum, text_orderforminfo_tuijianNum;
	EditText editText_orderforminfo_message;

	// ClearEditText clearEditText_orderforminfo_name,
	// clearEditText_orderforminfo_phone,
	// clearEditText_orderforminfo_idCardNum,
	// clearEditText_orderforminfo_refTel;

	Button button_orderforminfo_paytotal;

	List<Map<String, Object>> Orderinfo;
	// CheckBox checkBox_orderforminfo_ck;

	String orderformId;
	int allday;
	Zfnet zfnet;

	SharedPreferences sp;
	Editor editor;
	ProgressDialog xh_pDialog;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_orderforminfo);
		init();
		new Thread(new Runnable() {
			public void run() {

				try {
					Orderinfo = zfnet.getOrderinfo(orderformId,
							getApplication());

					if (Orderinfo.size() > 0) {
						Message message = new Message();

						message.what = 1;
						myHandler.sendMessage(message);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}).start();

	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				textView_orderforminfo_orderformnum.setText(Orderinfo.get(0)
						.get("ordersId").toString());

				if (Orderinfo.get(0).get("status").equals("1")) {
					textView_orderforminfo_orderformstate.setText("支付成功");
					editText_orderforminfo_message.setEnabled(false);

					if (Orderinfo.get(0).get("isAppraise").equals("false")) {
						button_orderforminfo_paytotal.setText("去评论");
						button_orderforminfo_paytotal.setTag("去评论");

					} else {
						button_orderforminfo_paytotal.setVisibility(View.GONE);
					}
				} else {
					textView_orderforminfo_orderformstate.setText("未支付");
					button_orderforminfo_paytotal.setText("去支付");
					button_orderforminfo_paytotal.setTag("去支付");
				}

				textView_orderforminfo_orderformtime.setText(Orderinfo.get(0)
						.get("ordersDt").toString());

				text_orderforminfo_title.setText(Orderinfo.get(0)
						.get("houseName").toString());

				text_orderforminfo_timein.setText(Orderinfo.get(0)
						.get("startDt").toString());

				text_orderforminfo_timeout.setText(Orderinfo.get(0)
						.get("endDt").toString());

				text_orderforminfo_housekeepername.setText(Orderinfo.get(0)
						.get("housekeeperName").toString());

				text_orderforminfo_housekeeperphone.setText(Orderinfo.get(0)
						.get("housekeeperTel").toString());

				text_orderforminfo_style.setText(Orderinfo.get(0).get("type")
						.toString());

				text_orderforminfo_name.setText(Orderinfo.get(0)
						.get("ordersUserName").toString());
				text_orderforminfo_phoneNum.setText(Orderinfo.get(0)
						.get("ordersTel").toString());
				text_orderforminfo_idNum.setText(Orderinfo.get(0)
						.get("idCardNum").toString());
				text_orderforminfo_tuijianNum.setText(Orderinfo.get(0)
						.get("idCardNum").toString());

				// clearEditText_orderforminfo_name.setText(Orderinfo.get(0)
				// .get("ordersUserName").toString());
				//
				// clearEditText_orderforminfo_phone.setText(Orderinfo.get(0)
				// .get("ordersTel").toString());
				//
				// clearEditText_orderforminfo_idCardNum.setText(Orderinfo.get(0)
				// .get("idCardNum").toString());
				//
				// clearEditText_orderforminfo_refTel.setText(Orderinfo.get(0)
				// .get("idCardNum").toString());

				editText_orderforminfo_message.setText(Orderinfo.get(0)
						.get("leaveMsg").toString());

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");// 输入日期的格式
				Date date1 = null;
				Date date2 = null;
				try {
					date1 = simpleDateFormat.parse(text_orderforminfo_timein
							.getText().toString());

					date2 = simpleDateFormat.parse(text_orderforminfo_timeout
							.getText().toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				GregorianCalendar cal1 = new GregorianCalendar();
				GregorianCalendar cal2 = new GregorianCalendar();

				cal1.setTime(date1);
				cal2.setTime(date2);

				// double dayCount =
				// (cal2.getTimeInMillis()-cal1.getTimeInMillis())/(1000*3600*24);//从间隔毫秒变成间隔天数
				allday = (int) ((cal2.getTimeInMillis() - cal1
						.getTimeInMillis()) / (1000 * 3600 * 24));// 从间隔毫秒变成间隔天数

				text_orderforminfo_amountpaid.setText(payallmoney().toString());

				text_orderforminfo_totalorder.setText(payallmoney().toString());

				text_orderforminfo_prepaidamount.setText(payallmoney()
						.toString());

				text_orderforminfo_paytotal.setText(payallmoney().toString());

				text_orderforminfo_daytotal.setText(allday + "天");

				xh_pDialog.cancel();
			}
			super.handleMessage(msg);
		}
	};

	private void init() {

		zfnet = new Zfnet();

		xh_pDialog = new ProgressDialog(OrderformInfoActivity.this);
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
		xh_pDialog.setCancelable(true);

		// 让ProgressDialog显示
		xh_pDialog.show();

		textView_orderforminfo_orderformnum = (TextView) findViewById(R.id.textView_orderforminfo_orderformnum);
		textView_orderforminfo_orderformstate = (TextView) findViewById(R.id.textView_orderforminfo_orderformstate);
		textView_orderforminfo_orderformtime = (TextView) findViewById(R.id.textView_orderforminfo_orderformtime);
		text_orderforminfo_title = (TextView) findViewById(R.id.text_orderforminfo_title);
		text_orderforminfo_style = (TextView) findViewById(R.id.text_orderforminfo_style);
		text_orderforminfo_timein = (TextView) findViewById(R.id.text_orderforminfo_timein);
		text_orderforminfo_timeout = (TextView) findViewById(R.id.text_orderforminfo_timeout);
		text_orderforminfo_daytotal = (TextView) findViewById(R.id.text_orderforminfo_daytotal);
		text_orderforminfo_roomtotal = (TextView) findViewById(R.id.text_orderforminfo_roomtotal);
		text_orderforminfo_amountpaid = (TextView) findViewById(R.id.text_orderforminfo_amountpaid);
		text_orderforminfo_totalorder = (TextView) findViewById(R.id.text_orderforminfo_totalorder);
		text_orderforminfo_prepaidamount = (TextView) findViewById(R.id.text_orderforminfo_prepaidamount);
		text_orderforminfo_availablevolumereduction = (TextView) findViewById(R.id.text_orderforminfo_availablevolumereduction);
		text_orderforminfo_housekeepername = (TextView) findViewById(R.id.text_orderforminfo_housekeepername);
		text_orderforminfo_housekeeperphone = (TextView) findViewById(R.id.text_orderforminfo_housekeeperphone);
		text_orderforminfo_paytotal = (TextView) findViewById(R.id.text_orderforminfo_paytotal);
		text_orderforminfo_name = (TextView) findViewById(R.id.textView_orderformlist_name);
		text_orderforminfo_phoneNum = (TextView) findViewById(R.id.textView_orderformlist_phonenum);
		text_orderforminfo_idNum = (TextView) findViewById(R.id.textView_orderformlist_idnum);
		text_orderforminfo_tuijianNum = (TextView) findViewById(R.id.textView_orderformlist_tuijiannum);

		editText_orderforminfo_message = (EditText) findViewById(R.id.editText_orderforminfo_message);

		button_orderforminfo_paytotal = (Button) findViewById(R.id.button_orderforminfo_paytotal);

		orderformId = getIntent().getStringExtra("orderformId");

		button_orderforminfo_paytotal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (button_orderforminfo_paytotal.getTag().equals("去评论")) {
					goPublicationevluationActivity();
				}
				if (button_orderforminfo_paytotal.getTag().equals("去支付")) {
					Toast.makeText(getApplicationContext(), "还未接入支付宝接口",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	private String payallmoney() {

		String price = Orderinfo.get(0).get("currentPrice").toString();
		int i = Integer.parseInt(price);

		i = i * allday;
		String s = Integer.toString(i);
		return s;
	}

	public void goPublicationevluationActivity() {
		Intent ii = new Intent();
		ii.putExtra("houseName", Orderinfo.get(0).get("houseName").toString());
		ii.putExtra("avgScore", Orderinfo.get(0).get("avgScore").toString()
				+ "分");
		ii.putExtra("totalAppraise", Orderinfo.get(0).get("totalAppraise")
				.toString()
				+ "条评论");
		ii.putExtra("type", Orderinfo.get(0).get("type").toString());
		ii.putExtra("paytotal", text_orderforminfo_paytotal.getText()
				.toString());
		ii.putExtra("addressDetails", Orderinfo.get(0).get("addressDetails")
				.toString());

		ii.putExtra("houseId", Orderinfo.get(0).get("id").toString());
		ii.putExtra("ordersId", Orderinfo.get(0).get("ordersId").toString());

		ii.setClass(getApplicationContext(), PublicationevluationActivity.class);
		startActivity(ii);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
