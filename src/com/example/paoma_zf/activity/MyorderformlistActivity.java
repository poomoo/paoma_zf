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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.config.ZfConfig;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.ListViewForScrollView;

public class MyorderformlistActivity extends Activity {

	ListViewForScrollView listViewForScrollView_myorderform_orderlist;
	ScrollView scrollView_myorderform_scroll;
	List<Map<String, Object>> OrderList;
	String userId;
	ZfApplication zfapp;
	Zfnet zfnet;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myorderformlist);
		init();
		new Thread(new Runnable() {
			public void run() {

				try {

					// OrderList = getOrderform(userId);

					OrderList = zfnet.getOrderformList(zfapp.getUserId(),
							getApplicationContext());

					if (OrderList.size() > 0) {
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

	private void init() {

		listViewForScrollView_myorderform_orderlist = (ListViewForScrollView) findViewById(R.id.listViewForScrollView_myorderform_orderlist);
		listViewForScrollView_myorderform_orderlist
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						TextView Id = (TextView) arg1
								.findViewById(R.id.textView_lvorderform_number);

						Intent intent = new Intent(
								MyorderformlistActivity.this,
								OrderformInfoActivity.class);
						intent.putExtra("orderformId", Id.getText().toString());

						Toast.makeText(getApplicationContext(),
								Id.getText().toString(), Toast.LENGTH_LONG)
								.show();
						startActivity(intent);
					}
				});

		scrollView_myorderform_scroll = (ScrollView) findViewById(R.id.scrollView_myorderform_scroll);

		scrollView_myorderform_scroll.smoothScrollTo(0, 0);

		zfapp = (ZfApplication) getApplication();
		zfnet = new Zfnet();
		userId = "1";

	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(), OrderList,
						R.layout.style_lvorderform, new String[] { "ordersId",
								"totalPrice", "houseName", "startDt", "endDt",
								"headPic" }, new int[] {
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

						if (view instanceof ImageView && data instanceof Bitmap) {
							ImageView iv = (ImageView) view;
							iv.setImageBitmap((Bitmap) data);
							return true;
						} else
							return false;
					}

				});
				listViewForScrollView_myorderform_orderlist.setAdapter(adapter);

			}
			super.handleMessage(msg);
		}
	};

	public List<Map<String, Object>> getOrderform(String userId)
			throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);
		jsonObject.put("currPage", "1");
		jsonObject.put("pageSize", "15");
		jsonObject.put("method", "4003");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getOrders);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");
		// // //
		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		// String orderresult=rrtt.getString("records");

		JSONArray orderresult = rrtt.getJSONArray("records");

		for (int i = 0; i < orderresult.length(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("endDt", orderresult.getJSONObject(i).get("endDt"));

			map.put("startDt", orderresult.getJSONObject(i).get("startDt"));

			map.put("houseName", orderresult.getJSONObject(i).get("houseName"));

			map.put("ordersId", orderresult.getJSONObject(i).get("ordersId"));

			map.put("totalPrice", orderresult.getJSONObject(i)
					.get("totalPrice"));

			map.put("totalPrice", orderresult.getJSONObject(i)
					.get("totalPrice"));

			Bitmap bm = getBitmap(orderresult.getJSONObject(i).get("headPic")
					.toString());
			map.put("headPic", bm);
			hh.add(map);

		}

		return hh;
	}

	public static Bitmap getBitmap(String path) throws IOException {

		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			return bitmap;
		}
		return null;
	}

	public static String doPost(List<NameValuePair> params, String url)
			throws Exception {
		String result = null;
		// 获取HttpClient对象
		HttpClient httpClient = new DefaultHttpClient();
		// 新建HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		if (params != null) {
			// 设置字符集
			HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			// 设置参数实体
			httpPost.setEntity(entity);
		}

		/*
		 * // 连接超时 httpClient.getParams().setParameter(
		 * CoreConnectionPNames.CONNECTION_TIMEOUT, 3000); // 请求超时
		 * httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
		 * 3000);
		 */
		// 获取HttpResponse实例
		HttpResponse httpResp = httpClient.execute(httpPost);
		// 判断是够请求成功
		if (httpResp.getStatusLine().getStatusCode() == 200) {
			// 获取返回的数据
			result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
		} else {
			// Log.i("HttpPost", "HttpPost方式请求失败");
		}

		// System.out.println(result);
		return result;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
