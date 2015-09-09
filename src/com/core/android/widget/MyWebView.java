/**
 * Project Name:SinaSports
 * File Name:MyWebView.java
 * Package Name:cn.com.sina.sports.widget
 * Date:2014-3-12上午10:32:46
 * Copyright (c) 2014, boqing@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.core.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.webkit.WebView;

/**
 * ClassName:MyWebView <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-3-12 上午10:32:46 <br/>
 * 
 * @author boqing
 * @version
 * @since JDK 1.6
 * @see
 */
public class MyWebView extends WebView {

	private float yDistance, yLast;
	/**
	 * @brief 手势滑动的差值
	 */
	public int mTouchSlop = 20;

	public MyWebView(Context context) {
		super(context);
		intiData();
	}

	public MyWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		intiData();
	}

	public MyWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		intiData();
	}

	private void intiData() {
		ViewConfiguration cfg = ViewConfiguration.get(getContext());
		mTouchSlop = cfg.getScaledTouchSlop();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			yDistance = 0f;
			yLast = 0f;
			yLast = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curY = ev.getY();
			yDistance = curY - yLast;
			if (Math.abs(yDistance) > mTouchSlop) {
				return true;
			}
		}
		return super.onInterceptTouchEvent(ev);
	}

}
