package com.githang.android.snippet.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 *
 * @author HaohangHuang (msdx.android@qq.com)
 * @since 0.1
 * @version 0.3.2
 */
public class AppManager {
    private static final String LOG_TAG = AppManager.class.getSimpleName();
    private static Stack<Activity> activityStack = new Stack<Activity>();

    /**
     * 添加Activity到堆栈
     * @param activity    要添加进入的Activity
     */
    public static void add(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * 从堆栈中移除指定的Activity
     * @param activity 要移除的Activity
     */
    public static void remove(Activity activity) {
        activityStack.remove(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public static Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public static void finishCurrentActivity() {
        Activity activity = activityStack.pop();
        activity.finish();
    }

    /**
     * 结束指定的Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        ArrayList<Activity> activities = new ArrayList<Activity>();
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                activity.finish();
                activities.add(activity);
            }
        }
        for(Activity activity: activities) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束所有Activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public static void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager manager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            manager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            Log.w(LOG_TAG, e);
        }
    }
    /**
     * 启动界面
     * @param context Context对象
     * @param clazz Activity的Class对象
     * @param flag Intent标志
     */
    public static void start(Context context, Class<? extends Activity> clazz, int flag) {
        Intent intent = new Intent(context, clazz);
        intent.addFlags(flag);
        context.startActivity(intent);
    }
}