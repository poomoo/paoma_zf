package com.example.paoma_zf.fragment;


import com.example.paoma_zf.R;
import com.example.paoma_zf.activity.LookhomeActivity;
import com.example.paoma_zf.activity.ScenicspotsActivity;
import com.example.paoma_zf.activity.SpecialtiesActivity;
import com.example.paoma_zf.activity.SurroundingActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("NewApi") public class NavigationFragment extends Fragment{
	
	LinearLayout linearLayout_navigation_shopping,linearLayout_navigation_scenicspots,linearLayout_navigation_specialties,linearLayout_navigation_surrounding;
	LinearLayout linearLayout_navigation_shoppingcolor,linearLayout_navigation_scenicspotscolor,linearLayout_navigation_specialtiescolor,linearLayout_navigation_surroundingcolor;
	
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
//		return super.onCreateView(inflater, container, savedInstanceState);
		
		return inflater.inflate(com.example.paoma_zf.R.layout.fargment_navigation, container, false);
		
	}


	public void init()
	{
		//点击区域
		linearLayout_navigation_shopping=(LinearLayout)getView().findViewById(R.id.linearLayout_navigation_shopping);
		linearLayout_navigation_shopping.setTag("shopping");
		linearLayout_navigation_shopping.setOnClickListener(new NavigationClickListener());
		linearLayout_navigation_scenicspots=(LinearLayout)getView().findViewById(R.id.linearLayout_navigation_scenicspots);
		linearLayout_navigation_scenicspots.setTag("scenicspots");
		linearLayout_navigation_scenicspots.setOnClickListener(new NavigationClickListener());
		linearLayout_navigation_specialties=(LinearLayout)getView().findViewById(R.id.linearLayout_navigation_specialties);
		linearLayout_navigation_specialties.setTag("specialties");
		linearLayout_navigation_specialties.setOnClickListener(new NavigationClickListener());
		linearLayout_navigation_surrounding=(LinearLayout)getView().findViewById(R.id.linearLayout_navigation_surrounding);
		linearLayout_navigation_surrounding.setTag("surrounding");
		linearLayout_navigation_surrounding.setOnClickListener(new NavigationClickListener());
		
		//变色区域
		linearLayout_navigation_shoppingcolor=(LinearLayout)getView().findViewById(R.id.linearLayout_navigation_shoppingcolor);
		linearLayout_navigation_scenicspotscolor=(LinearLayout)getView().findViewById(R.id.linearLayout_navigation_scenicspotscolor);
		linearLayout_navigation_specialtiescolor=(LinearLayout)getView().findViewById(R.id.linearLayout_navigation_specialtiescolor);
		linearLayout_navigation_surroundingcolor=(LinearLayout)getView().findViewById(R.id.linearLayout_navigation_surroundingcolor);
		
		setNavigationBackgroundResource();
		
	}
	
	
	public void setNavigationBackgroundResource()
	{
		String[] thisactivity=getActivity().toString().split("@");
		linearLayout_navigation_shoppingcolor.setBackgroundResource(R.color.white);
		linearLayout_navigation_scenicspotscolor.setBackgroundResource(R.color.white);
		linearLayout_navigation_specialtiescolor.setBackgroundResource(R.color.white);
		linearLayout_navigation_surroundingcolor.setBackgroundResource(R.color.white);
		
		linearLayout_navigation_shopping.setEnabled(true);
		linearLayout_navigation_scenicspots.setEnabled(true);
		linearLayout_navigation_specialties.setEnabled(true);
		linearLayout_navigation_surrounding.setEnabled(true);
	
		if(thisactivity[0].equals("com.example.paoma_zf.activity.LookhomeActivity"))
		{
			linearLayout_navigation_shoppingcolor.setBackgroundResource(R.color.green);
			linearLayout_navigation_shopping.setEnabled(false);
		}
		if(thisactivity[0].equals("com.example.paoma_zf.activity.ScenicspotsActivity"))
		{
			linearLayout_navigation_scenicspotscolor.setBackgroundResource(R.color.green);
			linearLayout_navigation_scenicspots.setEnabled(false);
		}
		if(thisactivity[0].equals("com.example.paoma_zf.activity.SpecialtiesActivity"))
		{
//			Toast.makeText(getActivity(), "ssssss", Toast.LENGTH_SHORT).show();
			linearLayout_navigation_specialtiescolor.setBackgroundResource(R.color.green);
			linearLayout_navigation_specialties.setEnabled(false);
		}
		if(thisactivity[0].equals("com.example.paoma_zf.activity.SurroundingActivity"))
		{
			linearLayout_navigation_surroundingcolor.setBackgroundResource(R.color.green);
			linearLayout_navigation_surrounding.setEnabled(false);
		}
	
		
		
	}
	
	
	class NavigationClickListener implements OnClickListener {       
    	@Override    
    	public void onClick(View v) {       
    		// TODO Auto-generated method stub       

    	
    		

    		if(v.getTag().equals("shopping"))
    		{
    			Intent intent = new Intent();
    			intent.setClass(getActivity(), LookhomeActivity.class);
        		startActivity(intent);
//        		getActivity().overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_out_of_right);
        		getActivity().finish();

    		}
    		if(v.getTag().equals("scenicspots"))
    		{
    			Intent intent = new Intent();
    			intent.setClass(getActivity(), ScenicspotsActivity.class);
        		startActivity(intent);
        		getActivity().finish();
      
    		}
    		if(v.getTag().equals("specialties"))
    		{
    			Intent intent = new Intent();
    			intent.setClass(getActivity(), SpecialtiesActivity.class);
        		startActivity(intent);
        		getActivity().finish();
       
    		}
    		if(v.getTag().equals("surrounding"))
    		{
    			Intent intent = new Intent();
    			intent.setClass(getActivity(), SurroundingActivity.class);
        		startActivity(intent);
        		getActivity().finish();
    
    		}

    	
    		  
    	} 
    	}

}
