/**
 * Project Name:SinaSports
 * File Name:AppUncaughtException.java
 * Package Name:cn.com.sina.sports.app
 * Date:2013-9-26下午3:07:22
 * Copyright (c) 2013, yaoyao1@staff.sina.com.cn All Rights Reserved.
 *
 */

package com.core.android.app;

import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.util.Log;

import com.lidroid.xutils.util.LogUtils;

/**
 * ClassName:AppUncaughtException <br/>
 * Date: 2013-9-26 下午3:07:22 <br/>
 * 
 * @author kan212
 * @version
 * @since JDK 1.6
 * @see
 */
public class AppUncaughtException implements UncaughtExceptionHandler {

	private static AppUncaughtException appUncaughtException;

	public static AppUncaughtException getIntance() {
		if (appUncaughtException == null) {
			appUncaughtException = new AppUncaughtException();
		}
		return appUncaughtException;
	}

	private AppUncaughtException() {

	}

	public void init(Context context) {
		Thread.setDefaultUncaughtExceptionHandler(appUncaughtException);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		LogUtils.e("crash");
		ex.printStackTrace();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Log.e(getClass().getSimpleName(), e.getStackTrace().toString());
		}
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
