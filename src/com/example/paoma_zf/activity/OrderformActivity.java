package com.example.paoma_zf.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.ClearEditText;

public class OrderformActivity extends Activity {

	List<Map<String, Object>> OrderList;
	List<Map<String, Object>> addOrderresult;

	String houseId;

	TextView text_orderform_title, text_orderform_style, text_orderform_timein,
			text_orderform_timeout, text_orderform_daytotal,
			text_orderform_amountpaid, text_orderform_totalorder,
			text_orderform_prepaidamount, text_orderform_housekeepername,
			text_orderform_housekeeperphone, text_orderform_paytotal,
			text_orderform_name, text_orderform_phone, text_orderform_idNum,
			text_orderform_reTel;
	EditText editText_orderform_message;

	Button button_orderform_paytotal;
	LinearLayout LinearLayout_orderform_select;
	int allday;
	String price;
	ZfApplication zfapp;
	SharedPreferences sp;

	Zfnet zz;
	ProgressDialog xh_pDialog;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_orderform);

		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void init() throws Exception {
		houseId = getIntent().getStringExtra("houseId");
		zfapp = (ZfApplication) getApplication();

		zz = new Zfnet();
		sp = getSharedPreferences("date", Context.MODE_PRIVATE);

		xh_pDialog = new ProgressDialog(OrderformActivity.this);
		// ���ý�������񣬷��ΪԲ�Σ���ת��
		xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// ����ProgressDialog ����
		// xh_pDialog.setTitle("��ʾ");
		// ����ProgressDialog��ʾ��Ϣ
		xh_pDialog.setMessage("���Ժ�....");
		// ����ProgressDialog����ͼ��
		// xh_pDialog.setIcon(R.drawable.ic_launcher);
		// ����ProgressDialog �Ľ������Ƿ���ȷ false ���ǲ�����Ϊ����ȷ
		xh_pDialog.setIndeterminate(false);
		// ����ProgressDialog �Ƿ���԰��˻ؼ�ȡ��
		xh_pDialog.setCancelable(true);

		// ��ProgressDialog��ʾ
		xh_pDialog.show();

		LinearLayout_orderform_select = (LinearLayout) findViewById(R.id.LinearLayout_orderform_select);
		LinearLayout_orderform_select.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(OrderformActivity.this,
						CalendarActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);
			}
		});

		text_orderform_title = (TextView) findViewById(R.id.text_orderform_title);

		text_orderform_style = (TextView) findViewById(R.id.text_orderform_style);

		text_orderform_timein = (TextView) findViewById(R.id.text_orderform_timein);
		// text_orderform_timein.setText(sp.getString("formIn", "").toString());

		text_orderform_timeout = (TextView) findViewById(R.id.text_orderform_timeout);
		// text_orderform_timeout.setText(sp.getString("formOut",
		// "").toString());

		text_orderform_daytotal = (TextView) findViewById(R.id.text_orderform_daytotal);
		// text_orderform_daytotal.setText(Double.toString(dayCount)+"��");

		text_orderform_amountpaid = (TextView) findViewById(R.id.text_orderform_amountpaid);

		text_orderform_totalorder = (TextView) findViewById(R.id.text_orderform_totalorder);

		text_orderform_prepaidamount = (TextView) findViewById(R.id.text_orderform_prepaidamount);

		text_orderform_housekeepername = (TextView) findViewById(R.id.text_orderform_housekeepername);

		text_orderform_housekeeperphone = (TextView) findViewById(R.id.text_orderform_housekeeperphone);

		text_orderform_paytotal = (TextView) findViewById(R.id.text_orderform_paytotal);

		editText_orderform_message = (EditText) findViewById(R.id.editText_orderform_message);

		text_orderform_name = (TextView) findViewById(R.id.textView_orderform_name);
		text_orderform_phone = (TextView) findViewById(R.id.textView_orderform_phonenum);
		text_orderform_idNum = (TextView) findViewById(R.id.textView_orderform_idnum);
		text_orderform_reTel = (TextView) findViewById(R.id.textView_orderform_tuijiannum);

		text_orderform_name.setText(zfapp.getUserName());
		text_orderform_name.setText(zfapp.getTel());

		button_orderform_paytotal = (Button) findViewById(R.id.button_orderform_confirm);

		button_orderform_paytotal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				xh_pDialog.show();
				
				Log.i("Orderform", "����ύ����");
				System.out.println("����ύ����");

				new Thread(new Runnable() {
					public void run() {

						try {
							SimpleDateFormat formatter = new SimpleDateFormat(
									"yyyy��MM��dd��    HH:mm:ss");
							Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
							String ordersDt = formatter.format(curDate);

							addOrderresult = zz.addOrderform(
									zfapp.getUserId(),
									text_orderform_timein.getText().toString(),
									text_orderform_timeout.getText().toString(),
									text_orderform_daytotal.getText()
											.toString(),
									text_orderform_paytotal.getText()
											.toString(), ordersDt, houseId,
									"1", OrderList.get(0).get("currentPrice")
											.toString(),
									editText_orderform_message.getText()
											.toString(), zfapp.getUserName(),
									zfapp.getTel(), "", "");

							if (addOrderresult.get(0).get("msg").toString()
									.equals("�ύ�����ɹ�")) {
								Message message = new Message();

								message.what = 2;

								myHandler.sendMessage(message);
							} else {
								Message message = new Message();

								message.what = 3;

								myHandler.sendMessage(message);
							}

						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
	}

	private String payallmoney() {

		price = OrderList.get(0).get("currentPrice").toString();
		int i = Integer.parseInt(price);

		i = i * allday;
		String s = Integer.toString(i);
		return s;
	}

	private void setText() throws Exception {

		text_orderform_title.setText(OrderList.get(0).get("name").toString());

		text_orderform_style.setText(OrderList.get(0).get("houseType")
				.toString());

		text_orderform_amountpaid.setText(OrderList.get(0).get("currentPrice")
				.toString());

		text_orderform_totalorder.setText(OrderList.get(0).get("currentPrice")
				.toString());

		text_orderform_prepaidamount.setText(OrderList.get(0)
				.get("currentPrice").toString());

		text_orderform_housekeepername.setText(OrderList.get(0)
				.get("housekeeperName").toString());

		text_orderform_housekeeperphone.setText(OrderList.get(0)
				.get("housekeeperTel").toString());

		if (sp.getString("dateIn", "").toString().length() > 0) {
			text_orderform_timein
					.setText(sp.getString("dateIn", "").toString());

			text_orderform_timeout.setText(sp.getString("dateOut", "")
					.toString());
		} else {

			Calendar ca = Calendar.getInstance();// �õ�һ��Calendar��ʵ��
			ca.setTime(new Date()); // ����ʱ��Ϊ��ǰʱ��
			Date nowday = ca.getTime(); // ���

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowdate = sdf.format(nowday);

			ca.add(Calendar.DATE, +1); // ��һ��
			Date lastday = ca.getTime(); // ���

			String lastdate = sdf.format(lastday);
			// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			// String nowday=sdf.format(new java.util.Date());

			text_orderform_timein.setText(nowdate);

			text_orderform_timeout.setText(lastdate);
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// �������ڵĸ�ʽ
		Date date1 = null;
		date1 = simpleDateFormat.parse(text_orderform_timein.getText()
				.toString());

		Date date2 = null;
		date2 = simpleDateFormat.parse(text_orderform_timeout.getText()
				.toString());

		GregorianCalendar cal1 = new GregorianCalendar();
		GregorianCalendar cal2 = new GregorianCalendar();

		cal1.setTime(date1);
		cal2.setTime(date2);

		// double dayCount =
		// (cal2.getTimeInMillis()-cal1.getTimeInMillis())/(1000*3600*24);//�Ӽ�������ɼ������
		allday = (int) ((cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000 * 3600 * 24));// �Ӽ�������ɼ������

		text_orderform_daytotal.setText(allday + "��");
		text_orderform_paytotal.setText(payallmoney());

	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {
				try {
					setText();
					xh_pDialog.cancel();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (msg.what == 2) {
				xh_pDialog.cancel();
				Toast.makeText(getApplicationContext(),
						addOrderresult.get(0).get("msg").toString(),
						Toast.LENGTH_SHORT).show();
				Intent i = new Intent();
				i.setClass(OrderformActivity.this, OrderformInfoActivity.class);
				i.putExtra("orderformId", addOrderresult.get(0).get("ordersId")
						.toString());
				startActivity(i);
				finish();
			}
			if (msg.what == 3) {
				xh_pDialog.cancel();
				Toast.makeText(getApplicationContext(),
						addOrderresult.get(0).get("msg").toString(),
						Toast.LENGTH_SHORT).show();
			}

			super.handleMessage(msg);
		}
	};

	// public Boolean checktext() {
	//
	// if (clearEditText_orderform_name.getText().equals("")) {
	// AlertDialog.Builder builder = new Builder(OrderformActivity.this);
	// builder.setMessage("�û���������Ϊ��");
	// builder.setTitle("��ʾ");
	// builder.setPositiveButton("ȷ��", new Dialog.OnClickListener() {
	//
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// // TODO Auto-generated
	// // method stub
	// dialog.dismiss();
	// }
	// });
	// builder.create().show();
	// }
	//
	// return true;
	//
	// }

	@Override
	protected void onStart() {
		super.onStart();

		new Thread(new Runnable() {
			public void run() {

				try {

					OrderList = zz.toSubmitOrders(houseId,
							getApplicationContext());
					Message message = new Message();

					message.what = 1;

					if (OrderList.size() > 0) {
						myHandler.sendMessage(message);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}).start();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
