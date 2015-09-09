package com.core.android.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
/**
 * 
 * ClassName: MyViewPager <br/>
 * Function: 实现不跟上下滑动冲突的viewpager <br/>
 * date: 2014-7-28 下午3:34:18 <br/>
 *
 * @author yangboqing
 * @version
 */
public class MyViewPager extends ViewPager {

	private float xDistance,yDistance,xLast,yLast;
	private int mTouchSlop;

	public MyViewPager(Context context) {
		super(context);
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {  
		case MotionEvent.ACTION_DOWN:  
			xDistance = yDistance = 0f;  
			xLast = ev.getX();  
			yLast = ev.getY();  
			break;  
		case MotionEvent.ACTION_MOVE:  
			final float curX = ev.getX();  
			final float curY = ev.getY();  

			xDistance += Math.abs(curX - xLast);  
			yDistance += Math.abs(curY - yLast);  
			xLast = curX;  
			yLast = curY;  

			if(xDistance > yDistance){  
				if(xDistance > mTouchSlop&&xDistance * 0.5f > yDistance){	
					return true;
				}
			}
		}
		return super.onInterceptTouchEvent(ev);
	}
}
