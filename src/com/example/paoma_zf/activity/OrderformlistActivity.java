package com.example.paoma_zf.activity;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.ListViewForScrollView;

public class OrderformlistActivity extends Activity {

	ListViewForScrollView listViewForScrollView_orderform_orderlist;
	ScrollView scrollView_orderform_scroll;
	List<Map<String, Object>> OrderList;
	String userId;
	ZfApplication Zfapp;
	Zfnet zfnet;
	ProgressDialog xh_pDialog;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_orderformlist);
		init();
		new Thread(new Runnable() {
			public void run() {
				Message message = new Message();
				try {
					OrderList = zfnet.getOrderformList(Zfapp.getUserId()
							.toString(), getApplicationContext());

					if (OrderList.size() > 0) {
						message.arg1 = 1;
					} else {
						message.arg1 = 2;
					}
				} catch (Exception e1) {
				} finally {
					message.what = 1;
					myHandler.sendMessage(message);
				}
			}
		}).start();
	}

	private void init() {

		Zfapp = (ZfApplication) getApplication();
		zfnet = new Zfnet();
		listViewForScrollView_orderform_orderlist = (ListViewForScrollView) findViewById(R.id.listViewForScrollView_orderform_orderlist);
		listViewForScrollView_orderform_orderlist
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						TextView Id = (TextView) arg1
								.findViewById(R.id.textView_lvorderform_number);

						Intent intent = new Intent(OrderformlistActivity.this,
								OrderformInfoActivity.class);
						intent.putExtra("orderformId", Id.getText().toString());

						startActivity(intent);
					}
				});

		scrollView_orderform_scroll = (ScrollView) findViewById(R.id.scrollView_orderform_scroll);

		scrollView_orderform_scroll.smoothScrollTo(0, 0);
		xh_pDialog = new ProgressDialog(OrderformlistActivity.this);

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

		// ����ProgressDialog ��һ��Button

		// ��ProgressDialog��ʾ
		xh_pDialog.show();

	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {
				xh_pDialog.cancel();
				if (msg.arg1 == 1) {
					SimpleAdapter adapter = new SimpleAdapter(
							getApplicationContext(), OrderList,
							R.layout.style_lvorderform, new String[] {
									"ordersId", "totalPrice", "houseName",
									"startDt", "endDt", "headPic" }, new int[] {
									R.id.textView_lvorderform_number,
									R.id.textView_lvorderform_money,
									R.id.textView_lvorderform_title,
									R.id.textView_lvorderform_timein,
									R.id.textView_lvorderform_timeout,
									R.id.imageView_lvorderform_img

							});

					adapter.setViewBinder(new ViewBinder() {

						@Override
						public boolean setViewValue(View view, Object data,
								String str) {
							// TODO Auto-generated method stub

							if (view instanceof ImageView
									&& data instanceof Bitmap) {
								ImageView iv = (ImageView) view;
								iv.setImageBitmap((Bitmap) data);
								return true;
							} else
								return false;
						}
					});

					System.out.println();
					listViewForScrollView_orderform_orderlist
							.setAdapter(adapter);
				} else {
					Toast.makeText(getApplicationContext(), "���޶���",
							Toast.LENGTH_SHORT).show();
					finish();
				}
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
