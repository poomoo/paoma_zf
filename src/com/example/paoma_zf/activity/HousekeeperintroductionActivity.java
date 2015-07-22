package com.example.paoma_zf.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.paoma_zf.R;
import com.example.paoma_zf.config.ZfConfig;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.RoundImageView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

public class HousekeeperintroductionActivity extends Activity {

	String managerId;
	List<Map<String, Object>> housekeeperintroduction;
	TextView TextView_housekeeperidc_name, TextView_housekeeperidc_goodpoint,
			TextView_housekeeperidc_address, TextView_housekeeperidc_phone,
			TextView_housekeeperidc_worktime,
			TextView_housekeeperidc_introduction,
			TextView_housekeeperidc_houseintroduction;
	ImageView imageView_housekeeperidc_return;

	RoundImageView roundImageView_housekeeperidc_pic;
	Zfnet zfnet;
	ProgressDialog   xh_pDialog;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_housekeeperintroduction);
		init();

		new Thread(new Runnable() {
			public void run() {

				try {

					housekeeperintroduction = zfnet.getHouseKeeperintroduction(
							managerId, getApplicationContext());

					Message message = new Message();

					message.what = 1;

					if (housekeeperintroduction.size() > 0) {
						myHandler.sendMessage(message);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}).start();

	}

	private void init() {
		managerId = getIntent().getStringExtra("managerId");

		zfnet = new Zfnet();

		
        xh_pDialog = new ProgressDialog(HousekeeperintroductionActivity.this);  
        // ���ý�������񣬷��ΪԲ�Σ���ת��  
        xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  
        // ����ProgressDialog ����  
//        xh_pDialog.setTitle("��ʾ");  
        // ����ProgressDialog��ʾ��Ϣ  
        xh_pDialog.setMessage("���Ժ�....");  
        // ����ProgressDialog����ͼ��  
//        xh_pDialog.setIcon(R.drawable.ic_launcher);  
        // ����ProgressDialog �Ľ������Ƿ���ȷ false ���ǲ�����Ϊ����ȷ  
        xh_pDialog.setIndeterminate(false);  
        // ����ProgressDialog �Ƿ���԰��˻ؼ�ȡ��  
        xh_pDialog.setCancelable(true);  
 
        // ��ProgressDialog��ʾ  
        xh_pDialog.show(); 
		
		
		
		TextView_housekeeperidc_name = (TextView) findViewById(R.id.TextView_housekeeperidc_name);

		TextView_housekeeperidc_goodpoint = (TextView) findViewById(R.id.TextView_housekeeperidc_goodpoint);

		TextView_housekeeperidc_address = (TextView) findViewById(R.id.TextView_housekeeperidc_address);

		TextView_housekeeperidc_phone = (TextView) findViewById(R.id.TextView_housekeeperidc_phone);

		TextView_housekeeperidc_worktime = (TextView) findViewById(R.id.TextView_housekeeperidc_worktime);

		TextView_housekeeperidc_introduction = (TextView) findViewById(R.id.TextView_housekeeperidc_introduction);

		TextView_housekeeperidc_houseintroduction = (TextView) findViewById(R.id.TextView_housekeeperidc_houseintroduction);

		roundImageView_housekeeperidc_pic = (RoundImageView) findViewById(R.id.roundImageView_housekeeperidc_pic);

		imageView_housekeeperidc_return = (ImageView) findViewById(R.id.imageView_housekeeperidc_return);

		imageView_housekeeperidc_return
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});

	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				TextView_housekeeperidc_name.setText(housekeeperintroduction
						.get(0).get("managerName").toString());

				TextView_housekeeperidc_goodpoint
						.setText(housekeeperintroduction.get(0)
								.get("goodPoint").toString());

				TextView_housekeeperidc_address.setText(housekeeperintroduction
						.get(0).get("cityName").toString());

				TextView_housekeeperidc_phone.setText(housekeeperintroduction
						.get(0).get("workTel").toString());

				TextView_housekeeperidc_worktime
						.setText(housekeeperintroduction.get(0).get("workTime")
								.toString());

				TextView_housekeeperidc_introduction
						.setText(housekeeperintroduction.get(0).get("myIntro")
								.toString());

				TextView_housekeeperidc_houseintroduction
						.setText(housekeeperintroduction.get(0)
								.get("houseIntro").toString());

				roundImageView_housekeeperidc_pic
						.setImageBitmap((Bitmap) housekeeperintroduction.get(0)
								.get("headIcon"));
				xh_pDialog.cancel();
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
