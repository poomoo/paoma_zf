package com.example.paoma_zf.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.example.paoma_zf.R;
import com.example.paoma_zf.adapter.ViewPagerAdapter;

public class HousePicViewPagerActivity extends BaseActivity implements
		OnClickListener, OnPageChangeListener {

	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	private Button button;

	// ����ͼƬ��Դ
	private static final int[] pics = { R.drawable.index1, R.drawable.index2,
			R.drawable.index3, R.drawable.index4 };
	private static final int lenth = pics.length;

	// �ײ�С��ͼƬ
	private ImageView[] dots;

	// ��¼��ǰѡ��λ��
	private int currentIndex;

	private int position = 0;
	private Bitmap[] bm = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager);

		views = new ArrayList<View>();

		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		// ��ʼ������ͼƬ�б�
		for (int i = 0; i < pics.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setLayoutParams(mParams);
			// ��ֹͼƬ����������Ļ
			iv.setScaleType(ScaleType.FIT_XY);

			iv.setImageResource(pics[i]);
			views.add(iv);
		}
		vp = (ViewPager) findViewById(R.id.viewpager_viewpager);
		// ��ʼ��Adapter
		vpAdapter = new ViewPagerAdapter(views);
		vp.setAdapter(vpAdapter);
		// �󶨻ص�
		vp.setOnPageChangeListener(this);

		// ��ʼ���ײ�С��
		initDots();

		button = (Button) findViewById(R.id.index_button);

		button.setOnClickListener(this);
		button.setTag("button");

	}

	private void initDots() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.viewpager_ll);
		dots = new ImageView[lenth];

		// ѭ��ȡ��С��ͼƬ
		for (int i = 0; i < lenth; i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(false);// ����Ϊ��ɫ
			dots[i].setOnClickListener(this);
			dots[i].setTag(i);// ����λ��tag������ȡ���뵱ǰλ�ö�Ӧ
		}

		currentIndex = 0;
		dots[currentIndex].setEnabled(true);// ����Ϊ��ɫ����ѡ��״̬
	}

	/**
	 * ���õ�ǰ������ҳ
	 */
	private void setCurView(int position) {
		if (position < 0 || position >= lenth) {
			return;
		}
		vp.setCurrentItem(position);
	}

	/**
	 * ��ǰ����С���ѡ��
	 */
	private void setCurDot(int positon) {
		if (positon < 0 || positon > lenth - 1 || currentIndex == positon) {
			return;
		}

		dots[positon].setEnabled(true);
		dots[currentIndex].setEnabled(false);

		currentIndex = positon;
	}

	// ������״̬�ı�ʱ����
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	// ����ǰҳ�汻����ʱ����
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	// ���µ�ҳ�汻ѡ��ʱ����
	@Override
	public void onPageSelected(int arg0) {
		// ���õײ�С��ѡ��״̬
		setCurDot(arg0);
		if (arg0 == 3)
			button.setVisibility(View.VISIBLE);
		else
			button.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onClick(View v) {
		if (v.getTag().equals("button")) {
			Intent i = new Intent(HousePicViewPagerActivity.this,
					GuidepageActivity.class);
			startActivity(i);
			finish();
		} else {
			int position = (Integer) v.getTag();
			setCurView(position);
			setCurDot(position);
		}
	}
}
