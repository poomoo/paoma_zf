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
			textView_title.setText("����");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.OrderformlistActivity")) {
			textView_title.setText("�ҵĶ����б�");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.MyorderformlistActivity")) {
			textView_title.setText("�ҵĶ����б�");
		}

		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.MythingActivity")) {
			textView_title.setText("�ҵ�");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.InformationActivity")) {
			textView_title.setText("��Դ����");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.InformationActivity")) {
			textView_title.setText("��Դ����");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.CollectListActivity")) {
			textView_title.setText("�ղ��б�");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.BrowserecordActivity")) {
			textView_title.setText("�����¼");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.DiscussdetailsActivity")) {
			textView_title.setText("��������");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.MessageListActivity")) {
			textView_title.setText("��Ϣ�б�");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.OrderformActivity")) {
			textView_title.setText("��������");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.OrderformInfoActivity")) {
			textView_title.setText("ȷ���ύ����");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.FeedbackActivity")) {
			textView_title.setText("�������");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.AboutusActivity")) {
			textView_title.setText("�����׼�����");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.HousecommentActivity")) {
			textView_title.setText("��������");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.RegistrationActivity")) {
			textView_title.setText("ע��");
		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.Authentication")) {
			textView_title.setText("ʵ����֤");
		}

		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.FindPasswordByPhoneActivity")
				|| thisactivity[0]
						.equals("com.example.paoma_zf.activity.FindPasswordByEmailActivity")) {
			textView_title.setText("�һ�����");

		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.ResetPasswordActivity")) {
			textView_title.setText("��������");

		}
		if (thisactivity[0]
				.equals("com.example.paoma_zf.activity.MyInfoActivity")) {
			textView_title.setText("������Ϣ");
		}

	}

}
