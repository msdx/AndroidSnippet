/*
 * Copyright (c) 2015. Xi'an iRain IOT Technology Service CO.,Ltd. All Rights Reserved.
 */
package com.githang.android.snippet.maps;

import android.content.Intent;
import android.net.Uri;

import java.net.URISyntaxException;

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
    GAODE("高德地图", "com.autonavi.minimap") {
        public Intent getIntent(String pkgName, double latitude, double longitude, String pointName,
                                String coordType) {
            final String dev;
            if(MapUtil.COORD_WGS84.equals(coordType)) {
                dev = "1";
            } else if(MapUtil.COORD_GCJ02.equals(coordType)) {
                dev = "0";
            } else {
                throw new IllegalArgumentException("高德地图调用暂仅支持原始坐标及火星坐标");
            }
            Intent gaodeIntent = new Intent(Intent.ACTION_VIEW);
            gaodeIntent.addCategory(Intent.CATEGORY_DEFAULT);
            StringBuilder sb = new StringBuilder();
            sb.append("androidamap://navi?sourceApplication=").append(pkgName)
                    .append("&lat=").append(latitude)
                    .append("&lon=").append(longitude).append("&dev=").append(dev)
                    .append("&poiname=").append(pointName).append("&style=0");
            gaodeIntent.setData(Uri.parse(sb.toString()));
            gaodeIntent.setPackage("com.autonavi.minimap");
            return gaodeIntent;
        }
    },
    BAIDU("百度地图", "com.baidu.BaiduMap"){

        public Intent getIntent(String pkgName, double latitude, double longitude, String pointName,
                                String coordType) {
            StringBuilder sb = new StringBuilder();
            sb.append("intent://map/direction?")
                    .append("destination=")
                    .append("latlng:").append(latitude).append(",").append(longitude)
                    .append("|name:").append(pointName)
                    .append("&mode=driving")
                    .append("&coord_type=").append(coordType)
                    .append("&src=").append(pkgName)
                    .append("#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            try {
                return Intent.parseUri(sb.toString(), 0);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }
    };

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
        throw new AbstractMethodError("Method no implement");
    }
}
