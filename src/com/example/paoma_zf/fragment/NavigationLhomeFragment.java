package com.example.paoma_zf.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.paoma_zf.R;
import com.example.paoma_zf.Application.ZfApplication;
import com.example.paoma_zf.activity.LookhomeActivity;
import com.example.paoma_zf.activity.MythingActivity;
import com.example.paoma_zf.activity.OrderformlistActivity;

@SuppressLint({ "NewApi", "ResourceAsColor" })
public class NavigationLhomeFragment extends Fragment {

	TextView textView_fargment_lookhome, textView_fargment_orderform,
			textView_fargment_mything;
	LinearLayout LinearLayout_1, LinearLayout_2, LinearLayout_3;
	ImageView imageView1, imageView2, imageView3;
	ZfApplication Zfapp;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		init();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(
				com.example.paoma_zf.R.layout.fargment_navigation_homesky,
				container, false);
	}

	private void init() {
		Zfapp = (ZfApplication) getActivity().getApplication();

		textView_fargment_lookhome = (TextView) getView().findViewById(
				R.id.textView_fargment_lookhome);
		imageView1 = (ImageView) getView().findViewById(R.id.imageView1);
		LinearLayout_1 = (LinearLayout) getView().findViewById(
				R.id.LinearLayout_1);
		LinearLayout_1.setTag("lookhome");
		LinearLayout_1.setOnClickListener(new NavigationClickListener());

		textView_fargment_orderform = (TextView) getView().findViewById(
				R.id.textView_fargment_orderform);
		imageView2 = (ImageView) getView().findViewById(R.id.imageView2);
		LinearLayout_2 = (LinearLayout) getView().findViewById(
				R.id.LinearLayout_2);
		LinearLayout_2.setTag("orderform");
		LinearLayout_2.setOnClickListener(new NavigationClickListener());

		textView_fargment_mything = (TextView) getView().findViewById(
				R.id.textView_fargment_mything);
		imageView3 = (ImageView) getView().findViewById(R.id.imageView3);
		LinearLayout_3 = (LinearLayout) getView().findViewById(
				R.id.LinearLayout_3);
		LinearLayout_3.setTag("mything");
		LinearLayout_3.setOnClickListener(new NavigationClickListener());
		setNavigationBackgroundResource();
	}

	@SuppressLint("ResourceAsColor")
	public void setNavigationBackgroundResource() {
		String[] thisactivity = getActivity().toString().split("@");
		LinearLayout_1.setEnabled(true);
		LinearLayout_2.setEnabled(true);
		LinearLayout_3.setEnabled(true);

		imageView1.setBackgroundResource(R.drawable.ic_shouye_up);
		imageView2.setBackgroundResource(R.drawable.ic_orderlist_up);
		imageView3.setBackgroundResource(R.drawable.ic_my_up);

		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.LookhomeActivity")) {
			LinearLayout_1.setEnabled(false);
			imageView1.setBackgroundResource(R.drawable.ic_shouye_down);

		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.OrderformlistActivity")) {
			LinearLayout_2.setEnabled(false);
			imageView2.setBackgroundResource(R.drawable.ic_orderlist_down);

		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.MythingActivity")) {
			LinearLayout_3.setEnabled(false);
			imageView3.setBackgroundResource(R.drawable.ic_my_down);
		}

	}

	public Boolean checklogin() {
		if (Zfapp.getUserName().toString().trim().equals("")) {
			AlertDialog.Builder builder = new Builder(getActivity());
			builder.setMessage("请先登录");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new Dialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
			return false;
		} else {
			Intent i = new Intent();
			i.setClass(getActivity(), OrderformlistActivity.class);
			startActivity(i);
			getActivity().finish();
			return true;
		}

	}

	class NavigationClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (v.getTag().equals("lookhome")) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), LookhomeActivity.class);
				startActivity(intent);
				getActivity().finish();

			}
			if (v.getTag().equals("orderform")) {
				checklogin();
			}
			if (v.getTag().equals("mything")) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), MythingActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		}
	}

}
