/**
 * 
 */
package com.example.yixin.util;

import com.example.yixin.config.Constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author kince
 *
 */
public class SharedPreferencesUtil {

	public static boolean readIsFirstUse(Context context){
		SharedPreferences preferences=context.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE );
		boolean isUse=preferences.getBoolean(Constants.IS_USE, false);
		return isUse;
	}
	
	public static void writeIsFirstUse(Context context){
		SharedPreferences preferences=context.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE );
		Editor editor=preferences.edit();
		editor.putBoolean(Constants.IS_USE, true);
		editor.commit();
	}
	
	
}
