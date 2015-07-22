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
import com.example.paoma_zf.view.ListViewForScrollView;

public class SpecialtiesActivity extends BaseActivity implements OnGestureListener{
	
	ViewFlipper viewFlipper_specialties;
	 private GestureDetector detector; 
	 private static final float FLING_MIN_DISTANCE = 0;
	 ScrollView scrollView_specialties;
	 ListViewForScrollView lv_specialties;
	 LinearLayout linearLayout_specialties_return;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specialties);
		init();
	}
	
	public void init() {
		detector = new GestureDetector(this); 
		linearLayout_specialties_return= (LinearLayout) findViewById(R.id.linearLayout_specialties_return);
		linearLayout_specialties_return.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		viewFlipper_specialties = (ViewFlipper) findViewById(R.id.viewFlipper_specialties);
		viewFlipper_specialties.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		viewFlipper_specialties.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));
		viewFlipper_specialties.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）
		viewFlipper_specialties.setFlipInterval(3000);
		if (viewFlipper_specialties.isAutoStart()
				&& !viewFlipper_specialties.isFlipping()) {
			viewFlipper_specialties.startFlipping();

		}
		viewFlipper_specialties.addView(addImageById(R.drawable.pic_tc1));
		viewFlipper_specialties.addView(addImageById(R.drawable.pic_tc2));
		viewFlipper_specialties.addView(addImageById(R.drawable.pic_tc3));
		
		scrollView_specialties=(ScrollView)findViewById(R.id.scrollView_specialties);
		scrollView_specialties.smoothScrollTo(0, 0);
		
		
		lv_specialties=(ListViewForScrollView)findViewById(R.id.lv_specialties);
	
		
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.style_lvhomepage,
                new String[]{"title","info","img","money"},
                new int[]{R.id.textView_style_homepagetitle,R.id.textView_style_homepagecontent,R.id.imageView_style_homepage,R.id.textView_style_homepagemoney});
		
		lv_specialties.setAdapter(adapter);
		
		lv_specialties.setOnItemClickListener(new Lvitemclicklistener()); 
	}
	
	
	 private List<Map<String, Object>> getData() {
	        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	 
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("title", "玉屏箫笛");
	        map.put("info", "\u3000\u3000"+"产于被誉为箫笛之乡的贵州省玉屏侗族自治县，它是侗乡民传统的手工艺品和民族乐器，至今已有300多年历史。箫笛在清朝被列为贡品，故又名贡箫。玉屏箫笛用当地产小水竹制作，竹节长而均匀，管壁肉厚持坚，不易破裂，不易虫蛀。箫笛音质纯正，音色圆润，尤其是椭圆形扁箫，音色更佳，为萧中上乘。");
	        map.put("money", "300");
	        map.put("img", R.drawable.pic_tc4);
	        list.add(map);
	        
	    
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
	        map1.put("info", "\u3000\u3000"+"梵净山翠峰茶产自武陵山脉主峰——梵净山西麓的印江土家族苗族自治县辖区.。梵净山方圆百平方公里，是地球同纬度上生物资源最丰富和保存得最完好的绿色宝库，被联合国列入“人与生物圈保护网”之一，1986年被国务院确定为国家级自然保护区。印江县位于梵净山西麓，全县属亚热带温暖湿润季风气候，暖湿同季，雨热同期，县境内降雨丰沛，年降雨量在1100毫米左右，年平均气温16.8℃");
	        map1.put("money", "675");
	        map1.put("img", R.drawable.pic_tc1);
	        list.add(map1);
	        
	        
	        Map<String, Object> map2 = new HashMap<String, Object>();
	        map2.put("title", "江口萝卜猪");
	        map2.put("info", "\u3000\u3000"+"江口萝卜猪是贵州省铜仁市江口县的特产。“江口萝卜猪”的发展历经数百年，所产萝卜猪皮薄、肉嫩、味美而远近闻名，因其形状像萝卜，长不大，称为萝卜猪。江口萝卜猪获国家地理标志证明商标。江口萝卜猪又叫“钻子头”。");
	        map2.put("money", "2300");
	        map2.put("img", R.drawable.pic_tc2);
	        list.add(map2);
	        
	        
	        Map<String, Object> map3 = new HashMap<String, Object>();
	        map3.put("title", "沿河沙子空心李");
	        map3.put("info", "\u3000\u3000"+"沙子空心李从1858年起在沙子镇沙坝村一带开始栽种。因产地群山环抱、溪水纵横、绿山碧水、低山、低中山、槽坝交错并存，森林覆盖率达36%，形成了雨热同期，光温同步，昼夜温差大和小气候明显的气候特点。产区内无任何工业企业，无化工污染，山青水秀，空气清新给沙子空心李的生长造就了独特的地理和气候环境。");
	        map3.put("money", "15");
	        map3.put("img", R.drawable.pic_tc3);
	        list.add(map3);
	     
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
				
				Intent intent = new Intent(SpecialtiesActivity.this, InformationActivity.class);
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
    	
    	viewFlipper_specialties.stopFlipping();				// 点击事件后，停止自动播放
    	viewFlipper_specialties.setAutoStart(false);
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
        	this.viewFlipper_specialties.setInAnimation(AnimationUtils.loadAnimation(this,  
                    R.anim.left_in));  
            this.viewFlipper_specialties.setOutAnimation(AnimationUtils.loadAnimation(this,  
                    R.anim.left_out)); 
            this.viewFlipper_specialties.showNext();  
            return true;  
        }  
        if (e1.getX() - e2.getX() < -FLING_MIN_DISTANCE) {  
        	 this.viewFlipper_specialties.setInAnimation(AnimationUtils.loadAnimation(this,  
                     R.anim.right_in));  
             this.viewFlipper_specialties.setOutAnimation(AnimationUtils.loadAnimation(this,  
                     R.anim.right_out)); 
            this.viewFlipper_specialties.showPrevious();  
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
