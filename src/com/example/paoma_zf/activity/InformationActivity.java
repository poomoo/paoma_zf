package com.example.paoma_zf.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.MyGestureListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class InformationActivity extends BaseActivity {

	TextView textView_information_money, textView_information_roomtype,
			textView_information_decoratetype, textView_information_square,
			textView_information_aspectType, textView_information_floor,
			textView_information_housetype, textView_information_propsJson,
			textView_information_houseAvgScore,
			textView_information_appraiseNum,
			textView_information_Commentpersonname,
			textView_information_Comment;
	Intent intent;
	Button button_information_tiyan;
	List<Map<String, Object>> HouseList;
	ViewFlipper viewFlipper__information_img;

	// private MyGestureListener detector;
	private static final float FLING_MIN_DISTANCE = 0;
	String Id;

	ZfApplication Zfapp;
	ProgressDialog xh_pDialog;
	// private Bitmap[] bm = null;
	private String[] strImageUrls = null;
	private ArrayList<String> imageUrlsList = null;
	private ImageLoader imageLoader = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		init();

		new Thread(new Runnable() {
			public void run() {

				try {
					Zfnet zz = new Zfnet();
					HouseList = zz.getHouseInfo(Id, getApplicationContext());

					Message message = new Message();

					message.what = 1;
					if (HouseList.size() > 0) {
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

				textView_information_money.setText(HouseList.get(0)
						.get("currentPrice").toString());

				textView_information_roomtype.setText(HouseList.get(0)
						.get("roomType").toString());

				textView_information_decoratetype.setText(HouseList.get(0)
						.get("decorateType").toString());

				textView_information_square.setText(HouseList.get(0)
						.get("square").toString());

				textView_information_aspectType.setText(HouseList.get(0)
						.get("aspectType").toString());

				textView_information_housetype.setText(HouseList.get(0)
						.get("houseType").toString());

				textView_information_houseAvgScore.setText(HouseList.get(0)
						.get("houseAvgScore").toString());

				textView_information_appraiseNum.setText(HouseList.get(0)
						.get("appraiseNum").toString());

				textView_information_Commentpersonname.setText(HouseList.get(0)
						.get("userName").toString());

				textView_information_Comment.setText(HouseList.get(0)
						.get("content").toString());

				String[] ss = (String[]) HouseList.get(0).get("propsJson");
				String s = "";
				for (int i = 0; i < ss.length; i++) {
					s = s + "," + ss[i];
				}

				textView_information_propsJson.setText(s);

				// bm = (Bitmap[]) HouseList.get(0).get("picList");

				strImageUrls = (String[]) HouseList.get(0).get("picUrls");
				imageUrlsList = new ArrayList<String>();

				for (int i = 0; i < strImageUrls.length; i++) {
					viewFlipper__information_img
							.addView(addImageById(strImageUrls[i]));
					imageUrlsList.add(strImageUrls[i]);
				}
				xh_pDialog.cancel();
			}

			if (msg.what == 2) {

			}

			super.handleMessage(msg);
		}
	};

	public void init() {

		Id = getIntent().getStringExtra("Id");
		Zfapp = (ZfApplication) getApplication();
		// iscollect = getIntent().getStringExtra("collect");

		textView_information_money = (TextView) findViewById(R.id.textView_information_money);

		textView_information_roomtype = (TextView) findViewById(R.id.textView_information_roomtype);

		textView_information_decoratetype = (TextView) findViewById(R.id.textView_information_decoratetype);

		textView_information_square = (TextView) findViewById(R.id.textView_information_square);

		textView_information_aspectType = (TextView) findViewById(R.id.textView_information_aspectType);

		textView_information_housetype = (TextView) findViewById(R.id.textView_information_housetype);

		textView_information_propsJson = (TextView) findViewById(R.id.textView_information_propsJson);

		textView_information_houseAvgScore = (TextView) findViewById(R.id.textView_information_houseAvgScore);

		textView_information_appraiseNum = (TextView) findViewById(R.id.textView_information_appraiseNum);
		textView_information_appraiseNum.setTag("housecomment");
		textView_information_appraiseNum
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent();
						i.setClass(getApplicationContext(),
								HousecommentActivity.class);
						i.putExtra("houseId", Id);
						System.out.println("ididid" + Id);
						startActivity(i);
					}
				});

		textView_information_Commentpersonname = (TextView) findViewById(R.id.textView_information_Commentpersonname);

		textView_information_Comment = (TextView) findViewById(R.id.textView_information_Comment);

		viewFlipper__information_img = (ViewFlipper) findViewById(R.id.viewFlipper__information_img);

		final GestureDetector gd = new GestureDetector(new MyGestureListener(
				this, viewFlipper__information_img));

		viewFlipper__information_img.setInAnimation(AnimationUtils
				.loadAnimation(this, android.R.anim.fade_in));
		viewFlipper__information_img.setOutAnimation(AnimationUtils
				.loadAnimation(this, android.R.anim.fade_out));
		viewFlipper__information_img.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）
		viewFlipper__information_img.setFlipInterval(3000);
		if (viewFlipper__information_img.isAutoStart()
				&& !viewFlipper__information_img.isFlipping()) {
			viewFlipper__information_img.startFlipping();
		}

		viewFlipper__information_img.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return gd.onTouchEvent(event);
			}
		});

		viewFlipper__information_img.setLongClickable(true);
		viewFlipper__information_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// viewFlipper__information_img.getCurrentView();
				// ImageView ivv = (ImageView) viewFlipper__information_img
				// .getCurrentView();

				// ivv.setDrawingCacheEnabled(true);
				//
				// Bitmap obmp = Bitmap.createBitmap(ivv.getDrawingCache());
				//
				// ivv.setDrawingCacheEnabled(false);
				//

				int position = viewFlipper__information_img.getDisplayedChild();
				imageBrower(position, imageUrlsList);

				// Intent intent = new Intent(InformationActivity.this,
				// DialogimgActivity.class);
				// intent.putParcelableArrayListExtra("imageList", imageList);
				// intent.putExtra("position", position);
				// startActivity(intent);

			}
		});

		button_information_tiyan = (Button) findViewById(R.id.button_information_tiyan);
		button_information_tiyan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Zfapp.getUserName().toString().trim().equals("")) {
					AlertDialog.Builder builder = new Builder(
							InformationActivity.this);
					builder.setMessage("请先登录");
					builder.setTitle("提示");
					builder.setPositiveButton("确认",
							new Dialog.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
					builder.create().show();
				} else {
					Intent i = new Intent();
					i.putExtra("houseId", Id);
					i.setClass(getApplicationContext(), OrderformActivity.class);
					startActivity(i);
				}

			}
		});

		xh_pDialog = new ProgressDialog(InformationActivity.this);
		// 设置进度条风格，风格为圆形，旋转的
		xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// 设置ProgressDialog 标题
		// xh_pDialog.setTitle("提示");
		// 设置ProgressDialog提示信息
		xh_pDialog.setMessage("请稍后....");
		// 设置ProgressDialog标题图标
		// xh_pDialog.setIcon(R.drawable.ic_launcher);
		// 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
		xh_pDialog.setIndeterminate(false);
		// 设置ProgressDialog 是否可以按退回键取消
		xh_pDialog.setCancelable(true);

		// 让ProgressDialog显示
		xh_pDialog.show();
	}

	protected void imageBrower(int position, ArrayList<String> urls2) {
		Intent intent = new Intent(InformationActivity.this,
				ImagePagerActivity.class);
		// 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
		startActivity(intent);
	}

	public View addImageById(String url) {
		final ImageView iv = new ImageView(this);
		Log.i("InformationActivity", "url:" + url);
		// 使用ImageLoader加载网络图片
		DisplayImageOptions options = new DisplayImageOptions.Builder()//
				.showImageOnLoading(R.drawable.ic_launcher) // 加载中显示的默认图片
				.showImageOnFail(R.drawable.ic_launcher) // 设置加载失败的默认图片
				.cacheInMemory(true) // 内存缓存
				.cacheOnDisk(true) // sdcard缓存
				.bitmapConfig(Config.RGB_565)// 设置最低配置
				.build();//
		imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(url, iv, options);
		iv.setScaleType(ScaleType.FIT_XY);
		// iv.setImageBitmap(bm);
		return iv;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
