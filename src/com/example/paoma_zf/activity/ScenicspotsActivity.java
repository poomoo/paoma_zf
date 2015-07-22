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
		viewFlipper_scenicspots.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）
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
		map.put("title", "荔波");
		map.put("info",
				"\u3000\u3000"
						+ "荔波县位于贵州省南部，隶属黔南布依族苗族自治州。荔波山川秀丽，四季如春，是一块神秘的土地。被誉为地球腰带上的“绿宝石”。 荔波以原始、古朴、神奇、多姿多彩而著称，主要四大景区：水春河峡谷景区、樟江田园风光景区、大七孔景区、小七孔景区。 地球腰带上的“绿宝石”异彩纷呈，风光绝世。自然风光和民族风情古朴、神奇而神秘。");
		map.put("money", "105");
		map.put("img", R.drawable.pic_jd2);
		list.add(map);

		// for(int i=0;i<10;i++)
		// {
		// Map<String, Object> map1 = new HashMap<String, Object>();
		// map1.put("title", "体验房名称"+i);
		// map1.put("info", "\u3000\u3000"+"【贵阳】这里是详细介绍内容");
		// map1.put("money", "1470");
		// map1.put("img", R.drawable.pic_home);
		// list.add(map1);
		// }

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("title", "黄果树");
		map1.put(
				"info",
				"\u3000\u3000"
						+ "黄果树风景名胜区位于贵州省西南部，被中国国家地理杂志社评为“中国最美丽的地方”。 景区以黄果树瀑布景区为中心，分布有天星桥景区、陡坡塘景区、滴水滩瀑布景区等几大景区。  ");
		map1.put("money", "126");
		map1.put("img", R.drawable.pic_jd1);
		list.add(map1);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "赤水");
		map2.put(
				"info",
				"\u3000\u3000"
						+ "赤水市位于贵州省西北部，赤水河中下游，与四川省南部接壤，历为川黔边贸纽带、经济文化重镇，是黔北通往巴蜀的重要门户，素有“川黔锁钥”、“黔北边城”之称。 赤水山川秀丽，风景优美，全市森林覆盖率76.2%，居贵州省第一位。赤水风景名胜区是国务院唯一以行政区命名的国家级风景名胜区，素有“千瀑之市”、“丹霞之冠”、“竹子之乡”、“桫椤王国”的美誉。 ");
		map2.put("money", "81");
		map2.put("img", R.drawable.pic_jd3);
		list.add(map2);
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("title", "梵净山");
		map3.put(
				"info",
				"\u3000\u3000"
						+ "梵净山是武陵山脉的主峰，位于贵州省东北部江口、印江、松桃三县交界处，海拔2572米，是贵州的第一山。梵净山山体庞大雄浑，摩云接天，早在明初就被尊为“名岳之宗”，是著名的佛教圣地。1986年，梵净山被列为国家级自然保护区，保护区总面积567平方公里，同年又荣选入国际生物圈保护区网，被誉为“地球和人类之宝”，是中国14个加入联合国“人与生物圈”世界性自然保护区的成员之一。 梵净山被认定为世界上同纬度保护最完好的原始森林， ");
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

			// ImageView对象(iv_photo)必须做如下设置后，才能获取其中的图像

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
		// 将触屏事件交给手势识别类处理

		viewFlipper_scenicspots.stopFlipping(); // 点击事件后，停止自动播放
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
			// 设置View进入和退出的动画效果
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
