package com.example.paoma_zf.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.config.ZfConfig;

public class BaseActivity extends Activity {

	// 等待操作的对话框

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 去掉默认标题栏
		Window window = getWindow();
		window.requestFeature(Window.FEATURE_NO_TITLE);

	}

	/**
	 * 得到当前版本号
	 */
	public String getVersionName() {
		PackageInfo info = null;
		String versionName = "";
		try {
			info = this.getPackageManager().getPackageInfo(
					this.getPackageName(), 0);
			versionName = info.versionName;
			int versionCode = info.versionCode;
			String packageNames = info.packageName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 将图片变成圆角
	 * 
	 * @param bitmap
	 *            需要处理图片
	 * @param pixels
	 *            圆角的数值
	 * @return 处理之后的图片
	 */
	public Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		// paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	// ==================================登录超时===========================================
	public void loginTimeOut(Activity activity) {
		// Intent intent = new Intent(activity, LogInActivity.class);
		// startActivity(intent);
	}

	// =============================activity管理======================================

	/**
	 * 清除所有Activity
	 */
	public void clearActivityArrayList() {
		for (Activity activity : ZfApplication.getInstance().getActivity()) {
			activity.finish();
		}
		ZfApplication.getInstance().getActivity().clear();
	}

	/**
	 * 跳转到上一个界面
	 * 
	 * @param
	 */
	public void goBackLastActivity() {
		int number = ZfApplication.getInstance().getActivity().size();
		if (number > 0) {
			String className = ZfApplication.getInstance().getActivity()
					.get(number - 1).getClass().getSimpleName();
			ZfApplication.getInstance().getActivity().get(number - 1).finish();

			if (className.equals("FirstActivity")
					|| className.equals("LoginActivity")
					|| className.equals("MessageActivity")
					|| className.equals("MyLoveActivity")
					|| className.equals("OrderManagerActivity")
					|| className.equals("AboutMeActivity")
					|| className.equals("AnnouncementActivity")
					|| className.equals("FeedbackActivity")
					|| className.equals("InstructionsActivity")
					|| className.equals("VegetableActivity")
					|| className.equals("LogInActivity")
					|| className.equals("VegetableListActivity")
					|| className.equals("VegetableInfoActivity")
					|| className.equals("FunctionActivity")
					|| className.equals("Promptdialog")) {

				getActivityOutToRight();

			} else
				getActivityLeftToRight();
			ZfApplication.getInstance().getActivity().remove(number - 1);
		}
	}

	// =============================下面是activity进出动画======================================

	/**
	 * activity切换-> 右进左出
	 */
	protected void getActivityRightToLeft() {
		overridePendingTransition(R.anim.activity_in_from_right,
				R.anim.activity_out_to_left);
	}

	/**
	 * activity切换-> 左进右出
	 */
	protected void getActivityLeftToRight() {
		overridePendingTransition(R.anim.activity_in_from_left,
				R.anim.activity_out_to_right);
	}

	/**
	 * activity切换-> 向下进(覆盖)
	 */
	protected void getActivityInFromTop() {
		overridePendingTransition(R.anim.activity_in_from_top,
				R.anim.activity_center);
	}

	/**
	 * activity切换-> 向上进(覆盖)
	 */
	protected void getActivityInFromBottom() {
		overridePendingTransition(R.anim.activity_in_from_bottom,
				R.anim.activity_center);
	}

	/**
	 * activity切换-> 向左进(覆盖)
	 */
	protected void getActivityInFromRight() {
		overridePendingTransition(R.anim.activity_in_from_right,
				R.anim.activity_center);
	}

	/**
	 * activity切换-> 向右进(覆盖)
	 */
	protected void getActivityInFromLeft() {
		overridePendingTransition(R.anim.activity_in_from_left,
				R.anim.activity_center);
	}

	/**
	 * activity切换-> 向下出(抽出)
	 */
	protected void getActivityOutToBottom() {
		overridePendingTransition(R.anim.activity_center,
				R.anim.activity_out_to_bottom);
	}

	/**
	 * activity切换-> 向上出(抽出)
	 */
	protected void getActivityOutToTop() {
		overridePendingTransition(R.anim.activity_center,
				R.anim.activity_out_to_top);
	}

	/**
	 * activity切换-> 向左出(抽出)
	 */
	protected void getActivityOutToLeft() {
		overridePendingTransition(R.anim.activity_center,
				R.anim.activity_out_to_left);
	}

	/**
	 * activity切换-> 向右出(抽出)
	 */
	protected void getActivityOutToRight() {
		overridePendingTransition(R.anim.activity_center,
				R.anim.activity_out_to_right);
	}

	/**
	 * activity切换-> 渐变动画
	 */
	protected void getActivityAlphaAnimation() {
		overridePendingTransition(R.anim.activity_in_alpha,
				R.anim.activity_out_alpha);
	}

	// =============================下面手机按键处理===================================

	/**
	 * 是否屏蔽手机按键
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 是否需要屏幕手机返回按键
			if (ZfConfig.IS_NEED_SHIELD) {
				return true;
			}
			goBackLastActivity();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 释放资源并且注销广播
	 */
	protected void onDestroy() {

		super.onDestroy();
	}

	// =============================需要封装的方法===================================
	// 写到sdcard中
	public void writeBitmapToSdcard(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);// png类型
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(
					Environment.getExternalStorageDirectory() + "/test2.jpg"));
			out.write(baos.toByteArray());
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// =============================下面是自定义加载对话框======================================

	/**
	 * 自定义等待操作框
	 * 
	 * @author pm004
	 */

}
