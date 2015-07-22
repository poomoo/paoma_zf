package com.example.paoma_zf.activity;

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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.example.paoma_zf.R;
import com.example.paoma_zf.config.ZfConfig;

public class AddressDialog extends Activity {

	LinearLayout LinearLayout_left, LinearLayout_right;
	List<Map<String, Object>> AddressList;
	int select;
	String ccname;
	String cchadAreas;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// ////����Ϊtrue�����������ʧ
		setFinishOnTouchOutside(true);//
		setContentView(R.layout.dialog_address);
		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // Ϊ��ȡ��Ļ����
		android.view.WindowManager.LayoutParams p = getWindow().getAttributes();
		p.height = (int) (d.getHeight() * 0.8); // �߶�����Ϊ��Ļ��0.8
		p.width = (int) (d.getWidth() * 0.8); // �������Ϊ��Ļ��0.7
		p.alpha = 0.6f; // ���ñ���͸����
		p.dimAmount = 0.4f; // ���úڰ���
		getWindow().setAttributes(p);

		init();
	}

	private void init() {
		LinearLayout_left = (LinearLayout) findViewById(R.id.LinearLayout_left);
		LinearLayout_right = (LinearLayout) findViewById(R.id.LinearLayout_right);
		select = 0;
		ccname = "";
	}

	@SuppressLint("ResourceAsColor")
	private void addleftview() {
		for (int i = 0; i < AddressList.size(); i++) {
			Button tt = new Button(getApplicationContext());

			tt.setText(AddressList.get(i).get("cityName").toString());
			tt.setTag(i);
			tt.setOnClickListener(new Addressleftclicklisten());
			LinearLayout.LayoutParams lp = new LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			lp.setMargins(5, 15, 5, 15);

			tt.setLayoutParams(lp);

			LinearLayout_left.addView(tt);
		}

	}

	private void addrightview(int areaid) {
		String[] ii = new String[((String[]) AddressList.get(areaid).get(
				"hadAreas")).length];

		ii = (String[]) AddressList.get(areaid).get("hadAreas");
		for (int i = 0; i < ii.length; i++) {
			Button tt = new Button(getApplicationContext());

			tt.setText(ii[i]);
			tt.setTag(ii[i]);
			tt.setOnClickListener(new Addressrightclicklisten());
			LinearLayout.LayoutParams lp = new LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			lp.setMargins(35, 15, 5, 15);

			tt.setLayoutParams(lp);

			LinearLayout_right.addView(tt);
		}

	}

	public List<Map<String, Object>> getAddressList() throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("method", "10001");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getAddress);

		JSONObject result = new JSONObject(ss.toString());// ת��ΪJSONObject

		JSONArray adsList = result.getJSONArray("resultList");// ��ȡJSONArray

		for (int i = 0; i < adsList.length(); i++) {

			Map<String, Object> map = new HashMap<String, Object>();

			String cityName = adsList.getJSONObject(i).get("cityName")
					.toString();
			map.put("cityName", cityName);

			JSONArray adsList222 = adsList.getJSONObject(i).getJSONArray(
					"hadAreas");
			String[] hadAreas = new String[adsList222.length()];
			for (int j = 0; j < adsList222.length(); j++) {
				System.out.println(adsList222.get(j).toString());
				hadAreas[j] = adsList222.get(j).toString();
			}

			map.put("hadAreas", hadAreas);
			hh.add(map);
		}

		return hh;
	}

	public static String doPost(List<NameValuePair> params, String url)
			throws Exception {
		String result = null;
		// ��ȡHttpClient����
		HttpClient httpClient = new DefaultHttpClient();
		// �½�HttpPost����
		HttpPost httpPost = new HttpPost(url);
		if (params != null) {
			// �����ַ���
			HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			// ���ò���ʵ��
			httpPost.setEntity(entity);
		}

		/*
		 * // ���ӳ�ʱ httpClient.getParams().setParameter(
		 * CoreConnectionPNames.CONNECTION_TIMEOUT, 3000); // ����ʱ
		 * httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
		 * 3000);
		 */
		// ��ȡHttpResponseʵ��
		HttpResponse httpResp = httpClient.execute(httpPost);
		// �ж��ǹ�����ɹ�
		if (httpResp.getStatusLine().getStatusCode() == 200) {
			// ��ȡ���ص�����
			result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
		} else {
			// Log.i("HttpPost", "HttpPost��ʽ����ʧ��");
		}

		// System.out.println(result);
		return result;
	}

	@Override
	protected void onStart() {
		super.onStart();

		new Thread(new Runnable() {
			public void run() {
				try {

					AddressList = getAddressList();

					Message message = new Message();
					message.what = 1;
					myHandler.sendMessage(message);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}).start();
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {
				addleftview();
				addrightview(select);
			}

			if (msg.what == 2) {
				LinearLayout_right.removeAllViews();
				addrightview(select);
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

	class Addressleftclicklisten implements OnClickListener {

		@Override
		public void onClick(View v) {
			select = (Integer) v.getTag();

			Message message = new Message();
			message.what = 2;
			myHandler.sendMessage(message);
		}
	}

	class Addressrightclicklisten implements OnClickListener {

		@Override
		public void onClick(View v) {
			ccname = AddressList.get(select).get("cityName").toString();
			cchadAreas = v.getTag().toString();

			SharedPreferences mySharedPreferences = getSharedPreferences(
					"address", Activity.MODE_PRIVATE);
			// ʵ����SharedPreferences.Editor���󣨵ڶ�����
			SharedPreferences.Editor editor = mySharedPreferences.edit();

			editor.putString("cityName", ccname);
			editor.putString("hadAreas", cchadAreas);

			// �ύ��ǰ����
			editor.commit();

			finish();
		}
	}
}
