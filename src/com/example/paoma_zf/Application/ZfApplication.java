package com.example.paoma_zf.Application;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.paoma_zf.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ZfApplication extends Application {

	private List<Activity> activity;
	private static ZfApplication instance;

	private Bitmap bm;
	private String UserName;
	private String userId;
	private String levelId;
	private String levelName;
	private String tel;
	private String email;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return this.levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public static ZfApplication getInstance() {
		if (instance == null) {
			instance = new ZfApplication();
		}
		return instance;
	}

	/**
	 * 得到activity栈
	 * 
	 * @return
	 */
	public List<Activity> getActivity() {
		if (activity == null) {
			activity = new ArrayList<Activity>();
		}
		return activity;
	}

	public Bitmap getBm() {
		return bm;
	}

	public void setBm(Bitmap bm) {
		this.bm = bm;
	}

	@SuppressLint("NewApi")
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		Bitmap mBitmap = BitmapFactory.decodeStream(is);

		System.out.println("btm" + mBitmap.getByteCount());
		setBm(mBitmap); // 初始化全局变量

		userId = "";
		UserName = "";
		levelId = "";

		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder() //
				.showImageForEmptyUri(R.drawable.ic_launcher) //
				.showImageOnFail(R.drawable.ic_launcher) //
				.cacheInMemory(true) //
				.cacheOnDisk(true) //
				.build();//
		ImageLoaderConfiguration config = new ImageLoaderConfiguration//
		.Builder(getApplicationContext())//
				.defaultDisplayImageOptions(defaultOptions)//
				.discCacheSize(50 * 1024 * 1024)//
				.discCacheFileCount(10)// 缓存一百张图片
				.writeDebugLogs()//
				.build();//
		ImageLoader.getInstance().init(config);
	}

}
