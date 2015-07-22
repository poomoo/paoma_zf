package com.example.paoma_zf.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.paoma_zf.R;

public class GuidepageActivity extends BaseActivity {

	ImageView imageView_guidepage;
	SharedPreferences sp;
	Editor editor;
	String guide;
	private long mTime = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guidepage);
		init();
	}

	public void init() {
		sp = getSharedPreferences("date", Context.MODE_PRIVATE);
		editor = sp.edit();
		guide = sp.getString("guide", "");

		imageView_guidepage = (ImageView) findViewById(R.id.imageView_guidepage);

		imageView_guidepage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.putString("guide", "true");
				editor.commit();
				Intent i = new Intent();
				i.setClass(getApplicationContext(), HomepageActivity.class);
				startActivity(i);
				finish();
			}
		});

		if (guide.equals("true")) {
			Intent i = new Intent();
			i.setClass(getApplicationContext(), HomepageActivity.class);
			startActivity(i);
			finish();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			long time = System.currentTimeMillis();
			if (time - mTime <= 3000) {
				// ZfApplication.getInstance().getOperateDataBase().deleteOneOrAllBoard(-1);
				finish();
				return true;
			}
			Toast.makeText(GuidepageActivity.this, "再按一次退出应用",
					Toast.LENGTH_SHORT).show();
			mTime = time;
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
