package com.core.android.dialog;


import com.core.android.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProgressDialog extends Dialog{
	
	private  Context mcontext=null;
	private final int CENTERVIEWID=1;
	private final int PROGRESSBAR=2;
	public ProgressDialog(Context context) {
		super(context, R.style.expand_progress_dialog_style);
		mcontext=	context;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		RelativeLayout llpd=new RelativeLayout(mcontext);
		llpd.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
		RelativeLayout.LayoutParams centerpblp=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,1);
		centerpblp.addRule(RelativeLayout.CENTER_IN_PARENT);
		TextView centerview=new TextView(mcontext);
		centerview.setId(CENTERVIEWID);
		llpd.addView(centerview,centerpblp);
		
		ProgressBar pb=new ProgressBar(mcontext);
		pb.setIndeterminateDrawable(mcontext.getResources().getDrawable(R.drawable.expand_progressbar_bg));
		pb.setIndeterminate(false);
		pb.setId(PROGRESSBAR);
		RelativeLayout.LayoutParams pblp=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		pblp.addRule(RelativeLayout.BELOW,CENTERVIEWID);
		pblp.addRule(RelativeLayout.CENTER_HORIZONTAL);
//		Resources r = getContext().getResources();
//		float pxValue = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics());
//		int pxval=Float.floatToIntBits(pxValue);
		pblp.setMargins(0,100, 0, 0);
		llpd.addView(pb,pblp);
		this.setContentView(llpd);
	}
	/**
	 * 设定正在加载的状态
	 */
	public  void show(){
		// 点击对话框外部取消对话框显示
		setCanceledOnTouchOutside(true);
		super.show();  
	}
	/**
	 * 设定正在加载的状态
	 */
	public void show(boolean cancelTouchOutside) {
		// 点击对话框外部取消对话框显示
		setCanceledOnTouchOutside(cancelTouchOutside);
		super.show();
	}
	/**
	 * 设定未加载状态
	 */
	public void dismiss(){
			super.dismiss(); 
	}
	
}
