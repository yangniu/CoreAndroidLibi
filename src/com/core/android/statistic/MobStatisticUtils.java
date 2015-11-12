package com.core.android.statistic;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/***
 * 统计事件
 * @author niufei
 *
 */
public class MobStatisticUtils {

	private static MobStatisticUtils statisticUtils;
	private MobStatisticUtils(){
	}
	public static MobStatisticUtils getInstance(){
		if(statisticUtils==null){
			synchronized (MobStatisticUtils.class) {
				if(statisticUtils==null){
					statisticUtils =new MobStatisticUtils();
				}
			}
		}
		return statisticUtils;
	}
	
	public void onStatisticResume(final Context context){
		new Thread(new Runnable() {
			@Override
			public void run() {
				MobclickAgent.onResume(context);
			}
		}).start();
		
	}
	
	public void onStatisticPause(final Context context){
		new Thread(new Runnable() {
			@Override
			public void run() {
				MobclickAgent.onPause(context);
			}
		}).start();
		
	}
	
	public void onFragResume(final String pageName){
		new Thread(new Runnable() {
			@Override
			public void run() {
				MobclickAgent.onPageStart(pageName);
			}
		}).start();
	}
	
	public void onFragPause(final String pageName){
		new Thread(new Runnable() {
			@Override
			public void run() {	
				MobclickAgent.onPageEnd(pageName);
			}
		}).start();
	}
	
	public static void onEvent(final Context context,final String value){
		new Thread(new Runnable() {
			@Override
			public void run() {	
				MobclickAgent.onEvent(context, value);
			}
		}).start();
	}
	
	public static void onEvent(final Context context,final String value,final String param){
		new Thread(new Runnable() {
			@Override
			public void run() {	
				MobclickAgent.onEvent(context, value, param);
			}
		}).start();
	}
}
