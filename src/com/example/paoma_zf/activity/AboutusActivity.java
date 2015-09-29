package com.example.paoma_zf.activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.ViewFlipper;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.net.Zfnet;
import com.example.paoma_zf.view.MyGestureListener;

public class AboutusActivity extends BaseActivity {

	Zfnet zz;
	ZfApplication zfapp;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutus);
		// init();
	}

	public void init() {
		zz = new Zfnet();
		zfapp = (ZfApplication) getApplication();

		new Thread(new Runnable() {
			public void run() {
				try {
					zz.getaboutus();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}).start();
	}
	ViewFlipper viewFlipper__information_img;
	final GestureDetector gd = new GestureDetector(new MyGestureListener(
			this, viewFlipper__information_img));
}
