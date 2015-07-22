package com.example.paoma_zf.activity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
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

import com.example.paoma_zf.R;
import com.example.paoma_zf.activity.SpecialtiesActivity.Lvitemclicklistener;
import com.example.paoma_zf.view.ListViewForScrollView;

public class SurroundingActivity extends BaseActivity implements OnGestureListener{
	
	ViewFlipper viewFlipper_surrounding;
	 private GestureDetector detector; 
	 private static final float FLING_MIN_DISTANCE = 0;
	 ScrollView scrollView_surrounding;
	 ListViewForScrollView lv_surrounding;
	 LinearLayout linearLayout_surrounding_return;
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_surrounding);
		init();
	}
	
	public void init() {
		detector = new GestureDetector(this); 
		
		linearLayout_surrounding_return = (LinearLayout) findViewById(R.id.linearLayout_surrounding_return);
		linearLayout_surrounding_return.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		viewFlipper_surrounding = (ViewFlipper) findViewById(R.id.viewFlipper_surrounding);
		viewFlipper_surrounding.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		viewFlipper_surrounding.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		viewFlipper_surrounding.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）
		viewFlipper_surrounding.setFlipInterval(3000);
		if (viewFlipper_surrounding.isAutoStart()
				&& !viewFlipper_surrounding.isFlipping()) {
			viewFlipper_surrounding.startFlipping();

		}
		viewFlipper_surrounding.addView(addImageById(R.drawable.pic_jd1));
		viewFlipper_surrounding.addView(addImageById(R.drawable.pic_kf1));
		viewFlipper_surrounding.addView(addImageById(R.drawable.pic_tc1));
		
		scrollView_surrounding=(ScrollView)findViewById(R.id.scrollView_surrounding);
		scrollView_surrounding.smoothScrollTo(0, 0);
		
		
		lv_surrounding=(ListViewForScrollView)findViewById(R.id.lv_surrounding);
	
		
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.style_lvhomepage,
                new String[]{"title","info","img","money"},
                new int[]{R.id.textView_style_homepagetitle,R.id.textView_style_homepagecontent,R.id.imageView_style_homepage,R.id.textView_style_homepagemoney});
		
		lv_surrounding.setAdapter(adapter);
		
		lv_surrounding.setOnItemClickListener(new Lvitemclicklistener()); 
	}
	
	
	 private List<Map<String, Object>> getData() {
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	 
//	        Map<String, Object> map = new HashMap<String, Object>();
//	        map.put("title", "体验房名称");
//	        map.put("info", "\u3000\u3000"+"【贵阳】这里是详细介绍内容这里是详细介绍内容这里是详细介绍内容这里是详细介绍内容这里是详细介绍内容");
//	        map.put("money", "1470");
//	        map.put("img", R.drawable.pic_home);
//	        list.add(map);
	        
	    
//	         for(int i=0;i<10;i++)
//	         {
//	        	    Map<String, Object> map1 = new HashMap<String, Object>();
//	    	        map1.put("title", "体验房名称"+i);
//	    	        map1.put("info", "\u3000\u3000"+"【贵阳】这里是详细介绍内容");
//	    	        map1.put("money", "1470");
//	    	        map1.put("img", R.drawable.pic_home);
//	    	        list.add(map1);
//	         }
	        
	        Map<String, Object> map1 = new HashMap<String, Object>();
	        map1.put("title", "梵净山翠峰茶");
	        map1.put("info", "\u3000\u3000"+"梵净山翠峰茶产自武陵山脉主峰——梵净山西麓的印江土家族苗族自治县辖区.。梵净山方圆百平方公里，是地球同纬度上生物资源最丰富和保存得最完好的绿色宝库，被联合国列入“人与生物圈保护网”之一，1986年被国务院确定为国家级自然保护区。印江县位于梵净山西麓，全县属亚热带温暖湿润季风气候，暖湿同季，雨热同期，县境内降雨丰沛，年降雨量在1100毫米左右，年平均气温16.8℃，年日照时间长达1296小时，无霜期近300天，年日照率在35%以下，常出现多云间晴或阴天天气，云雾多，光照柔和，多漫射光，既无高温热害，也无冻害严寒.");
	        map1.put("money", "675");
	        map1.put("img", R.drawable.pic_tc1);
	        list.add(map1);
	        
	        Map<String, Object> map2 = new HashMap<String, Object>();
	        map2.put("title", "江口萝卜猪");
	        map2.put("info", "\u3000\u3000"+"江口萝卜猪是贵州省铜仁市江口县的特产。“江口萝卜猪”的发展历经数百年，所产萝卜猪皮薄、肉嫩、味美而远近闻名，因其形状像萝卜，长不大，称为萝卜猪。江口萝卜猪获国家地理标志证明商标。江口萝卜猪又叫“钻子头”。该品种是江口县人民在长期的放牧饲养过程中，通过人工选育选配和自然选择形成的特有品种。具有体型矮小、耐粗饲、抗逆性强等特点。萝卜猪肉因皮薄骨细，肉质细嫩，肉味鲜美，无论从体型还是肉质或口感上均与白萝卜十分相似而得名。");
	        map2.put("money", "2300");
	        map2.put("img", R.drawable.pic_tc2);
	        list.add(map2);
	        
	    	Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("title", "梵净山");
			map3.put(
					"info",
					"\u3000\u3000"
							+ "梵净山是武陵山脉的主峰，位于贵州省东北部江口、印江、松桃三县交界处，海拔2572米，是贵州的第一山。梵净山山体庞大雄浑，摩云接天，早在明初就被尊为“名岳之宗”，是著名的佛教圣地。1986年，梵净山被列为国家级自然保护区，保护区总面积567平方公里，同年又荣选入国际生物圈保护区网，被誉为“地球和人类之宝”，是中国14个加入联合国“人与生物圈”世界性自然保护区的成员之一。 梵净山被认定为世界上同纬度保护最完好的原始森林.");
			map3.put("money", "100");
			map3.put("img", R.drawable.pic_jd4);
			list.add(map3);
			
			Map<String, Object> map4 = new HashMap<String, Object>();
			map4.put("title", "赤水");
			map4.put(
					"info",
					"\u3000\u3000"
							+ "赤水市位于贵州省西北部，赤水河中下游，与四川省南部接壤，历为川黔边贸纽带、经济文化重镇，是黔北通往巴蜀的重要门户，素有“川黔锁钥”、“黔北边城”之称。 赤水山川秀丽，风景优美，全市森林覆盖率76.2%，居贵州省第一位。赤水风景名胜区是国务院唯一以行政区命名的国家级风景名胜区，素有“千瀑之市”、“丹霞之冠”、“竹子之乡”、“桫椤王国”的美誉。 赤水因美丽而神秘的赤水河贯穿全境而得名，更因中国工农红军“四渡赤水”以及赤水丹霞世界自然遗产而扬名中外。 ");
			map4.put("money", "81");
			map4.put("img", R.drawable.pic_jd3);
			list.add(map4);
			
		       Map<String, Object> map = new HashMap<String, Object>();
		        map.put("title", "九州十里锦城");
		        map.put("info", "\u3000\u3000"+"九州十里锦城，是九州地产携手云岩区政府，斥重资对三马片区进行升级打造的7000亩的生态人居品质大盘。以HOPSCA开发模式，倾力打造一个集聚高层住宅、公寓、写字楼、大型商业中心、购物中心、学校教育等于一体的大型城市综合体。地处双城门户核心地段，坐拥两城区繁华。项目落子三马片区，是联结新老城区的重要枢纽。项目规划一期ABCDE组团，总建筑面积为212.9万方，住宅为88.38万方，商业为71.32万方，容积率为4.14。");
		        map.put("money", "4100");
		        map.put("img", R.drawable.pic_kf4);
		        list.add(map);
	        
		        Map<String, Object> map5 = new HashMap<String, Object>();
		        map5.put("title", "花果园");
		        map5.put("info", "\u3000\u3000"+"项目介绍：花果园项目位于贵阳市主城区中心位置，总占地面积6000余亩，总投资900亿元，总建筑面积1830万m2，包括1230万m2住宅、200万m2办公、200万m2商业、150万m2公寓、50万m2公建。花果园项目地处二环四路城市带中的贵黄路现代商贸商务功能板块，系二环四路城市带建设龙头项目，是贵阳主城区联系贵安新区的桥头堡。花果园项目以建设贵阳都会新中心为己任，建设有以贵州地标级406米双子塔、300米双子塔领衔的“大贵阳”战略下的核心CBD;有以30万m2环球商业广场.");
		        map5.put("money", "4860");
		        map5.put("img", R.drawable.pic_kf1);
		        list.add(map5);
		        
		        
	        return list;
	    }
	
		class Lvitemclicklistener implements OnItemClickListener
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView content=(TextView)arg1.findViewById(R.id.textView_style_homepagecontent);
				TextView title=(TextView)arg1.findViewById(R.id.textView_style_homepagetitle);
				TextView money=(TextView)arg1.findViewById(R.id.textView_style_homepagemoney);
				ImageView img=(ImageView)arg1.findViewById(R.id.imageView_style_homepage);
				
				// ImageView对象(iv_photo)必须做如下设置后，才能获取其中的图像

				img.setDrawingCacheEnabled(true);
				
				Bitmap obmp=Bitmap.createBitmap(img.getDrawingCache());
				
				img.setDrawingCacheEnabled(false);
				
				ByteArrayOutputStream bs = new ByteArrayOutputStream();
				
				obmp.compress(Bitmap.CompressFormat.JPEG,100,bs);
//				Toast.makeText(HomePageActivity.this, title.getText().toString(), 500).show();
				
				Intent intent = new Intent(SurroundingActivity.this, InformationActivity.class);
				intent.putExtra("title", title.getText().toString());
				intent.putExtra("content", content.getText().toString());
				intent.putExtra("img", bs.toByteArray());
				intent.putExtra("money", money.getText().toString());

				
				
	    		startActivity(intent);
			}
			
		}
	 
	
    public View addImageById(int id){ 
        ImageView iv = new ImageView(this); 
        iv.setScaleType(ScaleType.FIT_XY);

        iv.setImageResource(id); 
           
        return iv; 
    }
    
    
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
        // 将触屏事件交给手势识别类处理  
    	
    	viewFlipper_surrounding.stopFlipping();				// 点击事件后，停止自动播放
    	viewFlipper_surrounding.setAutoStart(false);
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
            //设置View进入和退出的动画效果  
        	this.viewFlipper_surrounding.setInAnimation(AnimationUtils.loadAnimation(this,  
                    R.anim.left_in));  
            this.viewFlipper_surrounding.setOutAnimation(AnimationUtils.loadAnimation(this,  
                    R.anim.left_out)); 
            this.viewFlipper_surrounding.showNext();  
            return true;  
        }  
        if (e1.getX() - e2.getX() < -FLING_MIN_DISTANCE) {  
        	 this.viewFlipper_surrounding.setInAnimation(AnimationUtils.loadAnimation(this,  
                     R.anim.right_in));  
             this.viewFlipper_surrounding.setOutAnimation(AnimationUtils.loadAnimation(this,  
                     R.anim.right_out)); 
            this.viewFlipper_surrounding.showPrevious();  
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
