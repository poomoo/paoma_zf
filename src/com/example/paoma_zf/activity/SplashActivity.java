package com.example.paoma_zf.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;

import com.example.paoma_zf.R;

public class SplashActivity extends BaseActivity {
	private final int SPLASH_DISPLAY_LENGHT = 3000; // —”≥Ÿ»˝√Î

	private SharedPreferences sp = null;
	private Editor editor = null;
	private String guide = "", index = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		sp = getSharedPreferences("date", Context.MODE_PRIVATE);
		editor = sp.edit();
		guide = sp.getString("guide", "");
		index = sp.getString("index", "");

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if (index.equals("true") && guide.equals("true")) {
					Intent i = new Intent();
					i.setClass(SplashActivity.this, HomepageActivity.class);
					startActivity(i);
				} else if (index.equals("")) {
					editor.putString("index", "true");
					editor.commit();
					Intent i = new Intent();
					i.setClass(SplashActivity.this,
							IndexViewPagerActivity.class);
					startActivity(i);
				} else if (guide.equals("")) {
					Intent i = new Intent();
					i.setClass(SplashActivity.this, GuidepageActivity.class);
					startActivity(i);
				}
				SplashActivity.this.finish();
			}

		}, SPLASH_DISPLAY_LENGHT);
	}

}
