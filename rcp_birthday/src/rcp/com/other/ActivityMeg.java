package rcp.com.other;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class ActivityMeg extends Application {
	// 用于保存activity
	private List<Activity> activityList = new LinkedList<Activity>();
	// 单例
	private static ActivityMeg instance;

	private ActivityMeg() {
	}

	public static ActivityMeg getInstance() {
		if (null == instance) {
			instance = new ActivityMeg();
		}
		return instance;
	}

	/**
	 * 可在activity的onCreate中调用
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 可在activity的onDestroy中调用
	 * 
	 * @param activity
	 */
	public void removeActivity(Activity activity) {
		for (int i = 0; i < activityList.size(); i++) {
			if (activityList.get(i) == activity) {
				activityList.remove(i);
			}
		}
	}

	/**
	 * 在需要退出所有activity的地方调用
	 */
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

	public List<Activity> getActivitiesList() {
		return activityList;
	}

	public void removeAll() {
		for (int i = 0; i < activityList.size(); i++) {
			activityList.remove(i);
		}
	}

}
