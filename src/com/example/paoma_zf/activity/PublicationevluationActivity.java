package com.example.paoma_zf.activity;

import java.util.ArrayList;
import java.util.List;

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
import org.json.JSONObject;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.config.ZfConfig;

public class PublicationevluationActivity extends BaseActivity {

	TextView textView_publication_name, textView_publication_score,
			textView_publication_comment, textView_publication_type,
			textView_publication_address, textView_publication_cannum,
			textView_publication_price;
	EditText editText_publication_message;
	RatingBar ratingBar_publication_scorehousekeeper,
			ratingBar_publication_scorehouse;
	RelativeLayout RelativeLayout_publication_send;
	int num = 100;
	float scorehousekeeper = 0;
	float scorehouse = 0;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publicationevaluation);
		init();
	}

	public void init() {
		editText_publication_message = (EditText) findViewById(R.id.editText_publication_message);
		textView_publication_cannum = (TextView) findViewById(R.id.textView_publication_cannum);
		textView_publication_cannum.setText("还能输入" + num + "字");
		editText_publication_message.addTextChangedListener(new TextWatcher() {

			private CharSequence temp;
			private int selectionStart;
			private int selectionEnd;

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				temp = s;
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				int number = num - s.length();
				// textView_publication_cannum.setText("" + number);
				textView_publication_cannum.setText("还能输入" + number + "字");

				selectionStart = editText_publication_message
						.getSelectionStart();
				selectionEnd = editText_publication_message.getSelectionEnd();
				if (temp.length() > num) {
					s.delete(selectionStart - 1, selectionEnd);
					int tempSelection = selectionEnd;
					editText_publication_message.setText(s);
					editText_publication_message.setSelection(tempSelection);// 设置光标在最后
				}

			}
		});

		textView_publication_name = (TextView) findViewById(R.id.textView_publication_name);
		textView_publication_name.setText(getIntent().getStringExtra(
				"houseName"));

		textView_publication_score = (TextView) findViewById(R.id.textView_publication_score);
		textView_publication_score.setText(getIntent().getStringExtra(
				"avgScore"));

		textView_publication_comment = (TextView) findViewById(R.id.textView_publication_comment);
		textView_publication_comment.setText(getIntent().getStringExtra(
				"totalAppraise"));

		textView_publication_type = (TextView) findViewById(R.id.textView_publication_type);
		textView_publication_type.setText(getIntent().getStringExtra("type"));

		textView_publication_address = (TextView) findViewById(R.id.textView_publication_address);
		textView_publication_address.setText(getIntent().getStringExtra(
				"addressDetails"));

		textView_publication_price = (TextView) findViewById(R.id.textView_publication_price);
		textView_publication_price.setText(getIntent().getStringExtra(
				"paytotal"));

		RelativeLayout_publication_send = (RelativeLayout) findViewById(R.id.RelativeLayout_publication_send);
		RelativeLayout_publication_send
				.setOnClickListener(new PublicationevluationActivityclicklisten());

		ratingBar_publication_scorehousekeeper = (RatingBar) findViewById(R.id.ratingBar_publication_scorehousekeeper);
		ratingBar_publication_scorehousekeeper.setTag("housekeeper");
		ratingBar_publication_scorehousekeeper
				.setOnRatingBarChangeListener(new RatingBarChangeListener());

		ratingBar_publication_scorehouse = (RatingBar) findViewById(R.id.ratingBar_publication_scorehouse);
		ratingBar_publication_scorehouse.setTag("house");
		ratingBar_publication_scorehouse
				.setOnRatingBarChangeListener(new RatingBarChangeListener());
	}

	public void sendComment(String userId, String houseId, String ordersId,
			String content, String housekeeperScore, String houseScore)
			throws Exception {
		// List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);

		jsonObject.put("houseId", houseId);

		jsonObject.put("ordersId", ordersId);

		jsonObject.put("content", content);

		jsonObject.put("housekeeperScore", housekeeperScore);

		jsonObject.put("houseScore", houseScore);

		jsonObject.put("method", "5001");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getComment);

		System.out.println(ss);

		// return hh;
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

	class RatingBarChangeListener implements OnRatingBarChangeListener {
		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {

			if (ratingBar.getTag().equals("housekeeper")) {
				System.out.println("当前分数=" + rating);
				scorehousekeeper = rating;
				System.out.println("scorehousekeeper=" + scorehousekeeper);

			}
			if (ratingBar.getTag().equals("house")) {
				System.out.println("当前分数=" + rating);
				scorehouse = rating;
				System.out.println("scorehouse=" + scorehouse);
			}

		}
	}

	class PublicationevluationActivityclicklisten implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			new Thread(new Runnable() {
				public void run() {

					try {
						String shk = String.valueOf(scorehousekeeper);
						String sh = String.valueOf(scorehouse);

						sendComment("5", getIntent().getStringExtra("houseId"),
								getIntent().getStringExtra("ordersId"),
								editText_publication_message.getText()
										.toString(), shk, sh);
						Toast.makeText(getApplicationContext(), "评论成功",
								Toast.LENGTH_SHORT).show();
						finish();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}).start();

		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
