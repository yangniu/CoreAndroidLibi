/**
 * 
 * @author hushuan
 * @version 2012-10-24
 */
package com.core.android.utils;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.lidroid.xutils.util.LogUtils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * 常用的调用系统功能（发短信，打电话，发邮件等）
 */
public class CommonUtil {

	/**
	 * getVersionCode:得到当前程序版本号. <br/>
	 * 
	 * @author hushuan
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		int versionCode = 0;
		PackageManager manager = context.getPackageManager();
		PackageInfo info;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
			versionCode = info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * getVersionName:得到当前程序版本名称. <br/>
	 * 
	 * @author hushuan
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String versionName = "";
		PackageManager manager = context.getPackageManager();
		PackageInfo info;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
			versionName = info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/**
	 * 跳到发短信页面
	 * 
	 * @param context
	 * @param phoneNumber
	 * @param message
	 */
	public static void sendShortMessage(Context context, String phoneNumber,
			String message) {
		Uri uri = Uri.parse("smsto:" + phoneNumber);

		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("sms_body", message);

		context.startActivity(intent);
	}

	/**
	 * 群发短信
	 * 
	 * @param context
	 * @param list
	 * @param message
	 */
	public static void sendShortMessage(Context context, List<String> list,
			String message) {
		if (list == null || list.size() == 0) {
			return;
		}
		StringBuffer sb = new StringBuffer();
		for (String phone : list) {
			if (!TextUtils.isEmpty(phone))
				sb.append(phone).append(';');
		}
		String mobile = sb.deleteCharAt(sb.length() - 1).toString();
		sendShortMessage(context, mobile, message);
	}

	/**
	 * 如果安装了地图软件则根据address指定地址跳到地图软件
	 * 
	 * @param context
	 * @param address
	 */
	public static void go2Address(Context context, String address) {
		Uri uri = Uri.parse("geo:0,0?q=" + address);
		// 其他 geo URI 範例
		// geo:latitude,longitude
		// geo:latitude,longitude?z=zoom
		// geo:0,0?q=my+street+address
		// geo:0,0?q=business+near+city
		// google.streetview:cbll=lat,lng&cbp=1,yaw,,pitch,zoom&mz=mapZoom
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * 如果安装了邮件软件，则跳到邮件软件
	 * 
	 * @param context
	 * @param emailAddressList
	 *            邮件接收地址列表
	 * @param subject
	 *            邮件主题
	 * @param message
	 *            邮件内容
	 */
	public static void sendEmail(Context context,
			List<String> emailAddressList, String subject, String message) {

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
		String[] emailAddress = emailAddressList
				.toArray(new String[emailAddressList.size()]);

		// 设置邮件默认地址
		// ntent.EXTRA_EMAIL,Intent.EXTRA_CC,Intent.EXTRA_BCC分别用于传递“接受人地址列表”、“抄送人地址列表”和“密送人地址列表”
		intent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
		// 设置邮件默认标题
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		// 设置要默认发送的内容
		intent.putExtra(Intent.EXTRA_TEXT, message);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * 发送邮件
	 * 
	 * @param context
	 * @param emailAddress
	 * @param subject
	 * @param message
	 */
	public static void sendEmail(Context context, String emailAddress,
			String subject, String message) {
		List<String> addressList = new ArrayList<String>();
		addressList.add(emailAddress);
		sendEmail(context, addressList, subject, message);
	}

	/**
	 * 跳到浏览器，打开url指定网页
	 * 
	 * @param context
	 * @param url
	 */
	public static void go2Web(Context context, String url) {
		if (url == null) {
			return;
		}
		if (!url.startsWith("http://")) {
			url = "http://" + url;
		}
		Uri uri = Uri.parse(url);

		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * 电话拨号
	 * 
	 * @param context
	 * @param number
	 */
	public static void dial(Context context, String number) {
		if (number == null) {
			return;
		}

		Uri uri = Uri.parse("tel:" + number);

		Intent intent = new Intent(Intent.ACTION_DIAL, uri);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	/**
	 * addShorCut:增加桌面快捷方式的实现方式. <br/>
	 * 
	 * @author hushuan
	 * @param context
	 * @param className
	 *            要启动的类名
	 */
	public static void addShorCut(Context context, String className) {
		// 这里需要用action作为intent启动的选项
		Intent shortCut = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		// 不允许重复创建
		shortCut.putExtra("duplicate", false);
		ApplicationInfo info = context.getApplicationInfo();
		int app_name = info.labelRes;
		int ic_launcher = info.icon;

		if (0 < app_name) {
			shortCut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
					context.getString(app_name));
		}
		if (0 < ic_launcher) {
			Parcelable icon = Intent.ShortcutIconResource.fromContext(context,
					ic_launcher);
			shortCut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		}
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setClassName(context, className);
		shortCut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
		context.sendBroadcast(shortCut);
	}

	/**
	 * isAppOnForeground:检测程序是否在前景. <br/>
	 * 
	 * @author hushuan
	 * @return
	 */
	public static Boolean isAppOnForeground(Context context) {
		// Returns a list of application processes that are running on the
		// device
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null) {
			return false;
		}

		String packageName = context.getPackageName();
		for (RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)) {
				return (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND);
			}
		}

		return false;
	}

	/**
	 * isActivityOnForeground:检测活动是否在前景. <br/>
	 * 
	 * @author hushuan
	 * @param context
	 * @param componentName
	 *            ：the complete component name for this activity
	 * @return
	 */
	public static Boolean isActivityOnForeground(Context context,
			ComponentName componentName) {
		ComponentName componentNameTemp = getTopActivity(context);
		if (null == componentNameTemp) {
			return false;
		}
		return componentNameTemp.equals(componentName);
	}

	/**
	 * getTopActivity:获取活动Activity栈顶类名. <br/>
	 * 
	 * @author hushuan
	 * @param context
	 * @return
	 */
	public static ComponentName getTopActivity(Context context) {
		ComponentName componentName = null;
		ActivityManager manager = (ActivityManager) context
				.getSystemService(Activity.ACTIVITY_SERVICE);
		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
		if (null != runningTaskInfos && runningTaskInfos.size() > 0) {
			componentName = runningTaskInfos.get(0).topActivity;
		}

		return componentName;
	}

	// 取配置文件属性集合
	public static Properties loadConfig(Context context, String file) {
		Properties properties = new Properties();
		try {
			// 通过流文件来进行properties文件读取的,要将文件放入到assets文件夹或者raw文件夹中
			InputStream is = context.getAssets().open(file);
			properties.load(is);
			is.close();
		} catch (Exception e) {
		}
		return properties;
	}

	public static String getProperties(Context context, String file, String key) {
		Properties properties = loadConfig(context, file);
		return properties.getProperty(key, "");
	}

	// 扫描保存的图片资源并添加到图片缩略图数据库
	public static void updateGallery(Context context, String filename)// filename是我们的文件全名，包括后缀哦
	{
		MediaScannerConnection.scanFile(context, new String[] { filename },
				null, new MediaScannerConnection.OnScanCompletedListener() {
					public void onScanCompleted(String path, Uri uri) {
						LogUtils.e("ExternalStorage ,Scanned " + path + ":");
						LogUtils.e("ExternalStorage-> uri=" + uri);
					}
				});
	}

	/**
	 * 获取ip地址
	 * 
	 * @return
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * getProvidersName:获取手机服务商信息. <br/>
	 * 需要加入权限<uses-permission
	 * android:name="android.permission.READ_PHONE_STATE"/>.<br/>
	 * 
	 * @author hushuan
	 * @param context
	 * @return
	 */
	public static String getProvidersName(Context context) {
		TelephonyManager telManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = telManager.getSubscriberId();
		if (TextUtils.isEmpty(imsi)) {
			return "";
		}
		String ProvidersName = "";
		// 返回唯一的用户ID;就是这张卡的编号神马的
		// IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
		if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
			ProvidersName = "中国移动";
		} else if (imsi.startsWith("46001")) {
			ProvidersName = "中国联通";
		} else if (imsi.startsWith("46003")) {
			ProvidersName = "中国电信";
		}
		return ProvidersName;
	}

	/**
	 * //网络连接类型，取值： //0:unknown //2:WIFI //3:cellular network-unknown generation
	 * //4:cellular network-2G //5:cellular network-3G //6:cellular network-4G
	 * 
	 * @return
	 */
	public static int getConnectionType(Context context) {
		NetworkInfo info = null;
		ConnectivityManager conn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conn != null) {
			info = conn.getActiveNetworkInfo();
		}
		if (info == null) {
			return 0;
		}
		int netType = info.getType();
		int netSubtype = info.getSubtype();
		if (netType == ConnectivityManager.TYPE_WIFI) {
			return 2;
		} else if (netType == ConnectivityManager.TYPE_MOBILE) {
			switch (netSubtype) {
			case TelephonyManager.NETWORK_TYPE_GPRS:
			case TelephonyManager.NETWORK_TYPE_EDGE:
			case TelephonyManager.NETWORK_TYPE_CDMA:
			case TelephonyManager.NETWORK_TYPE_1xRTT:
			case TelephonyManager.NETWORK_TYPE_IDEN:
				return 4;
			case TelephonyManager.NETWORK_TYPE_UMTS:
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
			case TelephonyManager.NETWORK_TYPE_HSDPA:
			case TelephonyManager.NETWORK_TYPE_HSUPA:
			case TelephonyManager.NETWORK_TYPE_HSPA:
			case TelephonyManager.NETWORK_TYPE_EVDO_B:
			case TelephonyManager.NETWORK_TYPE_EHRPD:
			case TelephonyManager.NETWORK_TYPE_HSPAP:
				return 5;
			case TelephonyManager.NETWORK_TYPE_LTE:
				return 6;
			default:
				return 3;
			}
		}
		return 0;
	}

}
