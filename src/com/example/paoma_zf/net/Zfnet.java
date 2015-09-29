package com.example.paoma_zf.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.paoma_zf.R;
import com.example.paoma_zf.config.ZfConfig;

public class Zfnet {

	public List<Map<String, Object>> addOrderform(String userId,
			String startDt, String endDt, String totalDay, String totalPrice,
			String ordersDt, String houseId, String qty, String unitPrice,
			String leaveMsg, String ordersUserName, String ordersTel,
			String idCardNum, String refTel) throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);
		jsonObject.put("startDt", startDt);
		jsonObject.put("endDt", endDt);
		jsonObject.put("totalDay", totalDay);
		jsonObject.put("totalPrice", totalPrice);
		jsonObject.put("ordersDt", ordersDt);
		jsonObject.put("houseId", houseId);
		jsonObject.put("qty", qty);
		jsonObject.put("unitPrice", unitPrice);
		jsonObject.put("ordersTel", ordersTel);
		jsonObject.put("ordersUserName", ordersUserName);
		jsonObject.put("idCardNum", idCardNum);
		jsonObject.put("refTel", refTel);
		jsonObject.put("leaveMsg", leaveMsg);

		jsonObject.put("method", "4002");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getOrders);
		System.out.println("sssssssssss" + ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("ordersId", result.getString("ordersId"));
		map.put("msg", result.getString("msg"));
		hh.add(map);
		return hh;
	}

	public List<Map<String, Object>> getOrderinfo(String ordersId,
			Context context) throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("ordersId", ordersId);

		jsonObject.put("method", "4004");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getOrders);
		Map<String, Object> map = new HashMap<String, Object>();
		// System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject
		//
		String ttrrf = result.getString("resultList");
		//
		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		System.out.println(rrtt.getString("linkString").toString());
		map.put("linkString", rrtt.getString("linkString"));

		String ordersItemJson = rrtt.getString("ordersItemJson");

		// System.out.println(ordersItemJson);
		JSONObject ItemJson = new JSONObject(ordersItemJson.toString());

		map.put("ordersId", ItemJson.getString("ordersId"));

		map.put("ordersDt", ItemJson.getString("ordersDt"));

		map.put("status", ItemJson.getString("status"));

		map.put("startDt", ItemJson.getString("startDt"));

		map.put("endDt", ItemJson.getString("endDt"));

		map.put("isAppraise", ItemJson.getString("isAppraise"));

		map.put("ordersUserName", ItemJson.getString("ordersUserName"));

		map.put("ordersTel", ItemJson.getString("ordersTel"));

		map.put("houseName", ItemJson.getString("houseName"));

		map.put("leaveMsg", ItemJson.getString("leaveMsg"));
		map.put("idCardNum", ItemJson.getString("idCardNum"));
		map.put("refTel", ItemJson.getString("refTel"));

		Bitmap bm = getBitmap(ItemJson.get("headPic").toString(), context);
		map.put("headPic", bm);

		String houseInOrdersJson = rrtt.getString("houseInOrdersJson");
		JSONObject houseItemJson = new JSONObject(houseInOrdersJson.toString());

		map.put("currentPrice", houseItemJson.getString("currentPrice"));

		map.put("type",
				houseItemJson.getString("houseType")
						+ houseItemJson.getString("roomType")
						+ houseItemJson.getString("square") + "平米");

		map.put("housekeeperName", houseItemJson.getString("housekeeperName"));
		map.put("housekeeperTel", houseItemJson.getString("housekeeperTel"));

		map.put("avgScore", houseItemJson.getString("avgScore"));

		map.put("totalAppraise", houseItemJson.getString("totalAppraise"));

		map.put("id", houseItemJson.getString("id"));

		map.put("addressDetails", houseItemJson.getString("addressDetails"));
		hh.add(map);

		return hh;
	}

	public List<Map<String, Object>> getOrderformList(String userId,
			Context context) throws Exception {

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
					.toString(), context);
			map.put("headPic", bm);

			hh.add(map);

		}

		return hh;
	}

	public List<Map<String, Object>> getDiscussionInfo(String dynamicId,
			Context context) throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("dynamicId", dynamicId);

		jsonObject.put("method", "8008");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getDiscussion);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");

		result = new JSONObject(ttrrf.toString());// 转换为JSONObject
		Map<String, Object> map = new HashMap<String, Object>();

		Bitmap bm = getBitmap(result.get("headPic").toString(), context);
		map.put("headPic", bm);

		// if (!result.get("headPic").equals("")) {
		//
		// } else {
		// Resources r = this.getResources();
		// InputStream is = r.openRawResource(R.drawable.ic_launcher);
		// BitmapDrawable bmpDraw = new BitmapDrawable(is);
		// Bitmap bm = bmpDraw.getBitmap();
		// map.put("headPic", bm);
		// }

		map.put("userName", result.get("userName"));

		map.put("levelName", result.get("levelName"));

		map.put("cityName", result.get("cityName"));

		map.put("insertDt", result.get("insertDt"));

		map.put("content", result.get("content"));

		map.put("praiseTotal", result.get("praiseTotal"));

		map.put("commentTotal", result.get("commentTotal"));

		hh.add(map);

		return hh;
	}

	public List<Map<String, Object>> getpariselist(String dynamicId)
			throws Exception {
		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("dynamicId", dynamicId);

		jsonObject.put("method", "8006");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getDiscussion);

		// System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");

		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		JSONArray nameList = rrtt.getJSONArray("praiseJsonArray");// 获取JSONArray

		for (int i = 0; i < nameList.length(); i++) {// 遍历JSONArray

			// JSONObject oj = nameList.getJSONObject(i);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dynamicId", nameList.getJSONObject(i).get("dynamicId"));
			map.put("id", nameList.getJSONObject(i).get("id"));
			map.put("praiseDt", nameList.getJSONObject(i).get("praiseDt"));
			map.put("userName", nameList.getJSONObject(i).get("userName"));
			map.put("userId", nameList.getJSONObject(i).get("userId"));

			hh.add(map);

		}

		return hh;

	}

	public List<Map<String, Object>> getreplylist(String dynamicId)
			throws Exception {
		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("dynamicId", dynamicId);

		jsonObject.put("method", "8007");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getDiscussion);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");

		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		JSONArray nameList = rrtt.getJSONArray("commentJsonArray");// 获取JSONArray

		for (int i = 0; i < nameList.length(); i++) {// 遍历JSONArray

			// JSONObject oj = nameList.getJSONObject(i);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dynamicId", nameList.getJSONObject(i).get("dynamicId"));
			map.put("id", nameList.getJSONObject(i).get("id"));
			map.put("commentDt", nameList.getJSONObject(i).get("commentDt"));
			map.put("userName", nameList.getJSONObject(i).get("userName"));
			map.put("userId", nameList.getJSONObject(i).get("userId"));
			map.put("content", nameList.getJSONObject(i).get("content"));

			hh.add(map);

		}

		return hh;
	}

	public void addparise(String dynamicId, String userId) throws Exception {
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("dynamicId", dynamicId);
		jsonObject.put("userId", userId);
		jsonObject.put("method", "8004");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getDiscussion);

		// System.out.println(ss);

	}

	public void addComment(String dynamicId, String userId, String content)
			throws Exception {
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("dynamicId", dynamicId);
		jsonObject.put("userId", userId);
		jsonObject.put("content", content);
		jsonObject.put("method", "8003");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getDiscussion);

		System.out.println(ss);

	}

	public List<Map<String, Object>> toSubmitOrders(String houseId,
			Context context) throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("houseId", houseId);

		jsonObject.put("method", "4001");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getOrders);
		Map<String, Object> map = new HashMap<String, Object>();
		// System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject
		//
		String ttrrf = result.getString("resultList");
		//
		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		map.put("holdNum", rrtt.getString("holdNum"));

		map.put("currentPrice", rrtt.getString("currentPrice"));

		map.put("id", rrtt.getString("id"));

		map.put("workTel", rrtt.getString("workTel"));

		map.put("addressDetails", rrtt.getString("addressDetails"));

		map.put("housekeeperTel", rrtt.getString("housekeeperTel"));

		map.put("totalFloor", rrtt.getString("totalFloor"));

		map.put("houseType", rrtt.getString("houseType"));

		map.put("name", rrtt.getString("name"));

		map.put("workTime", rrtt.getString("workTime"));

		map.put("square", rrtt.getString("square"));

		map.put("floorNum", rrtt.getString("floorNum"));

		map.put("housekeeperName", rrtt.getString("housekeeperName"));

		hh.add(map);

		return hh;
	}

	public String login(String username, String password, String url) {
		HttpClient httpClient = new DefaultHttpClient();

		String userId = "";
		String rsCode = "";
		String msgg = "";

		try {

			System.out.println("进入");
			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("userName", username);
			jsonObject.put("password", password);
			jsonObject.put("method", "1002");

			nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
					.toString()));

			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

			// TODO 状态处理 500 200
			int res = 0;

			res = httpClient.execute(httpPost).getStatusLine().getStatusCode();

			if (res == 200) {

				HttpResponse httpResponse = httpClient.execute(httpPost);
				StringBuilder builder = new StringBuilder();
				BufferedReader bufferedReader2 = new BufferedReader(
						new InputStreamReader(httpResponse.getEntity()
								.getContent()));
				String str2 = "";
				for (String s = bufferedReader2.readLine(); s != null; s = bufferedReader2
						.readLine()) {
					System.out.println(s);
					System.out.println("取值");
					builder.append(s);
				}

				// JSONObject jsonObject3 = new
				// JSONObject(builder.toString()).getJSONObject("");
				JSONObject jsonObject3 = new JSONObject(builder.toString());

				rsCode = jsonObject3.getString("rsCode");
				System.out.println(rsCode);
				msgg = jsonObject3.getString("msg");
				System.out.println(msgg);
				userId = jsonObject3.getString("userId");
				System.out.println("userId" + userId);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("异常");
		}
		if (rsCode.equals("true")) {
			System.out.println("userId" + userId);
			return userId;
		} else {
			return "0";
		}

	}

	public List<Map<String, Object>> getUserInfo(String userId, String url,
			Context context) throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);
		jsonObject.put("method", "1004");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, url);
		System.out.println("userinfo" + ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");

		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		// String gg=rrtt.getString("email");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", rrtt.getString("email"));
		map.put("levelId", rrtt.getString("levelId"));
		map.put("levelName", rrtt.getString("levelName"));
		map.put("tel", rrtt.getString("tel"));
		map.put("userId", rrtt.getString("userId"));
		map.put("userName", rrtt.getString("userName"));
		map.put("address", rrtt.getString("address"));
		map.put("type", rrtt.getString("type"));

		Bitmap bm = getBitmap(rrtt.getString("headIcon").toString(), context);
		map.put("headIcon", bm);

		hh.add(map);
		return hh;

	}

	public List<Map<String, Object>> getHouseList(String btime, String etime,
			String roomType, String houseType, String orderByValue,
			String orderType, String cityName, String areaName, Context context)
			throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("currPage", "1");
		jsonObject.put("pageSize", "15");
		jsonObject.put("startDt", btime);
		jsonObject.put("endDt", etime);
		jsonObject.put("roomType", roomType);
		jsonObject.put("houseType", houseType);
		jsonObject.put("orderByValue", orderByValue);
		jsonObject.put("orderType", orderType);

		jsonObject.put("cityName", cityName);
		jsonObject.put("areaName", areaName);

		jsonObject.put("method", "3001");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.gethouseList);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");

		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		JSONArray nameList = rrtt.getJSONArray("records");// 获取JSONArray

		for (int i = 0; i < nameList.length(); i++) {// 遍历JSONArray

			// System.out.println(i);

			// JSONObject oj = nameList.getJSONObject(i);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaName", nameList.getJSONObject(i).get("areaName"));
			map.put("currentPrice",
					nameList.getJSONObject(i).get("currentPrice"));
			map.put("name", nameList.getJSONObject(i).get("name"));
			map.put("avgScore", nameList.getJSONObject(i).get("avgScore"));
			map.put("addressDetails",
					nameList.getJSONObject(i).get("addressDetails"));
			map.put("id", nameList.getJSONObject(i).get("id"));
			map.put("totalOrdersDay",
					"已定" + nameList.getJSONObject(i).get("totalOrdersDay")
							+ "晚");
			map.put("roomType", nameList.getJSONObject(i).get("roomType"));

			map.put("collect", nameList.getJSONObject(i).get("collect"));

			map.put("collectInfo", nameList.getJSONObject(i).get("id") + ","
					+ nameList.getJSONObject(i).get("collect"));

			Bitmap bm = getBitmap(nameList.getJSONObject(i).get("headPic")
					.toString(), context);
			map.put("headPic", bm);

			hh.add(map);

		}

		return hh;
	}

	public List<Map<String, Object>> getHouseList(String userId, String btime,
			String etime, String roomType, String houseType,
			String orderByValue, String orderType, String cityName,
			String areaName, Context context) throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("currPage", "1");
		jsonObject.put("pageSize", "15");
		jsonObject.put("startDt", btime);
		jsonObject.put("endDt", etime);
		jsonObject.put("roomType", roomType);
		jsonObject.put("houseType", houseType);
		jsonObject.put("orderByValue", orderByValue);
		jsonObject.put("orderType", orderType);

		jsonObject.put("cityName", cityName);
		jsonObject.put("areaName", areaName);

		jsonObject.put("userId", userId);

		jsonObject.put("method", "3001");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.gethouseList);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");

		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		JSONArray nameList = rrtt.getJSONArray("records");// 获取JSONArray

		for (int i = 0; i < nameList.length(); i++) {// 遍历JSONArray

			// System.out.println(i);

			// JSONObject oj = nameList.getJSONObject(i);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("areaName", nameList.getJSONObject(i).get("areaName"));
			map.put("currentPrice",
					nameList.getJSONObject(i).get("currentPrice"));
			map.put("name", nameList.getJSONObject(i).get("name"));
			map.put("avgScore", nameList.getJSONObject(i).get("avgScore"));
			map.put("addressDetails",
					nameList.getJSONObject(i).get("addressDetails"));
			map.put("id", nameList.getJSONObject(i).get("id"));
			map.put("totalOrdersDay",
					"已定" + nameList.getJSONObject(i).get("totalOrdersDay")
							+ "晚");
			map.put("roomType", nameList.getJSONObject(i).get("roomType"));

			map.put("collect", nameList.getJSONObject(i).get("collect"));

			map.put("collectInfo", nameList.getJSONObject(i).get("id") + ","
					+ nameList.getJSONObject(i).get("collect"));

			Bitmap bm = getBitmap(nameList.getJSONObject(i).get("headPic")
					.toString(), context);

			map.put("headPic", bm);

			hh.add(map);

		}

		return hh;
	}

	public List<Map<String, Object>> getHousecommentList(String houseId,
			Context context) throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("houseId", houseId);
		jsonObject.put("currPage", "1");
		jsonObject.put("pageSize", "15");
		jsonObject.put("method", "5002");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getComment);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");
		// // //
		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		// String orderresult=rrtt.getString("records");

		JSONArray orderresult = rrtt.getJSONArray("records");
		System.out.println(orderresult.toString());

		for (int i = 0; i < orderresult.length(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("appraiseDt", orderresult.getJSONObject(i)
					.get("appraiseDt"));

			map.put("content", orderresult.getJSONObject(i).get("content"));

			map.put("userName", orderresult.getJSONObject(i).get("userName"));

			// map.put("ordersId",
			// orderresult.getJSONObject(i).get("ordersId"));
			//
			// map.put("totalPrice", orderresult.getJSONObject(i)
			// .get("totalPrice"));
			//
			// map.put("totalPrice", orderresult.getJSONObject(i)
			// .get("totalPrice"));
			//
			// Bitmap bm = getBitmap(orderresult.getJSONObject(i).get("headPic")
			// .toString(), context);
			// map.put("headPic", bm);

			hh.add(map);

		}

		return hh;
	}

	public List<Map<String, Object>> getHouseKeeper(Context context)
			throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("pageSize", "15");
		jsonObject.put("currPage", "1");
		jsonObject.put("method", "7001");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getHouseKeeper);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject
		// // //
		String ttrrf = result.getString("resultList");
		// System.out.println(ttrrf);
		// // //
		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject
		// //

		JSONArray housejsonarry = rrtt.getJSONArray("records");
		for (int i = 0; i < housejsonarry.length(); i++) {// 遍历JSONArray

			// JSONObject oj = nameList.getJSONObject(i);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("avgHousekeeperScore",
					housejsonarry.getJSONObject(i).get("avgHousekeeperScore"));

			map.put("houseName", housejsonarry.getJSONObject(i)
					.get("houseName"));
			map.put("managerName",
					housejsonarry.getJSONObject(i).get("managerName"));

			map.put("managerId", housejsonarry.getJSONObject(i)
					.get("managerId"));
			map.put("myIntro", housejsonarry.getJSONObject(i).get("myIntro"));

			Bitmap bm = getBitmap(housejsonarry.getJSONObject(i)
					.get("headIcon").toString(), context);
			map.put("headIcon", bm);

			hh.add(map);

		}

		return hh;
	}

	public List<Map<String, Object>> getDiscussionList(Context context)
			throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("pageSize", "15");
		jsonObject.put("currPage", "1");
		jsonObject.put("method", "8002");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getDiscussion);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");

		result = new JSONObject(ttrrf.toString());// 转换为JSONObject

		JSONArray housejsonarry = result.getJSONArray("records");

		for (int i = 0; i < housejsonarry.length(); i++) {// 遍历JSONArray

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("commentTotal",
					housejsonarry.getJSONObject(i).get("commentTotal"));

			map.put("content", housejsonarry.getJSONObject(i).get("content"));

			map.put("dynamicId", housejsonarry.getJSONObject(i)
					.get("dynamicId"));

			map.put("insertDt", housejsonarry.getJSONObject(i).get("insertDt"));

			map.put("levelName", housejsonarry.getJSONObject(i)
					.get("levelName"));

			map.put("praiseTotal",
					housejsonarry.getJSONObject(i).get("praiseTotal"));

			// map.put("replyTotal",
			// housejsonarry.getJSONObject(i).get("replyTotal"));

			map.put("userId", housejsonarry.getJSONObject(i).get("userId"));

			map.put("userName", housejsonarry.getJSONObject(i).get("userName"));

			Bitmap bm = getBitmap(housejsonarry.getJSONObject(i).get("headPic")
					.toString(), context);
			map.put("headPic", bm);

			map.put("dz", "dz");
			map.put("hf", "hf");

			hh.add(map);

		}

		return hh;
	}

	public void addCommentDiscussion(String userId, String content)
			throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);
		jsonObject.put("content", content);
		jsonObject.put("method", "8001");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getDiscussion);

		// System.out.println(ss);

	}

	public void addfeedback(String userId, String content, String tel)
			throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);
		jsonObject.put("content", content);
		jsonObject.put("tel", tel);
		jsonObject.put("method", "1009 ");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getUserList);

		System.out.println(ss);

	}

	public List<Map<String, Object>> getHouseKeeperintroduction(
			String managerId, Context context) throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("managerId", managerId);

		jsonObject.put("method", "7002");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getHouseKeeper);

		// System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject
		// // // //
		String ttrrf = result.getString("resultList");

		JSONObject housekeeperjson = new JSONObject(ttrrf.toString());
		System.out.println(ttrrf);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityName", housekeeperjson.get("cityName"));

		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		map.put("cityName", housekeeperjson.get("cityName"));

		map.put("managerName", housekeeperjson.get("managerName"));

		map.put("workTel", housekeeperjson.get("workTel"));

		map.put("workTime", housekeeperjson.get("workTime"));

		map.put("goodPoint", housekeeperjson.get("goodPoint"));

		map.put("houseIntro", housekeeperjson.get("houseIntro"));

		map.put("myIntro", housekeeperjson.get("myIntro"));

		Bitmap bm = getBitmap(housekeeperjson.get("headIcon").toString(),
				context);
		map.put("headIcon", bm);

		hh.add(map);

		return hh;
	}

	public List<Map<String, Object>> getHouseInfo(String Id, Context context)
			throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("id", Id);

		jsonObject.put("method", "3002");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.gethouseList);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject
		//
		String ttrrf = result.getString("houseJson");
		//
		JSONObject rrtt = new JSONObject(ttrrf.toString());// 转换为JSONObject

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("currentPrice", rrtt.getString("currentPrice"));
		map.put("floor",
				rrtt.getString("floorNum") + "/" + rrtt.getString("totalFloor"));
		map.put("square", rrtt.getString("square") + "平米");
		map.put("roomType", rrtt.getString("roomType"));
		// //装修类型
		map.put("decorateType", rrtt.getString("decorateType"));
		// //朝向类型
		map.put("aspectType", rrtt.getString("aspectType"));

		map.put("houseType", rrtt.getString("houseType"));

		map.put("housekeeperId", rrtt.getString("housekeeperId"));

		map.put("id", rrtt.getString("id"));

		map.put("appraiseNum", rrtt.getString("appraiseNum") + "条评论");
		map.put("houseAvgScore", rrtt.getString("houseAvgScore") + "分");

		JSONArray jsonArray = new JSONArray(rrtt.getString("picList"));
		// JSONArray nameList = rrtt.getJSONArray("picList");// 获取JSONArray
		String[] urls = new String[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {
			urls[i] = jsonArray.get(i).toString();
		}
		map.put("picUrls", urls);

		Bitmap[] bitmap = new Bitmap[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {

			Bitmap bm = getBitmap(jsonArray.get(i).toString(), context);
			bitmap[i] = bm;

		}

		map.put("picList", bitmap);

		jsonArray = new JSONArray(rrtt.getString("propsJson"));
		// JSONArray nameList = rrtt.getJSONArray("picList");// 获取JSONArray
		String[] jj = new String[jsonArray.length()];

		for (int i = 0; i < jsonArray.length(); i++) {
			jj[i] = jsonArray.get(i).toString();
		}

		map.put("propsJson", jj);

		String pl = result.getString("appraiseJson");

		result = new JSONObject(pl.toString());// 转换为JSONObject

		String userName = result.getString("userName");

		String content = result.getString("content");

		map.put("userName", userName);
		map.put("content", content);

		hh.add(map);

		return hh;
	}

	public List<Map<String, Object>> getBrowserecordlist(String userId,
			Context context) throws Exception {
		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);

		jsonObject.put("method", "6002");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getBrowserecord);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");

		result = new JSONObject(ttrrf.toString());// 转换为JSONObject

		ttrrf = result.getString("records");

		JSONArray recordList = result.getJSONArray("records");// 获取JSONArray

		for (int i = 0; i < recordList.length(); i++) {
			JSONObject res = recordList.getJSONObject(i).getJSONObject(
					"houseItem");
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("areaName", res.get("areaName"));
			map.put("currentPrice", res.get("currentPrice"));
			map.put("name", res.get("name"));
			map.put("avgScore", res.get("avgScore"));
			map.put("addressDetails", res.get("addressDetails"));
			map.put("id", res.get("id"));
			map.put("totalOrdersDay", "已定" + res.get("totalOrdersDay") + "晚");
			map.put("roomType", res.get("roomType"));

			Bitmap bm = getBitmap(res.get("headPic").toString(), context);
			map.put("headPic", bm);

			map.put("collect", res.get("collect"));

			map.put("collectInfo", res.get("id") + "," + res.get("collect"));

			hh.add(map);

		}

		return hh;

	}

	public List<Map<String, Object>> getaboutus() throws Exception {
		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("method", "1012");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getUserList);

		System.out.println(ss);

		// JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject
		//
		// String ttrrf = result.getString("resultList");
		//
		//
		// result = new JSONObject(ttrrf.toString());// 转换为JSONObject
		//
		//
		// ttrrf=result.getString("records");
		//
		//
		//
		//
		// JSONArray recordList = result.getJSONArray("records");// 获取JSONArray

		// for(int i=0;i<recordList.length();i++)
		// {
		// JSONObject res =
		// recordList.getJSONObject(i).getJSONObject("houseItem");
		// Map<String, Object> map = new HashMap<String, Object>();
		//
		//
		//
		//
		//
		// map.put("areaName",res.get("areaName"));
		// map.put("currentPrice",
		// res.get("currentPrice"));
		// map.put("name", res.get("name"));
		// map.put("avgScore", res.get("avgScore"));
		// map.put("addressDetails",
		// res.get("addressDetails"));
		// map.put("id", res.get("id"));
		// map.put("totalOrdersDay",
		// "已定" + res.get("totalOrdersDay")
		// + "晚");
		// map.put("roomType", res.get("roomType"));
		//
		// Bitmap bm = getBitmap(res.get("headPic")
		// .toString(), context);
		// map.put("headPic", bm);
		//
		// hh.add(map);
		//
		//
		// }

		return hh;

	}

	public List<Map<String, Object>> addBrowserecord(String userId,
			String houseId) throws Exception {
		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);
		jsonObject.put("houseId", houseId);

		jsonObject.put("method", "6001");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getBrowserecord);

		System.out.println(ss);

		return hh;

	}

	public List<Map<String, Object>> getCollectlist(String userId,
			Context context) throws Exception {
		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);

		jsonObject.put("method", "9003");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getCollection);

		System.out.println(ss);

		JSONObject result = new JSONObject(ss.toString());// 转换为JSONObject

		String ttrrf = result.getString("resultList");

		result = new JSONObject(ttrrf.toString());// 转换为JSONObject

		ttrrf = result.getString("records");

		JSONArray recordList = result.getJSONArray("records");// 获取JSONArray

		for (int i = 0; i < recordList.length(); i++) {
			// JSONObject res =
			// recordList.getJSONObject(i).getJSONObject("houseItem");
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("areaName", recordList.getJSONObject(i).get("areaName"));

			map.put("currentPrice",
					recordList.getJSONObject(i).get("currentPrice"));
			map.put("name", recordList.getJSONObject(i).get("name"));
			map.put("avgScore", recordList.getJSONObject(i).get("avgScore"));
			map.put("addressDetails",
					recordList.getJSONObject(i).get("addressDetails"));
			map.put("id", recordList.getJSONObject(i).get("id"));
			map.put("totalOrdersDay",
					"已定" + recordList.getJSONObject(i).get("totalOrdersDay")
							+ "晚");
			map.put("roomType", recordList.getJSONObject(i).get("roomType"));

			Bitmap bm = getBitmap(recordList.getJSONObject(i).get("headPic")
					.toString(), context);
			map.put("headPic", bm);

			map.put("collect", recordList.getJSONObject(i).get("collect"));

			map.put("collectInfo", recordList.getJSONObject(i).get("id") + ","
					+ recordList.getJSONObject(i).get("collect"));

			hh.add(map);

		}

		return hh;

	}

	public void addcollection(String userId, String houseId) throws Exception {

		List<Map<String, Object>> hh = new ArrayList<Map<String, Object>>();

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userId", userId);
		jsonObject.put("houseId", houseId);
		jsonObject.put("method", "9001");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		String ss = doPost(nameValuePair, ZfConfig.getCollection);

		// System.out.println(ss);

	}

	public static boolean putHeadPic(String path, String url) {
		URL imgUrl = null;
		Bitmap bitmap = null;
		try {
			imgUrl = new URL(url);
			// 使用HttpURLConnection打开连接
			HttpURLConnection urlConn = (HttpURLConnection) imgUrl
					.openConnection();
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.connect();
			// 将得到的数据转化成InputStream
			InputStream is = urlConn.getInputStream();

			// 将InputStream转换成Bitmap
			bitmap = BitmapFactory.decodeStream(is);

			is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("[getNetWorkBitmap->]MalformedURLException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[getNetWorkBitmap->]IOException");
			e.printStackTrace();
		}
		return true;
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

	@SuppressLint("NewApi")
	public static Bitmap getNetWorkBitmap(String urlString) {
		URL imgUrl = null;
		Bitmap bitmap = null;
		try {
			imgUrl = new URL(urlString);
			// 使用HttpURLConnection打开连接
			HttpURLConnection urlConn = (HttpURLConnection) imgUrl
					.openConnection();
			urlConn.setDoInput(true);
			urlConn.connect();
			// 将得到的数据转化成InputStream
			InputStream is = urlConn.getInputStream();

			// 将InputStream转换成Bitmap
			bitmap = BitmapFactory.decodeStream(is);

			is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("[getNetWorkBitmap->]MalformedURLException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[getNetWorkBitmap->]IOException");
			e.printStackTrace();
		}
		return bitmap;
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
		} else {

		}

		return null;
	}

	public static Bitmap getcollectBitmap(String iscollect, Context context)
			throws IOException {

		System.out.println("xxxxxxxxxxxxx" + iscollect);
		if (iscollect.equals("true")) {
			Resources r = context.getApplicationContext().getResources();
			InputStream is = r.openRawResource(R.drawable.ic_collection_down);
			BitmapDrawable bmpDraw = new BitmapDrawable(is);
			Bitmap bm = bmpDraw.getBitmap();
			return bm;
		} else {
			Resources r = context.getApplicationContext().getResources();
			InputStream is = r.openRawResource(R.drawable.ic_collection_up);
			BitmapDrawable bmpDraw = new BitmapDrawable(is);
			Bitmap bm = bmpDraw.getBitmap();
			return bm;
		}

	}

	public static Bitmap getBitmap(String path, Context context)
			throws IOException {

		try {
			if (path.length() > 0) {
				URL url = new URL(path);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();

				conn.setConnectTimeout(500);
				conn.setRequestMethod("GET");

				if (conn.getResponseCode() == 200) {

					InputStream inputStream = conn.getInputStream();

					Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

					return bitmap;
				}
			} else {
				Resources r = context.getApplicationContext().getResources();
				InputStream is = r.openRawResource(R.drawable.ic_launcher);
				BitmapDrawable bmpDraw = new BitmapDrawable(is);
				Bitmap bm = bmpDraw.getBitmap();
				return bm;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		Resources r = context.getApplicationContext().getResources();
		InputStream is = r.openRawResource(R.drawable.ic_launcher);
		BitmapDrawable bmpDraw = new BitmapDrawable(is);
		Bitmap bm = bmpDraw.getBitmap();
		return bm;
		// return null;
	}

	// 获取验证码
	public static boolean getIdentitynumber(String phonenumber, String url) {
		System.out.println("url:"+url);
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("userName", phonenumber);
			jsonObject.put("method", "1005");

			nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
					.toString()));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

			HttpResponse httpResponse = httpClient.execute(httpPost);

			// 判断是够请求成功
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				// 获取返回的数据
				// result = EntityUtils.toString(httpResponse.getEntity(),
				// "UTF-8");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
		}
		return false;
	}

	// 获取随机许可号
	public static String getLicenseNumber(String url) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();
		String result = "";

		jsonObject.put("method", "1013");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

		HttpResponse httpResponse = httpClient.execute(httpPost);

		// 判断是够请求成功
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			// 获取返回的数据
			result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		}

		jsonObject = new JSONObject(result.toString());
		return jsonObject.getString("licenseNo");
	}

	// 检查手机号是否可用
	public static boolean checkPhoneIsUsed(String phoneNum, String url)
			throws Exception {
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();
		String result = "";

		jsonObject.put("tel", phoneNum);
		jsonObject.put("method", "1015");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

		HttpResponse httpResponse = httpClient.execute(httpPost);

		// 判断是够请求成功
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			// 获取返回的数据
			result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			Log.i("checkPhoneIsUsed", result);
		}

		jsonObject = new JSONObject(result.toString());
		String rscode = jsonObject.getString("rsCode");
		// true--表示手机号可以用 false--表示不可用
		if (rscode.equals("true"))
			return true;

		return false;
	}

	/**
	 * 
	 * 
	 * @Title: checkcode
	 * @Description: TODO 校验手机验证码
	 * @author 李苜菲
	 * @return
	 * @return Boolean
	 * @throws
	 * @date 2015-7-21下午3:03:37
	 */
	public static Boolean checkcode(String userName, String code, String url)
			throws Exception {

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("userName", userName);
		jsonObject.put("code", code);
		jsonObject.put("method", "1006");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));

		String result = null;
		// 获取HttpClient对象
		HttpClient httpClient = new DefaultHttpClient();
		// 新建HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		if (nameValuePair != null) {
			// 设置字符集
			HttpEntity entity = new UrlEncodedFormEntity(nameValuePair,
					HTTP.UTF_8);
			// 设置参数实体
			httpPost.setEntity(entity);
		}

		// 获取HttpResponse实例
		HttpResponse httpResp = httpClient.execute(httpPost);
		// 判断是够请求成功
		if (httpResp.getStatusLine().getStatusCode() == 200) {
			// 获取返回的数据
			result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
		}
		JSONObject jsonObject2 = new JSONObject(result.toString());

		String rscode = jsonObject2.getString("rsCode");

		if (rscode.equals("true"))
			return true;
		return false;
	}

	/**
	 * 
	 * 
	 * @Title: checkLicenseNum
	 * @Description: TODO 校验许可号
	 * @author 李苜菲
	 * @return
	 * @return Boolean
	 * @throws
	 * @date 2015-7-21下午3:06:02
	 */
	public static Boolean checkLicenseNum(String licenseNo, String url)
			throws Exception {

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("licenseNo", licenseNo);
		jsonObject.put("method", "1014");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));

		String result = null;
		// 获取HttpClient对象
		HttpClient httpClient = new DefaultHttpClient();
		// 新建HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		if (nameValuePair != null) {
			// 设置字符集
			HttpEntity entity = new UrlEncodedFormEntity(nameValuePair,
					HTTP.UTF_8);
			// 设置参数实体
			httpPost.setEntity(entity);
		}

		// 获取HttpResponse实例
		HttpResponse httpResp = httpClient.execute(httpPost);
		// 判断是够请求成功
		if (httpResp.getStatusLine().getStatusCode() == 200) {
			// 获取返回的数据
			result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
		}
		JSONObject jsonObject2 = new JSONObject(result.toString());

		String rscode = jsonObject2.getString("rsCode");

		if (rscode.equals("true"))
			return true;
		return false;
	}

	public static Boolean resetpassword(String tel, String password, String url)
			throws Exception {

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("password", password);
		jsonObject.put("tel", tel);
		jsonObject.put("method", "1017");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));

		System.out.println("resetpassword:" + jsonObject);

		String result = null;
		// 获取HttpClient对象
		HttpClient httpClient = new DefaultHttpClient();
		// 新建HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		if (nameValuePair != null) {
			// 设置字符集
			HttpEntity entity = new UrlEncodedFormEntity(nameValuePair,
					HTTP.UTF_8);
			// 设置参数实体
			httpPost.setEntity(entity);
		}

		// 获取HttpResponse实例
		HttpResponse httpResp = httpClient.execute(httpPost);
		// 判断是够请求成功
		if (httpResp.getStatusLine().getStatusCode() == 200) {
			// 获取返回的数据
			result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
		}
		System.out.println("result:" + result);
		JSONObject jsonObject2 = new JSONObject(result.toString());

		String rscode = jsonObject2.getString("rsCode");

		if (rscode.equals("true"))
			return true;
		return false;
	}

	public static String sendEmail(String email, String url) throws Exception {

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("email", email);
		jsonObject.put("method", "1016");

		nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
				.toString()));

		System.out.println("sendEmail:" + jsonObject);

		String result = null;
		// 获取HttpClient对象
		HttpClient httpClient = new DefaultHttpClient();
		// 新建HttpPost对象
		HttpPost httpPost = new HttpPost(url);
		if (nameValuePair != null) {
			// 设置字符集
			HttpEntity entity = new UrlEncodedFormEntity(nameValuePair,
					HTTP.UTF_8);
			// 设置参数实体
			httpPost.setEntity(entity);
		}

		// 获取HttpResponse实例
		HttpResponse httpResp = httpClient.execute(httpPost);
		// 判断是够请求成功
		if (httpResp.getStatusLine().getStatusCode() == 200) {
			// 获取返回的数据
			result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
		}
		System.out.println("result:" + result);
		JSONObject jsonObject2 = new JSONObject(result.toString());

		String rscode = jsonObject2.getString("rsCode");
		String msg = jsonObject2.getString("msg");
		return rscode + "|" + msg;
	}

	/**
	 * 
	 * 
	 * @Title: uploadUserInfo
	 * @Description: TODO 上传修改的用户信息
	 * @author 李苜菲
	 * @return
	 * @return boolean
	 * @throws
	 * @date 2015-7-23下午4:02:22
	 */
	public static boolean uploadUserInfo(String userId, String userName,
			String address, String email, File file, String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

		JSONObject jsonObject = new JSONObject();

		try {
			// 放入文件
			jsonObject.put("userId", userId);
			jsonObject.put("userName", userName);
			jsonObject.put("address", address);
			jsonObject.put("email", email);
			jsonObject.put("method", "1019");
			
  
			
			nameValuePair.add(new BasicNameValuePair("jsonData", jsonObject
					.toString()));

			if (nameValuePair != null) {
				// 设置字符集
				HttpEntity entity = new UrlEncodedFormEntity(nameValuePair,
						HTTP.UTF_8);
				// 设置参数实体
				httpPost.setEntity(entity);
			}

			// 获取HttpResponse实例
			HttpResponse httpResp = httpClient.execute(httpPost);
			// 判断是够请求成功
			if (httpResp.getStatusLine().getStatusCode() == 200) {
				// 获取返回的数据
				String result = EntityUtils.toString(httpResp.getEntity(),
						"UTF-8");
			}

		} catch (Exception e) {
			System.out.println("异常" + e.getMessage().toString());
		}
		return true;
	}
}
