package com.example.paoma_zf.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

import com.example.paoma_zf.R;
import com.suo.image.ScaleView;

public class DialogimgActivity extends Activity {

	ScaleView img_dialog_pic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);// 需要添加的语句
		setContentView(R.layout.dialog_img);

		init();

	}

	public void init() {
		img_dialog_pic = (ScaleView) findViewById(R.id.img_dialog_pic);

		byte[] b = getIntent().getByteArrayExtra("img");

		Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);

		img_dialog_pic.setImageBitmap(bmp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return super.onTouchEvent(event);
	}

}