/**
 * 
 * @author heqing1
 * @version 2012-10-22
 */
package com.core.android.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtil {
	public static final String DEFAULT_SHARED_PREF_NAME = "DefaultSharedPrefName";
	private static SharedPrefUtil instance;

	private SharedPrefUtil() {

	}

	public static synchronized SharedPrefUtil getInstance() {
		if (instance == null) {
			instance = new SharedPrefUtil();
		}
		return instance;
	}

	/**
	 * 从指定fileName的sharedPref中读取key对应的字符串值
	 * 
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @return string
	 */
	public String getString(Context context, String fileName, String key,
			String defaultValue) {
		SharedPreferences shareddata = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return shareddata.getString(key, defaultValue);
	}

	/**
	 * key-value键值对写入fileName指定的sharedPref中
	 * 
	 * @param fileName
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putString(Context context, String fileName, String key,
			String value) {
		SharedPreferences.Editor dataEditor = context.getSharedPreferences(
				fileName, Context.MODE_PRIVATE).edit();
		dataEditor.putString(key, value);
		return dataEditor.commit();
	}

	/**
	 * 从指定fileName的sharedPref中读取key对应的int值
	 * 
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public int getInt(Context context, String fileName, String key,
			int defaultValue) {
		SharedPreferences sharedata = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sharedata.getInt(key, defaultValue);
	}

	/**
	 * key-value键值对写入fileName指定的sharedPref中
	 * 
	 * @param fileName
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putInt(Context context, String fileName, String key,
			int value) {
		SharedPreferences.Editor dataEditor = context.getSharedPreferences(
				fileName, Context.MODE_PRIVATE).edit();
		dataEditor.putInt(key, value);
		return dataEditor.commit();
	}

	/**
	 * 从指定fileName的sharedPref中读取key对应的boolean值
	 * 
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public boolean getBoolean(Context context, String fileName, String key,
			boolean defaultValue) {
		SharedPreferences sharedata = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sharedata.getBoolean(key, defaultValue);
	}

	/**
	 * key-value键值对写入fileName指定的sharedPref中
	 * 
	 * @param fileName
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putBoolean(Context context, String fileName, String key,
			Boolean value) {
		SharedPreferences.Editor dataEditor = context.getSharedPreferences(
				fileName, Context.MODE_PRIVATE).edit();
		dataEditor.putBoolean(key, value);
		return dataEditor.commit();
	}

	/**
	 * 从指定fileName的sharedPref中读取key对应的long值
	 * 
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public long getLong(Context context, String fileName, String key,
			long defaultValue) {
		SharedPreferences sharedata = context.getSharedPreferences(fileName,
				Context.MODE_PRIVATE);
		return sharedata.getLong(key, defaultValue);
	}

	/**
	 * key-value键值对写入fileName指定的sharedPref中
	 * 
	 * @param fileName
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putLong(Context context, String fileName, String key,
			long value) {
		SharedPreferences.Editor dataEditor = context.getSharedPreferences(
				fileName, Context.MODE_PRIVATE).edit();
		dataEditor.putLong(key, value);
		return dataEditor.commit();
	}

	/**
	 * 从默认的sharedPref中读取key对应的string值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getString(Context context, String key, String defaultValue) {
		return getString(context, DEFAULT_SHARED_PREF_NAME, key, defaultValue);
	}

	/**
	 * key-value键值对写入fileName指定的sharedPref中
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putString(Context context, String key, String value) {
		return putString(context, DEFAULT_SHARED_PREF_NAME, key, value);
	}

	/**
	 * 从默认的sharedPref中读取key对应的int值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public int getInt(Context context, String key, int defaultValue) {
		return getInt(context, DEFAULT_SHARED_PREF_NAME, key, defaultValue);
	}

	/**
	 * key-value键值对写入默认sharedPref中
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putInt(Context context, String key, int value) {
		return putInt(context, DEFAULT_SHARED_PREF_NAME, key, value);
	}

	/**
	 * 从默认的sharedPref中读取key对应的boolean值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public boolean getBoolean(Context context, String key, boolean defaultValue) {
		return getBoolean(context, DEFAULT_SHARED_PREF_NAME, key, defaultValue);
	}

	/**
	 * key-value键值对写入默认sharedPref中
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putBoolean(Context context, String key, Boolean value) {
		return putBoolean(context, DEFAULT_SHARED_PREF_NAME, key, value);
	}

	/**
	 * 从默认的sharedPref中读取key对应的long值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public long getLong(Context context, String key, long defaultValue) {
		return getLong(context, DEFAULT_SHARED_PREF_NAME, key, defaultValue);
	}

	/**
	 * key-value键值对写入默认sharedPref中
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean putLong(Context context, String key, long value) {
		return putLong(context, DEFAULT_SHARED_PREF_NAME, key, value);
	}
	
	
	/**=======================建议使用下面方法 ，效率会更加高===================**/
	
	
	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public void put(Context context, String key, Object object) {

		SharedPreferences sp = context.getSharedPreferences(DEFAULT_SHARED_PREF_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if (object instanceof String) {
			editor.putString(key, (String) object);
		} else if (object instanceof Integer) {
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean) {
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float) {
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long) {
			editor.putLong(key, (Long) object);
		} else {
			editor.putString(key, object.toString());
		}

		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * 
	 * @param context
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public Object get(Context context, String key, Object defaultObject) {
		SharedPreferences sp = context.getSharedPreferences(DEFAULT_SHARED_PREF_NAME,
				Context.MODE_PRIVATE);

		if (defaultObject instanceof String) {
			return sp.getString(key, (String) defaultObject);
		} else if (defaultObject instanceof Integer) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if (defaultObject instanceof Boolean) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if (defaultObject instanceof Float) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if (defaultObject instanceof Long) {
			return sp.getLong(key, (Long) defaultObject);
		}

		return null;
	}

	/**
	 * 移除某个key值已经对应的值
	 * 
	 * @param context
	 * @param key
	 */
	public void remove(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(DEFAULT_SHARED_PREF_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(key);
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 清除所有数据
	 * 
	 * @param context
	 */
	public void clear(Context context) {
		SharedPreferences sp = context.getSharedPreferences(DEFAULT_SHARED_PREF_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 查询某个key是否已经存在
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public boolean contains(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(DEFAULT_SHARED_PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.contains(key);
	}

	/**
	 * 返回所有的键值对
	 * 
	 * @param context
	 * @return
	 */
	public Map<String, ?> getAll(Context context) {
		SharedPreferences sp = context.getSharedPreferences(DEFAULT_SHARED_PREF_NAME,
				Context.MODE_PRIVATE);
		return sp.getAll();
	}
	
	
	/**
	 * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
	 * 
	 * 
	 */
	private static class SharedPreferencesCompat {
		private static final Method sApplyMethod = findApplyMethod();

		/**
		 * 反射查找apply的方法
		 * 
		 * @return
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static Method findApplyMethod() {
			try {
				Class clz = SharedPreferences.Editor.class;
				return clz.getMethod("apply");
			} catch (NoSuchMethodException e) {
			}

			return null;
		}

		/**
		 * 如果找到则使用apply执行，否则使用commit
		 * 
		 * @param editor
		 */
		public static void apply(SharedPreferences.Editor editor) {
			try {
				if (sApplyMethod != null) {
					sApplyMethod.invoke(editor);
					return;
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
			editor.commit();
		}
	}

}
