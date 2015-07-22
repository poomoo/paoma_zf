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
		viewFlipper__information_img.setAutoStart(true); // �����Զ����Ź��ܣ�����¼���ǰ�Զ����ţ�
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
					builder.setMessage("���ȵ�¼");
					builder.setTitle("��ʾ");
					builder.setPositiveButton("ȷ��",
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

		// ��ProgressDialog��ʾ
		xh_pDialog.show();
	}

	protected void imageBrower(int position, ArrayList<String> urls2) {
		Intent intent = new Intent(InformationActivity.this,
				ImagePagerActivity.class);
		// ͼƬurl,Ϊ����ʾ����ʹ�ó�����һ������ݿ��л������л�ȡ
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
		intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
		startActivity(intent);
	}

	public View addImageById(String url) {
		final ImageView iv = new ImageView(this);
		Log.i("InformationActivity", "url:" + url);
		// ʹ��ImageLoader��������ͼƬ
		DisplayImageOptions options = new DisplayImageOptions.Builder()//
				.showImageOnLoading(R.drawable.ic_launcher) // ��������ʾ��Ĭ��ͼƬ
				.showImageOnFail(R.drawable.ic_launcher) // ���ü���ʧ�ܵ�Ĭ��ͼƬ
				.cacheInMemory(true) // �ڴ滺��
				.cacheOnDisk(true) // sdcard����
				.bitmapConfig(Config.RGB_565)// �����������
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
