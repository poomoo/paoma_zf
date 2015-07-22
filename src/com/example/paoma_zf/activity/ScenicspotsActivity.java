package com.example.paoma_zf.activity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.paoma_zf.R;
import com.example.paoma_zf.activity.LookhomeActivity.Lvitemclicklistener;
import com.example.paoma_zf.view.ListViewForScrollView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;

public class ScenicspotsActivity extends BaseActivity implements
		OnGestureListener {

	ViewFlipper viewFlipper_scenicspots;
	private GestureDetector detector;
	private static final float FLING_MIN_DISTANCE = 0;
	ScrollView scrollView_scenicspots;
	ListViewForScrollView lv_scenicspots;
	LinearLayout linearLayout_scenicspots_return;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scenicspots);
		init();
	}

	public void init() {
		detector = new GestureDetector(this);

		viewFlipper_scenicspots = (ViewFlipper) findViewById(R.id.viewFlipper_scenicspots);
		linearLayout_scenicspots_return = (LinearLayout) findViewById(R.id.linearLayout_scenicspots_return);
		viewFlipper_scenicspots.setInAnimation(AnimationUtils.loadAnimation(
				this, android.R.anim.fade_in));
		viewFlipper_scenicspots.setOutAnimation(AnimationUtils.loadAnimation(
				this, android.R.anim.fade_out));
		viewFlipper_scenicspots.setAutoStart(true); // �����Զ����Ź��ܣ�����¼���ǰ�Զ����ţ�
		viewFlipper_scenicspots.setFlipInterval(3000);
		if (viewFlipper_scenicspots.isAutoStart()
				&& !viewFlipper_scenicspots.isFlipping()) {
			viewFlipper_scenicspots.startFlipping();

		}
		viewFlipper_scenicspots.addView(addImageById(R.drawable.pic_jd1));
		viewFlipper_scenicspots.addView(addImageById(R.drawable.pic_jd2));
		viewFlipper_scenicspots.addView(addImageById(R.drawable.pic_jd3));

		scrollView_scenicspots = (ScrollView) findViewById(R.id.scrollView_scenicspots);
		scrollView_scenicspots.smoothScrollTo(0, 0);

		lv_scenicspots = (ListViewForScrollView) findViewById(R.id.lv_scenicspots);

		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.style_lvhomepage, new String[] { "title", "info",
						"img", "money" }, new int[] {
						R.id.textView_style_homepagetitle,
						R.id.textView_style_homepagecontent,
						R.id.imageView_style_homepage,
						R.id.textView_style_homepagemoney });

		lv_scenicspots.setAdapter(adapter);

		lv_scenicspots.setOnItemClickListener(new Lvitemclicklistener());

		linearLayout_scenicspots_return
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "��");
		map.put("info",
				"\u3000\u3000"
						+ "����λ�ڹ���ʡ�ϲ�������ǭ�ϲ��������������ݡ���ɽ���������ļ��紺����һ�����ص����ء�����Ϊ���������ϵġ��̱�ʯ���� ����ԭʼ�����ӡ����桢���˶�ʶ����ƣ���Ҫ�Ĵ�����ˮ����Ͽ�Ⱦ�����������԰��⾰�������߿׾�����С�߿׾����� ���������ϵġ��̱�ʯ����ʷ׳ʣ�����������Ȼ�������������ӡ���������ء�");
		map.put("money", "105");
		map.put("img", R.drawable.pic_jd2);
		list.add(map);

		// for(int i=0;i<10;i++)
		// {
		// Map<String, Object> map1 = new HashMap<String, Object>();
		// map1.put("title", "���鷿����"+i);
		// map1.put("info", "\u3000\u3000"+"����������������ϸ��������");
		// map1.put("money", "1470");
		// map1.put("img", R.drawable.pic_home);
		// list.add(map1);
		// }

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("title", "�ƹ���");
		map1.put(
				"info",
				"\u3000\u3000"
						+ "�ƹ����羰��ʤ��λ�ڹ���ʡ���ϲ������й����ҵ�����־����Ϊ���й��������ĵط����� �����Իƹ����ٲ�����Ϊ���ģ��ֲ��������ž�������������������ˮ̲�ٲ������ȼ�������  ");
		map1.put("money", "126");
		map1.put("img", R.drawable.pic_jd1);
		list.add(map1);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "��ˮ");
		map2.put(
				"info",
				"\u3000\u3000"
						+ "��ˮ��λ�ڹ���ʡ����������ˮ�������Σ����Ĵ�ʡ�ϲ���������Ϊ��ǭ��óŦ���������Ļ�������ǭ��ͨ���������Ҫ�Ż������С���ǭ��Կ������ǭ���߳ǡ�֮�ơ� ��ˮɽ���������羰������ȫ��ɭ�ָ�����76.2%���ӹ���ʡ��һλ����ˮ�羰��ʤ���ǹ���ԺΨһ�������������Ĺ��Ҽ��羰��ʤ�������С�ǧ��֮�С�������ϼ֮�ڡ���������֮�硱��������������������� ");
		map2.put("money", "81");
		map2.put("img", R.drawable.pic_jd3);
		list.add(map2);
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("title", "��ɽ");
		map3.put(
				"info",
				"\u3000\u3000"
						+ "��ɽ������ɽ�������壬λ�ڹ���ʡ���������ڡ�ӡ�����������ؽ��紦������2572�ף��ǹ��ݵĵ�һɽ����ɽɽ���Ӵ��ۻ룬Ħ�ƽ��죬���������ͱ���Ϊ������֮�ڡ����������ķ��ʥ�ء�1986�꣬��ɽ����Ϊ���Ҽ���Ȼ�������������������567ƽ�����ͬ������ѡ���������Ȧ��������������Ϊ�����������֮���������й�14���������Ϲ�����������Ȧ����������Ȼ�������ĳ�Ա֮һ�� ��ɽ���϶�Ϊ������ͬγ�ȱ�������õ�ԭʼɭ�֣� ");
		map3.put("money", "100");
		map3.put("img", R.drawable.pic_jd4);
		list.add(map3);

		return list;
	}

	class Lvitemclicklistener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			TextView content = (TextView) arg1
					.findViewById(R.id.textView_style_homepagecontent);
			TextView title = (TextView) arg1
					.findViewById(R.id.textView_style_homepagetitle);
			TextView money = (TextView) arg1
					.findViewById(R.id.textView_style_homepagemoney);
			ImageView img = (ImageView) arg1
					.findViewById(R.id.imageView_style_homepage);

			// ImageView����(iv_photo)�������������ú󣬲��ܻ�ȡ���е�ͼ��

			img.setDrawingCacheEnabled(true);

			Bitmap obmp = Bitmap.createBitmap(img.getDrawingCache());

			img.setDrawingCacheEnabled(false);

			ByteArrayOutputStream bs = new ByteArrayOutputStream();

			obmp.compress(Bitmap.CompressFormat.JPEG, 100, bs);
			// Toast.makeText(HomePageActivity.this, title.getText().toString(),
			// 500).show();

			Intent intent = new Intent(ScenicspotsActivity.this,
					InformationActivity.class);
			intent.putExtra("title", title.getText().toString());
			intent.putExtra("content", content.getText().toString());
			intent.putExtra("img", bs.toByteArray());
			intent.putExtra("money", money.getText().toString());

			startActivity(intent);
		}

	}

	public View addImageById(int id) {
		ImageView iv = new ImageView(this);
		iv.setScaleType(ScaleType.FIT_XY);

		iv.setImageResource(id);

		return iv;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// �������¼���������ʶ���ദ��

		viewFlipper_scenicspots.stopFlipping(); // ����¼���ֹͣ�Զ�����
		viewFlipper_scenicspots.setAutoStart(false);
		return this.detector.onTouchEvent(event);
	}

	public boolean onDown(MotionEvent e) {
		return false;
	}

	public void onShowPress(MotionEvent e) {
	}

	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	public void onLongPress(MotionEvent e) {
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE) {
			// ����View������˳��Ķ���Ч��
			this.viewFlipper_scenicspots.setInAnimation(AnimationUtils
					.loadAnimation(this, R.anim.left_in));
			this.viewFlipper_scenicspots.setOutAnimation(AnimationUtils
					.loadAnimation(this, R.anim.left_out));
			this.viewFlipper_scenicspots.showNext();
			return true;
		}
		if (e1.getX() - e2.getX() < -FLING_MIN_DISTANCE) {
			this.viewFlipper_scenicspots.setInAnimation(AnimationUtils
					.loadAnimation(this, R.anim.right_in));
			this.viewFlipper_scenicspots.setOutAnimation(AnimationUtils
					.loadAnimation(this, R.anim.right_out));
			this.viewFlipper_scenicspots.showPrevious();
			return true;
		}
		return false;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			finish();

		}
		return super.onKeyDown(keyCode, event);
	}

}
