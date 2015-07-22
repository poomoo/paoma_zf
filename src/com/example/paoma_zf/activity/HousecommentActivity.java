package com.example.paoma_zf.activity;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.ListViewForScrollView;

public class HousecommentActivity extends Activity {

	ScrollView scrollView_housecommentlist_scroll;
	ListViewForScrollView listViewForScrollView_housecommentlist_commentlist;
	List<Map<String, Object>> housecommentList;
	Zfnet zz;
	ProgressDialog xh_pDialog;
	ZfApplication zfapp;
	String houseId;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_housecommentlist);
		init();

	}

	private void init() {

		houseId = getIntent().getStringExtra("houseId");
		System.out.println(houseId);
		listViewForScrollView_housecommentlist_commentlist = (ListViewForScrollView) findViewById(R.id.listViewForScrollView_housecommentlist_commentlist);

		scrollView_housecommentlist_scroll = (ScrollView) findViewById(R.id.scrollView_housecommentlist_scroll);
		scrollView_housecommentlist_scroll.smoothScrollTo(0, 0);
		zz = new Zfnet();
		zfapp = (ZfApplication) getApplication();

		xh_pDialog = new ProgressDialog(HousecommentActivity.this);
		xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		xh_pDialog.setMessage("请稍后....");
		xh_pDialog.setIndeterminate(false);
		// 设置ProgressDialog 是否可以按退回键取消
		xh_pDialog.setCancelable(true);
		// 让ProgressDialog显示
		xh_pDialog.show();

		new Thread(new Runnable() {
			public void run() {

				try {

					housecommentList = zz.getHousecommentList(houseId,
							getApplicationContext());

					if (housecommentList.size() > 1) {
						Message message = new Message();

						message.what = 1;

						myHandler.sendMessage(message);
					} else {
						Message message = new Message();

						message.what = 2;

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

				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(),
						housecommentList,
						R.layout.style_lvhousecomment,
						new String[] { "userName", "appraiseDt", "content", },
						new int[] {
								R.id.textView_stylelvhousecomment_username,
								R.id.textView_stylelvhousecomment_commentdt,
								R.id.textView_stylelvhousecomment_commentcontent

						});

				listViewForScrollView_housecommentlist_commentlist
						.setAdapter(adapter);
				xh_pDialog.cancel();
			}

			if (msg.what == 2) {
				xh_pDialog.cancel();
				Toast.makeText(getApplicationContext(), "当前没有评论请返回",
						Toast.LENGTH_LONG).show();
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
