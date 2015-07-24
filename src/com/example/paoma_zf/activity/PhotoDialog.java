package com.example.paoma_zf.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.config.ZfConfig;
import com.example.paoma_zf.net.Zfnet;

public class PhotoDialog extends Activity {

	public static final int NONE = 0;
	public static final int PHOTOHRAPH = 1;// ����
	public static final int PHOTOZOOM = 2; // ����
	public static final int PHOTORESOULT = 3;// ���

	public static final String IMAGE_UNSPECIFIED = "image/*";
	ImageView imageView = null;
	Button button1 = null;
	Button button2 = null;
	Button button3 = null;
	ZfApplication Zfapp;
	byte[] photodata;
	Bitmap photo;
	private ProgressDialog xh_pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_photo);

		button1 = (Button) findViewById(R.id.btn_01);
		button2 = (Button) findViewById(R.id.btn_02);
		button3 = (Button) findViewById(R.id.btn_03);
		Zfapp = (ZfApplication) getApplication();

		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						IMAGE_UNSPECIFIED);
				startActivityForResult(intent, PHOTOZOOM);
			}
		});

		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
						Environment.getExternalStorageDirectory(), "temp.jpg")));
				startActivityForResult(intent, PHOTOHRAPH);
			}
		});

		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		init();
	}

	private void init() {
		xh_pDialog = new ProgressDialog(this);

		// ���ý�������񣬷��ΪԲ�Σ���ת��
		xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		// ����ProgressDialog��ʾ��Ϣ
		xh_pDialog.setMessage("�ϴ���...");

		// ����ProgressDialog �Ľ������Ƿ���ȷ false ���ǲ�����Ϊ����ȷ
		xh_pDialog.setIndeterminate(false);

		// ����ProgressDialog �Ƿ���԰��˻ؼ�ȡ��
		xh_pDialog.setCancelable(false);
		xh_pDialog.setCanceledOnTouchOutside(false);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("PhotoDialog", "requestCode:" + requestCode);
		if (resultCode == NONE)
			return;
		// ����
		if (requestCode == PHOTOHRAPH) {
			// �����ļ�����·��������ڸ�Ŀ¼��
			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			startPhotoZoom(Uri.fromFile(picture));
		}

		if (data == null)
			return;

		// ��ȡ�������ͼƬ
		if (requestCode == PHOTOZOOM) {
			startPhotoZoom(data.getData());
		}
		// ������
		if (requestCode == PHOTORESOULT) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				photo = extras.getParcelable("data");
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);
				Zfapp.setBm(photo);
				// ��ProgressDialog��ʾ
				xh_pDialog.show();
				new Thread(new Runnable() {

					@Override
					public void run() {
						Message message = new Message();
						// if (Zfnet.uploadFile(saveBitmap(photo),
						// ZfConfig.getUserList))
						// message.what = 1;
						// else
						// message.what = 2;
						myHandler.sendMessage(message);
					}
				}).start();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		// aspectX aspectY �ǿ�ߵı���
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY �ǲü�ͼƬ���
		intent.putExtra("outputX", 100);
		intent.putExtra("outputY", 100);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTORESOULT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public File saveBitmap(Bitmap bitmap) {

		File f = new File(Environment.getExternalStorageDirectory(),
				"headpic.jpg");

		if (f.exists()) {
			f.delete();
		}

		try {
			FileOutputStream out = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				xh_pDialog.dismiss();
				Intent re = getIntent();
				re.putExtra("isTrue", true);
				setResult(101, re);
				finish();
				Toast.makeText(getApplicationContext(), "�ϴ��ɹ�",
						Toast.LENGTH_LONG).show();
			} else {
				xh_pDialog.dismiss();
				Intent re = getIntent();
				re.putExtra("isTrue", false);
				setResult(101, re);
				finish();
				Toast.makeText(getApplicationContext(), "�ϴ�ʧ��",
						Toast.LENGTH_LONG).show();

			}
			super.handleMessage(msg);
		}
	};

}