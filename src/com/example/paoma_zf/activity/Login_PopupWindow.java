package com.example.paoma_zf.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.paoma_zf.R;

public class Login_PopupWindow extends PopupWindow {

	private TextView textView_login_byphone, textView_login_bymail,
			textView_login_cancle;
	private View mMenuView;
	private LinearLayout layout_phone;

	public Login_PopupWindow(Activity context, OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popup_login, null);
		textView_login_byphone = (TextView) mMenuView
				.findViewById(R.id.popup_login_byphone);
		textView_login_bymail = (TextView) mMenuView
				.findViewById(R.id.popup_login_bymail);
		textView_login_cancle = (TextView) mMenuView
				.findViewById(R.id.popup_login_cancle);
		
		textView_login_cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dismiss();
			}
		});

		textView_login_byphone.setOnClickListener(itemsOnClick);
		textView_login_bymail.setOnClickListener(itemsOnClick);
		this.setContentView(mMenuView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		this.setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int height = mMenuView.findViewById(R.id.popup_login_layout)
						.getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
	}
}
