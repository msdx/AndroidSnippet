/*
 * Copyright (c) 2015. Xi'an iRain IOT Technology Service CO.,Ltd. All Rights Reserved.
 */
package com.githang.android.snippet.maps;

import android.content.Intent;

/**
 * 地图APP枚举类，表示支持的地图软件。
 *
 * @author 黄浩坑 (hanghaohang@parkingwang.com)
 * @version 2015-03-09
 * @since 1.0
 */
public enum MapApp {
    // 腾讯地图暂只支持WEB/WAP的URI调用。2015/1/14
    // 搜狗官网上没找到Android相关的URI调用API。
    GAODE("高德地图", "com.autonavi.minimap"),
    BAIDU("百度地图", "com.baidu.BaiduMap");

    private String mAppName;
    private String mPackageName;

    MapApp(String appName, String packageName) {
        this.mAppName = appName;
        this.mPackageName = packageName;
    }

    public String getAppName() {
        return mAppName;
    }

    public String getPackageName() {
        return mPackageName;
    }

    @Override
    public String toString() {
        return mAppName;
    }

    public Intent getIntent(String pkgName, double latitude, double longitude, String pointName,
                            String coordType) {
        switch (this) {
            case GAODE:
                return MapUtil.getGaodeIntent(pkgName, latitude, longitude, pointName, coordType);
            case BAIDU:
                return MapUtil.getBaiduIntent(pkgName, latitude, longitude, pointName, coordType);
            default:
                return null;
        }
    }

}
