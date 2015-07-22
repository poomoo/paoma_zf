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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

import com.example.paoma_zf.R;
import com.example.paoma_zf.config.ZfConfig;
import com.example.paoma_zf.view.ListViewForScrollView;

public class MessageListActivity extends Activity {

	ScrollView scrollView_message_sv;
	ListViewForScrollView listViewForScrollView_message_lv;
	List<Map<String, Object>> MessageList;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messagelist);
		init();

	}

	public void init() {
		scrollView_message_sv = (ScrollView) findViewById(R.id.scrollView_message_sv);
		scrollView_message_sv.smoothScrollTo(0, 0);

		listViewForScrollView_message_lv = (ListViewForScrollView) findViewById(R.id.listViewForScrollView_message_lv);
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == 1) {

				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(), MessageList,
						R.layout.style_lvmessage, new String[] { "message" },
						new int[] { R.id.textView_message_title

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

				// if(lv_lookhome.getCount()>0)
				// {
				// lv_lookhome.removeAllViews();
				// }
				listViewForScrollView_message_lv.setAdapter(adapter);

			}
			super.handleMessage(msg);
		}
	};

	public List<Map<String, Object>> getMessageList(String userId)
			throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);

		jsonObject.put("method", "1008");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getUserList);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");
		//
		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject
		//
		JSONArray nameList = rrtt.getJSONArray("systemMessage");// 获取JSONArray
		//

		String[] sysmessage = new String[nameList.length()];

		for (int i = 0; i < nameList.length(); i++) {
			nameList.get(i).toString();
			System.out.println(nameList.get(i).toString());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", nameList.get(i).toString());
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
	protected void onStart() {
		super.onStart();

		new Thread(new Runnable() {
			public void run() {

				try {

					MessageList = getMessageList("5");

					if (MessageList.size() > 0) {
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			finish();

		}
		return super.onKeyDown(keyCode, event);
	}

}
