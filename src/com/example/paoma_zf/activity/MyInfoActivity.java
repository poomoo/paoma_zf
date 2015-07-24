package com.example.paoma_zf.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.config.ZfConfig;
import com.example.paoma_zf.popupwindow.AlterUserInfo_PopupWindow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MyInfoActivity extends BaseActivity {
	private LinearLayout layout_alter;
	private EditText editText_name, editText_address, editText_mail;
	private ImageView imageView;

	private String id, name, address, email;
	private ZfApplication Zfapp = null;
	private AlterUserInfo_PopupWindow alterUserInfo_PopupWindow;

	public static final int NONE = 0;
	public static final int PHOTOHRAPH = 1;// 拍照
	public static final int PHOTOZOOM = 2; // 缩放
	public static final int PHOTORESOULT = 3;// 结果

	public static final String IMAGE_UNSPECIFIED = "image/*";
	private Bitmap photo;
	private ProgressDialog xh_pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);

		Zfapp = (ZfApplication) getApplication();

		id = Zfapp.getUserId();
		name = Zfapp.getUserName();
		address = Zfapp.getAddress();
		email = Zfapp.getEmail();

		init();
	}

	private void init() {
		editText_name = (EditText) findViewById(R.id.editText_myinfo_name);
		editText_address = (EditText) findViewById(R.id.editText_myinfo_address);
		editText_mail = (EditText) findViewById(R.id.editText_myinfo_mail);

		layout_alter = (LinearLayout) findViewById(R.id.layout_myinfo_alter);
		// layout_headpic = (LinearLayout)
		// findViewById(R.id.layout_myinfo_headpic);
		layout_alter.setOnClickListener(new theOnClickListener());
		// layout_headpic.setOnClickListener(new theOnClickListener());

		imageView = (ImageView) findViewById(R.id.imageView_myinfo_headpic);
		imageView.setOnClickListener(new theOnClickListener());

		editText_name.setText(name);
		editText_address.setText(address);
		editText_mail.setText(email);
		imageView.setImageBitmap(Zfapp.getBm());

		xh_pDialog = new ProgressDialog(this);

		// 设置进度条风格，风格为圆形，旋转的
		xh_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

		// 设置ProgressDialog提示信息
		xh_pDialog.setMessage("上传中...");

		// 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
		xh_pDialog.setIndeterminate(false);

		// 设置ProgressDialog 是否可以按退回键取消
		xh_pDialog.setCancelable(false);
		xh_pDialog.setCanceledOnTouchOutside(false);

	}

	public boolean check() {
		name = editText_name.getText().toString().trim();
		email = editText_mail.getText().toString().trim();
		address = editText_address.getText().toString().trim();
		if (name.equals("")) {
			AlertDialog.Builder builder = new Builder(MyInfoActivity.this);
			builder.setMessage("请输入昵称");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			return false;
		}

		if (!email.equals("")) {
			Pattern p = Pattern
					.compile("^[a-zA-Z0-9]+([\\_|\\-|\\.]?[a-zA-Z0-9])*\\@[a-zA-Z0-9]+([\\_|\\-|\\.]?[a-zA-Z0-9])*\\.[a-zA-Z]{2,3}$");
			Matcher m = p.matcher(email);
			if (!m.find()) {
				AlertDialog.Builder builder = new Builder(MyInfoActivity.this);
				builder.setMessage("邮箱格式不正确");
				builder.setTitle("提示");
				builder.setPositiveButton("确认", new Dialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				builder.create().show();
				return false;
			}
		}

		return true;
	}

	public class theOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.imageView_myinfo_headpic:
				// 实例化SelectPicPopupWindow
				alterUserInfo_PopupWindow = new AlterUserInfo_PopupWindow(
						MyInfoActivity.this, itemsOnClick);
				// 显示窗口
				alterUserInfo_PopupWindow.showAtLocation(MyInfoActivity.this
						.findViewById(R.id.activity_alterUserInfo_layout),
						Gravity.CENTER, 0, 0); // 设置layout在PopupWindow中显示的位置

				break;
			case R.id.layout_myinfo_alter:
				if (check()) {
					xh_pDialog.show();
					File file = null;
					if (photo != null)
						file = saveBitmap(photo);
					uploadUserInfo(id, name, address, email, file,
							ZfConfig.getUserList);
				}
				break;
			default:
				break;
			}

		}
	}

	// 为弹出窗口实现监听类
	private OnClickListener itemsOnClick = new OnClickListener() {

		@Override
		public void onClick(View view) {
			alterUserInfo_PopupWindow.dismiss();
			switch (view.getId()) {
			case R.id.popup_alterUserInfo_camera:
				Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri
						.fromFile(new File(Environment
								.getExternalStorageDirectory(), "temp.jpg")));
				startActivityForResult(intent1, PHOTOHRAPH);
				break;

			case R.id.popup_alterUserInfo_photograph:
				Intent intent = new Intent(Intent.ACTION_PICK, null);
				intent.setDataAndType(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
						IMAGE_UNSPECIFIED);
				startActivityForResult(intent, PHOTOZOOM);
				break;
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
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

	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 100);
		intent.putExtra("outputY", 100);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTORESOULT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == NONE)
			return;
		// 拍照
		if (requestCode == PHOTOHRAPH) {
			// 设置文件保存路径这里放在跟目录下
			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			startPhotoZoom(Uri.fromFile(picture));
		}

		if (data == null)
			return;

		// 读取相册缩放图片
		if (requestCode == PHOTOZOOM) {
			startPhotoZoom(data.getData());
		}
		// 处理结果
		if (requestCode == PHOTORESOULT) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				photo = extras.getParcelable("data");
				imageView.setImageBitmap(photo);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				xh_pDialog.dismiss();
				if (photo != null)
					Zfapp.setBm(photo);
				Zfapp.setUserName(name);
				Zfapp.setEmail(email);
				Zfapp.setAddress(address);

				Intent re = getIntent();
				re.putExtra("isTrue", true);
				setResult(101, re);
				finish();
				Toast.makeText(getApplicationContext(), "上传成功",
						Toast.LENGTH_LONG).show();
			} else {
				xh_pDialog.dismiss();
				Intent re = getIntent();
				re.putExtra("isTrue", false);
				setResult(101, re);
				finish();
				Toast.makeText(getApplicationContext(), "上传失败",
						Toast.LENGTH_LONG).show();
			}
			super.handleMessage(msg);
		}
	};

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
	private boolean uploadUserInfo(String userId, String userName,
			String address, String email, File file, String url) {
		// HttpClient httpClient = new DefaultHttpClient();
		// HttpPost httpPost = new HttpPost(url);
		// List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		RequestParams params = new RequestParams();

		JSONObject jsonObject = new JSONObject();

		try {
			// 放入文件
			jsonObject.put("userId", userId);
			jsonObject.put("userName", userName);
			jsonObject.put("address", address);
			jsonObject.put("email", email);
			jsonObject.put("method", "1019");

			String strJson = new String(jsonObject.toString().getBytes(),
					"UTF-8");

			params.put("jsonData", strJson);
			if (file != null)
				params.put("file", file);
			System.out.println("strJson:" + strJson + "params:" + params);

			// 异步的客户端对象
			AsyncHttpClient client = new AsyncHttpClient();
			client.post(url, params, new AsyncHttpResponseHandler() {
				Message message = new Message();

				@Override
				public void onFailure(Throwable arg0, String arg1) {
					message.what = 2;
					myHandler.sendMessage(message);
					System.out.println("onFailure");
				}

				@Override
				public void onSuccess(String arg0) {
					message.what = 1;
					myHandler.sendMessage(message);
					System.out.println("onSuccess");
				}

			});
		} catch (Exception e) {
			System.out.println("异常" + e.getMessage().toString());
		}
		return true;
	}
}
