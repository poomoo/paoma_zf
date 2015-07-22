package com.example.paoma_zf.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paoma_zf.R;

@SuppressLint("NewApi")
public class ReturnFragment extends Fragment {

	ImageView imageView_return;
	TextView textView_title;

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
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fargment_return, container, false);

	}

	public void init() {
		imageView_return = (ImageView) getView().findViewById(
				R.id.imageView_return);
		imageView_return.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		textView_title = (TextView) getView().findViewById(R.id.textView_title);
		setNavigationTitle();
	}

	public void setNavigationTitle() {
		String[] thisactivity = getActivity().toString().split("@");

		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.LookhomeActivity")) {
			textView_title.setText("看房");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.OrderformlistActivity")) {
			textView_title.setText("我的订单列表");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.MyorderformlistActivity")) {
			textView_title.setText("我的订单列表");
		}

		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.MythingActivity")) {
			textView_title.setText("我的");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.InformationActivity")) {
			textView_title.setText("房源详情");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.InformationActivity")) {
			textView_title.setText("房源详情");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.CollectListActivity")) {
			textView_title.setText("收藏列表");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.BrowserecordActivity")) {
			textView_title.setText("浏览记录");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.DiscussdetailsActivity")) {
			textView_title.setText("讨论详情");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.MessageListActivity")) {
			textView_title.setText("消息列表");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.OrderformActivity")) {
			textView_title.setText("创建订单");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.OrderformInfoActivity")) {
			textView_title.setText("确认提交订单");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.FeedbackActivity")) {
			textView_title.setText("意见反馈");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.AboutusActivity")) {
			textView_title.setText("关于易家天下");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.HousecommentActivity")) {
			textView_title.setText("房间评价");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.RegistrationActivity")) {
			textView_title.setText("注册");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.Authentication")) {
			textView_title.setText("实名认证");
		}

		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.FindPasswordByPhoneActivity")
				|| thisactivity[0]
						.equals("com.example.paoma_zf.activity.FindPasswordByEmailActivity")) {
			textView_title.setText("找回密码");

		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.ResetPasswordActivity")) {
			textView_title.setText("重置密码");

		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.MyInfoActivity")) {
			textView_title.setText("个人信息");
		}

	}

}
