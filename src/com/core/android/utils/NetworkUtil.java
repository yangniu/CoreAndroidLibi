package com.core.android.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ClassName: NetworkUtil <br/>
 * Function: isNetWorAvailable;getNetworkType <br/>
 * Reason: 联网检测. <br/>
 * date: 2013-8-6 上午11:17:53 <br/>
 * 
 * @author hushuan
 * @version
 */
public class NetworkUtil {
	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 *            环境对象
	 * @return true 有网络，false 无网络
	 */
	public static boolean isNetWorkAvailable(Context context) {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mActiveNetworkInfo = mConnectivityManager
				.getActiveNetworkInfo();
		if (mActiveNetworkInfo != null && mActiveNetworkInfo.isAvailable()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取网络类型
	 * 
	 * @param context
	 *            环境对象
	 * @return 网络类型，无网络时为空
	 */
	public static String getNetworkType(Context context) {
		String NetworkType = "";
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mActiveNetworkInfo = mConnectivityManager
				.getActiveNetworkInfo();
		if (mActiveNetworkInfo != null && mActiveNetworkInfo.isAvailable()) {
			if (mActiveNetworkInfo.getTypeName().equals("WIFI")) {
				NetworkType = mActiveNetworkInfo.getTypeName();
			} else {
				NetworkType = mActiveNetworkInfo.getExtraInfo();
			}
			return NetworkType;
		} else {
			NetworkType = "";
			return NetworkType;
		}
	}

	/**
	 * 根据输入流获取字节数组
	 * 
	 * @param inputStream
	 * @return byte[]
	 * @throws IOException
	 */
	public byte[] readStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		outputStream.close();
		inputStream.close();
		return outputStream.toByteArray();
	}

	/**
	 * 获得urlStr指定网络路径的字节流
	 * 
	 * @param urlStr
	 * @return byte[]
	 */
	public byte[] getBytesByUrl(String urlStr) {
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				return readStream(is);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public static boolean isWifiMode(Context context) {
		boolean isWifi = false;

		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifi = connManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI); // wifi
		if (wifi != null && wifi.isConnectedOrConnecting()) {
			isWifi = true;
		}
		return isWifi;
	}
}
