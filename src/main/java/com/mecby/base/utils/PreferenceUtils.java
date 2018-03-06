package com.mecby.base.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils {
	private final static String NAME = "share_data";

	private static SharedPreferences preferences;

	private static SharedPreferences getPreferences(Context context) {
		if (preferences == null) {
			preferences = context.getSharedPreferences(NAME,
					Context.MODE_PRIVATE);
		}
		return preferences;
	}

	public static void remove(Context context, String key){
		SharedPreferences preferences = getPreferences(context);
		Editor editor = preferences.edit();
		editor.remove(key);
		editor.apply();
	}

	public static boolean getBoolean(Context context, String key) {
		return getBoolean(context, key, false);
	}

	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		SharedPreferences preferences = getPreferences(context);
		return preferences.getBoolean(key, defValue);
	}

	public static void setBoolean(Context context, String key, boolean value) {
		SharedPreferences preferences = getPreferences(context);
		Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}

	public static String getString(Context context, String key) {
		return getString(context, key, null);
	}

	public static String getString(Context context, String key, String defValue) {
		SharedPreferences preferences = getPreferences(context);
		return preferences.getString(key, defValue);
	}

	public static void setString(Context context, String key, String value) {
		SharedPreferences preferences = getPreferences(context);
		Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.apply();
	}

	public static int getInt(Context context, String key) {
		return getInt(context, key, -1);
	}

	public static int getInt(Context context, String key, int defValue) {
		SharedPreferences preferences = getPreferences(context);
		return preferences.getInt(key, defValue);
	}

	public static void setInt(Context context, String key, int value) {
		SharedPreferences preferences = getPreferences(context);
		Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.apply();
	}

	public void setObject(Context context,String key, Object ser) {
		try {
			SharedPreferences preferences = getPreferences(context);
				byte[] bytes = ByteUtil.objectToByte(ser);
				bytes = BASE64.encode(bytes);
				setString(context,key, HexUtil.encodeHexStr(bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getObject(Context context,String key) {
		try {
			String hex = getString(context,key, null);
			if (hex == null) return null;
			byte[] bytes = HexUtil.decodeHex(hex.toCharArray());
			bytes = BASE64.decode(bytes);
			Object obj = ByteUtil.byteToObject(bytes);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
