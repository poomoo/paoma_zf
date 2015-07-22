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

	// �ȴ������ĶԻ���

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// ȥ��Ĭ�ϱ�����
		Window window = getWindow();
		window.requestFeature(Window.FEATURE_NO_TITLE);

	}

	/**
	 * �õ���ǰ�汾��
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
	 * ��ͼƬ���Բ��
	 * 
	 * @param bitmap
	 *            ��Ҫ����ͼƬ
	 * @param pixels
	 *            Բ�ǵ���ֵ
	 * @return ����֮���ͼƬ
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

	// ==================================��¼��ʱ===========================================
	public void loginTimeOut(Activity activity) {
		// Intent intent = new Intent(activity, LogInActivity.class);
		// startActivity(intent);
	}

	// =============================activity����======================================

	/**
	 * �������Activity
	 */
	public void clearActivityArrayList() {
		for (Activity activity : ZfApplication.getInstance().getActivity()) {
			activity.finish();
		}
		ZfApplication.getInstance().getActivity().clear();
	}

	/**
	 * ��ת����һ������
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

	// =============================������activity��������======================================

	/**
	 * activity�л�-> �ҽ����
	 */
	protected void getActivityRightToLeft() {
		overridePendingTransition(R.anim.activity_in_from_right,
				R.anim.activity_out_to_left);
	}

	/**
	 * activity�л�-> ����ҳ�
	 */
	protected void getActivityLeftToRight() {
		overridePendingTransition(R.anim.activity_in_from_left,
				R.anim.activity_out_to_right);
	}

	/**
	 * activity�л�-> ���½�(����)
	 */
	protected void getActivityInFromTop() {
		overridePendingTransition(R.anim.activity_in_from_top,
				R.anim.activity_center);
	}

	/**
	 * activity�л�-> ���Ͻ�(����)
	 */
	protected void getActivityInFromBottom() {
		overridePendingTransition(R.anim.activity_in_from_bottom,
				R.anim.activity_center);
	}

	/**
	 * activity�л�-> �����(����)
	 */
	protected void getActivityInFromRight() {
		overridePendingTransition(R.anim.activity_in_from_right,
				R.anim.activity_center);
	}

	/**
	 * activity�л�-> ���ҽ�(����)
	 */
	protected void getActivityInFromLeft() {
		overridePendingTransition(R.anim.activity_in_from_left,
				R.anim.activity_center);
	}

	/**
	 * activity�л�-> ���³�(���)
	 */
	protected void getActivityOutToBottom() {
		overridePendingTransition(R.anim.activity_center,
				R.anim.activity_out_to_bottom);
	}

	/**
	 * activity�л�-> ���ϳ�(���)
	 */
	protected void getActivityOutToTop() {
		overridePendingTransition(R.anim.activity_center,
				R.anim.activity_out_to_top);
	}

	/**
	 * activity�л�-> �����(���)
	 */
	protected void getActivityOutToLeft() {
		overridePendingTransition(R.anim.activity_center,
				R.anim.activity_out_to_left);
	}

	/**
	 * activity�л�-> ���ҳ�(���)
	 */
	protected void getActivityOutToRight() {
		overridePendingTransition(R.anim.activity_center,
				R.anim.activity_out_to_right);
	}

	/**
	 * activity�л�-> ���䶯��
	 */
	protected void getActivityAlphaAnimation() {
		overridePendingTransition(R.anim.activity_in_alpha,
				R.anim.activity_out_alpha);
	}

	// =============================�����ֻ���������===================================

	/**
	 * �Ƿ������ֻ�����
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			return true;
		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �Ƿ���Ҫ��Ļ�ֻ����ذ���
			if (ZfConfig.IS_NEED_SHIELD) {
				return true;
			}
			goBackLastActivity();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * �ͷ���Դ����ע���㲥
	 */
	protected void onDestroy() {

		super.onDestroy();
	}

	// =============================��Ҫ��װ�ķ���===================================
	// д��sdcard��
	public void writeBitmapToSdcard(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);// png����
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

	// =============================�������Զ�����ضԻ���======================================

	/**
	 * �Զ���ȴ�������
	 * 
	 * @author pm004
	 */

}
