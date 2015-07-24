package com.example.paoma_zf.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.paoma_zf.R;

public class AlterUserInfo_PopupWindow extends PopupWindow {

	private TextView textView_camera, textView_photograph;
	private View mMenuView;

	public AlterUserInfo_PopupWindow(Activity context,
			OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popup_alteruserinfo, null);
		textView_camera = (TextView) mMenuView
				.findViewById(R.id.popup_alterUserInfo_camera);
		textView_photograph = (TextView) mMenuView
				.findViewById(R.id.popup_alterUserInfo_photograph);

		textView_camera.setOnClickListener(itemsOnClick);
		textView_photograph.setOnClickListener(itemsOnClick);
		this.setContentView(mMenuView);
		this.setWidth(LayoutParams.MATCH_PARENT);
		this.setHeight(LayoutParams.MATCH_PARENT);
		this.setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		this.setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int height_top = mMenuView.findViewById(
						R.id.popup_alterUserInfo_layout).getTop();
				int height_bottom = mMenuView.findViewById(
						R.id.popup_alterUserInfo_layout).getBottom();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height_top || y > height_bottom) {
						dismiss();
					}
				}
				return true;
			}
		});
	}
}
