package com.example.paoma_zf.view;

import com.example.paoma_zf.R;

import android.content.Context;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MyGestureListener implements OnGestureListener{

	private Context context;
    private ViewFlipper vf;
    private static final float FLING_MIN_DISTANCE = 0;
    public MyGestureListener(Context context, ViewFlipper vf) {
            this.context = context;
            this.vf = vf;
    }

    @Override
    public boolean onDown(MotionEvent e) {
            // TODO Auto-generated method stub
            vf.setClickable(true);
            return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                    float velocityY) {
            // TODO Auto-generated method stub
            vf.setClickable(false);
//            if ((e1.getY() - e2.getY()) > 100) {
//                    if (vf.getCurrentView() == vf.getChildAt(0)) {
//                            Toast.makeText(context, "已经移至第一张", Toast.LENGTH_SHORT).show();
//                    } else {
//                            vf.setInAnimation(AnimationUtils.loadAnimation(context,
//                                            R.anim.left_in));
//                            vf.setOutAnimation(AnimationUtils.loadAnimation(context,
//                                            R.anim.left_out));
//                            vf.showPrevious();
//                            return true;
//                    }
//            } else if ((e1.getY() - e2.getY()) < -100) {
//                    if (vf.getCurrentView() == vf
//                                    .getChildAt(vf.getChildCount()-1)) {
//                            Toast.makeText(context, "已移至最后一张", Toast.LENGTH_SHORT).show();
//                    } else {
//                            this.vf.setInAnimation(AnimationUtils.loadAnimation(context,
//                                            R.anim.right_in));
//                            this.vf.setOutAnimation(AnimationUtils.loadAnimation(context,
//                                            R.anim.right_out));
//                            this.vf.showNext();
//                            return true;
//                    }
//            }
//            return false;
            
            
            
            
    		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE) {
			// 设置View进入和退出的动画效果
    			
    			
    			  vf.setInAnimation(AnimationUtils.loadAnimation(context,
                          R.anim.left_in));
    	            vf.setOutAnimation(AnimationUtils.loadAnimation(context,
                            R.anim.left_out));
            vf.showPrevious();
    			

			return true;
		}
		if (e1.getX() - e2.getX() < -FLING_MIN_DISTANCE) {
			
			
			
			   this.vf.setInAnimation(AnimationUtils.loadAnimation(context,
                       R.anim.right_in));
       this.vf.setOutAnimation(AnimationUtils.loadAnimation(context,
                       R.anim.right_out));
       this.vf.showNext();
       return true;
			
			

		}
		return false;
            
    }
    
    @Override
    public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub

    }
    
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                    float distanceY) {
            // TODO Auto-generated method stub
            return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
    }
	
}
