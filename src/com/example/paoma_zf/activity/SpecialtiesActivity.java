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
		viewFlipper_specialties.setAutoStart(true); // �����Զ����Ź��ܣ�����¼���ǰ�Զ����ţ�
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
	        map.put("title", "�������");
	        map.put("info", "\u3000\u3000"+"���ڱ���Ϊ���֮��Ĺ���ʡ�������������أ����Ƕ�����ͳ���ֹ���Ʒ��������������������300������ʷ��������峯����Ϊ��Ʒ�������������������õ��ز�Сˮ����������ڳ������ȣ��ܱ����ּᣬ�������ѣ����׳�����������ʴ�������ɫԲ����������Բ�α����ɫ���ѣ�Ϊ�����ϳˡ�");
	        map.put("money", "300");
	        map.put("img", R.drawable.pic_tc4);
	        list.add(map);
	        
	    
//	         for(int i=0;i<10;i++)
//	         {
//	        	    Map<String, Object> map1 = new HashMap<String, Object>();
//	    	        map1.put("title", "���鷿����"+i);
//	    	        map1.put("info", "\u3000\u3000"+"����������������ϸ��������");
//	    	        map1.put("money", "1470");
//	    	        map1.put("img", R.drawable.pic_home);
//	    	        list.add(map1);
//	         }
	        
	        Map<String, Object> map1 = new HashMap<String, Object>();
	        map1.put("title", "��ɽ����");
	        map1.put("info", "\u3000\u3000"+"��ɽ�����������ɽ�����塪����ɽ��´��ӡ������������������Ͻ��.����ɽ��Բ��ƽ������ǵ���ͬγ����������Դ��ḻ�ͱ��������õ���ɫ���⣬�����Ϲ����롰��������Ȧ��������֮һ��1986�걻����Ժȷ��Ϊ���Ҽ���Ȼ��������ӡ����λ����ɽ��´��ȫ�������ȴ���ůʪ�󼾷�����ůʪͬ��������ͬ�ڣ��ؾ��ڽ�����棬�꽵������1100�������ң���ƽ������16.8��");
	        map1.put("money", "675");
	        map1.put("img", R.drawable.pic_tc1);
	        list.add(map1);
	        
	        
	        Map<String, Object> map2 = new HashMap<String, Object>();
	        map2.put("title", "�����ܲ���");
	        map2.put("info", "\u3000\u3000"+"�����ܲ����ǹ���ʡͭ���н����ص��ز����������ܲ����ķ�չ���������꣬�����ܲ���Ƥ�������ۡ�ζ����Զ��������������״���ܲ��������󣬳�Ϊ�ܲ��������ܲ������ҵ����־֤���̱ꡣ�����ܲ����ֽС�����ͷ����");
	        map2.put("money", "2300");
	        map2.put("img", R.drawable.pic_tc2);
	        list.add(map2);
	        
	        
	        Map<String, Object> map3 = new HashMap<String, Object>();
	        map3.put("title", "�غ�ɳ�ӿ�����");
	        map3.put("info", "\u3000\u3000"+"ɳ�ӿ������1858������ɳ����ɳ�Ӵ�һ����ʼ���֡������Ⱥɽ������Ϫˮ�ݺᡢ��ɽ��ˮ����ɽ������ɽ���۰ӽ����棬ɭ�ָ����ʴ�36%���γ�������ͬ�ڣ�����ͬ������ҹ�²���С�������Ե������ص㡣���������κι�ҵ��ҵ���޻�����Ⱦ��ɽ��ˮ�㣬�������¸�ɳ�ӿ��������������˶��صĵ�������򻷾���");
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
				
				// ImageView����(iv_photo)�������������ú󣬲��ܻ�ȡ���е�ͼ��

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
        // �������¼���������ʶ���ദ��  
    	
    	viewFlipper_specialties.stopFlipping();				// ����¼���ֹͣ�Զ�����
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
            //����View������˳��Ķ���Ч��  
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
