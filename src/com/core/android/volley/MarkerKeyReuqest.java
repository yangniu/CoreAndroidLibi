package com.core.android.volley;

import java.io.UnsupportedEncodingException;

import android.text.TextUtils;

import com.core.android.volley.toolbox.HttpHeaderParser;


public class MarkerKeyReuqest extends Request<String> {

	private boolean responseSucc=false;
	public MarkerKeyReuqest(String param) {
		super(Method.GET, "http://nfframe.sinaapp.com/api.php?key="+param,null);
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		if(response.statusCode==200){
			String charset = HttpHeaderParser.parseCharset(response.headers);
			try {
				String str = new String(response.data,charset);
				setResponseCode(!TextUtils.isEmpty(str)&&str.equals("success"));
			} catch (UnsupportedEncodingException e) {
			}
		}
		return null;
	}
	@Override
	protected void deliverResponse(String response) {
	}

	public boolean getResponseCode() {
		return responseSucc;
	}

	public void setResponseCode(boolean responseSucc) {
		this.responseSucc = responseSucc;
	}
	
	
}
