package com.githang.android.snippet.content;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;

/**
 * 可启动的intent。
 *
 * @author msdx.android@qq.com
 * @version 2016-02-04
 * @since 2016-02-04
 */
public class StartIntent extends Intent {
    private Context mCaller;

    public StartIntent() {
    }

    public StartIntent(Intent o) {
        super(o);
    }

    public StartIntent(String action) {
        super(action);
    }

    public StartIntent(String action, Uri uri) {
        super(action, uri);
    }

    public StartIntent(Context packageContext, Class<?> cls) {
        super(packageContext, cls);
        mCaller = packageContext;
    }

    public StartIntent(String action, Uri uri, Context packageContext, Class<?> cls) {
        super(action, uri, packageContext, cls);
    }

    @Override
    public Intent setClass(Context packageContext, Class<?> cls) {
        mCaller = packageContext;
        return super.setClass(packageContext, cls);
    }

    @Override
    public Intent setClassName(Context packageContext, String className) {
        mCaller = packageContext;
        return super.setClassName(packageContext, className);
    }

    /**
     * 默认以packageContext作为调用者，启动一个Activity。
     */
    public void startActivity() {
        mCaller.startActivity(this);
    }

    /**
     * 默认以packageContext作为调用者，启动一个Service。
     */
    public void startService() {
        mCaller.startService(this);
    }

    public void writeToParcel(Parcel dest, int parcelableFlags) {
        super.writeToParcel(dest, parcelableFlags);
    }

    protected StartIntent(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
    }

    public static final Creator<StartIntent> CREATOR
            = new Creator<StartIntent>() {
        public StartIntent createFromParcel(Parcel source) {
            return new StartIntent(source);
        }

        public StartIntent[] newArray(int size) {
            return new StartIntent[size];
        }
    };

    public static StartIntent with(Context context, Class cls) {
        return new StartIntent(context, cls);
    }
}
