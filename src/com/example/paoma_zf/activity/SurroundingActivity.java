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
		viewFlipper_surrounding.setAutoStart(true); // �����Զ����Ź��ܣ�����¼���ǰ�Զ����ţ�
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
//	        map.put("title", "���鷿����");
//	        map.put("info", "\u3000\u3000"+"����������������ϸ����������������ϸ����������������ϸ����������������ϸ����������������ϸ��������");
//	        map.put("money", "1470");
//	        map.put("img", R.drawable.pic_home);
//	        list.add(map);
	        
	    
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
	        map1.put("info", "\u3000\u3000"+"��ɽ�����������ɽ�����塪����ɽ��´��ӡ������������������Ͻ��.����ɽ��Բ��ƽ������ǵ���ͬγ����������Դ��ḻ�ͱ��������õ���ɫ���⣬�����Ϲ����롰��������Ȧ��������֮һ��1986�걻����Ժȷ��Ϊ���Ҽ���Ȼ��������ӡ����λ����ɽ��´��ȫ�������ȴ���ůʪ�󼾷�����ůʪͬ��������ͬ�ڣ��ؾ��ڽ�����棬�꽵������1100�������ң���ƽ������16.8�棬������ʱ�䳤��1296Сʱ����˪�ڽ�300�죬����������35%���£������ֶ��Ƽ������������������࣬������ͣ�������⣬���޸����Ⱥ���Ҳ�޶����Ϻ�.");
	        map1.put("money", "675");
	        map1.put("img", R.drawable.pic_tc1);
	        list.add(map1);
	        
	        Map<String, Object> map2 = new HashMap<String, Object>();
	        map2.put("title", "�����ܲ���");
	        map2.put("info", "\u3000\u3000"+"�����ܲ����ǹ���ʡͭ���н����ص��ز����������ܲ����ķ�չ���������꣬�����ܲ���Ƥ�������ۡ�ζ����Զ��������������״���ܲ��������󣬳�Ϊ�ܲ��������ܲ������ҵ����־֤���̱ꡣ�����ܲ����ֽС�����ͷ������Ʒ���ǽ����������ڳ��ڵķ������������У�ͨ���˹�ѡ��ѡ�����Ȼѡ���γɵ�����Ʒ�֡��������Ͱ�С���ʹ��ǡ�������ǿ���ص㡣�ܲ�������Ƥ����ϸ������ϸ�ۣ���ζ���������۴����ͻ������ʻ�ڸ��Ͼ�����ܲ�ʮ�����ƶ�������");
	        map2.put("money", "2300");
	        map2.put("img", R.drawable.pic_tc2);
	        list.add(map2);
	        
	    	Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("title", "��ɽ");
			map3.put(
					"info",
					"\u3000\u3000"
							+ "��ɽ������ɽ�������壬λ�ڹ���ʡ���������ڡ�ӡ�����������ؽ��紦������2572�ף��ǹ��ݵĵ�һɽ����ɽɽ���Ӵ��ۻ룬Ħ�ƽ��죬���������ͱ���Ϊ������֮�ڡ����������ķ��ʥ�ء�1986�꣬��ɽ����Ϊ���Ҽ���Ȼ�������������������567ƽ�����ͬ������ѡ���������Ȧ��������������Ϊ�����������֮���������й�14���������Ϲ�����������Ȧ����������Ȼ�������ĳ�Ա֮һ�� ��ɽ���϶�Ϊ������ͬγ�ȱ�������õ�ԭʼɭ��.");
			map3.put("money", "100");
			map3.put("img", R.drawable.pic_jd4);
			list.add(map3);
			
			Map<String, Object> map4 = new HashMap<String, Object>();
			map4.put("title", "��ˮ");
			map4.put(
					"info",
					"\u3000\u3000"
							+ "��ˮ��λ�ڹ���ʡ����������ˮ�������Σ����Ĵ�ʡ�ϲ���������Ϊ��ǭ��óŦ���������Ļ�������ǭ��ͨ���������Ҫ�Ż������С���ǭ��Կ������ǭ���߳ǡ�֮�ơ� ��ˮɽ���������羰������ȫ��ɭ�ָ�����76.2%���ӹ���ʡ��һλ����ˮ�羰��ʤ���ǹ���ԺΨһ�������������Ĺ��Ҽ��羰��ʤ�������С�ǧ��֮�С�������ϼ֮�ڡ���������֮�硱��������������������� ��ˮ�����������صĳ�ˮ�ӹᴩȫ���������������й���ũ������Ķɳ�ˮ���Լ���ˮ��ϼ������Ȼ�Ų����������⡣ ");
			map4.put("money", "81");
			map4.put("img", R.drawable.pic_jd3);
			list.add(map4);
			
		       Map<String, Object> map = new HashMap<String, Object>();
		        map.put("title", "����ʮ�����");
		        map.put("info", "\u3000\u3000"+"����ʮ����ǣ��Ǿ��ݵز�Я�������������������ʶ�����Ƭ���������������7000Ķ����̬�˾�Ʒ�ʴ��̡���HOPSCA����ģʽ����������һ�����۸߲�סլ����Ԣ��д��¥��������ҵ���ġ��������ġ�ѧУ��������һ��Ĵ��ͳ����ۺ��塣�ش�˫���Ż����ĵضΣ���ӵ��������������Ŀ��������Ƭ�������������ϳ�������Ҫ��Ŧ����Ŀ�滮һ��ABCDE���ţ��ܽ������Ϊ212.9�򷽣�סլΪ88.38�򷽣���ҵΪ71.32�򷽣��ݻ���Ϊ4.14��");
		        map.put("money", "4100");
		        map.put("img", R.drawable.pic_kf4);
		        list.add(map);
	        
		        Map<String, Object> map5 = new HashMap<String, Object>();
		        map5.put("title", "����԰");
		        map5.put("info", "\u3000\u3000"+"��Ŀ���ܣ�����԰��Ŀλ�ڹ���������������λ�ã���ռ�����6000��Ķ����Ͷ��900��Ԫ���ܽ������1830��m2������1230��m2סլ��200��m2�칫��200��m2��ҵ��150��m2��Ԣ��50��m2����������԰��Ŀ�ش�������·���д��еĹ��·�ִ���ó�����ܰ�飬ϵ������·���д�������ͷ��Ŀ���ǹ�����������ϵ����������ͷ��������԰��Ŀ�Խ����������������Ϊ���Σ��������Թ��ݵر꼶406��˫������300��˫�������εġ��������ս���µĺ���CBD;����30��m2������ҵ�㳡.");
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
				
				// ImageView����(iv_photo)�������������ú󣬲��ܻ�ȡ���е�ͼ��

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
        // �������¼���������ʶ���ദ��  
    	
    	viewFlipper_surrounding.stopFlipping();				// ����¼���ֹͣ�Զ�����
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
            //����View������˳��Ķ���Ч��  
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
