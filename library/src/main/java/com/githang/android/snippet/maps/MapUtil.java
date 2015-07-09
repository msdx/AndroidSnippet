/*
 * Copyright (c) 2015. Xi'an iRain IOT Technology Service CO.,Ltd. All Rights Reserved.
 */

package com.githang.android.snippet.maps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * 地图工具类。
 * @author      黄浩坑 (hanghaohang@parkingwang.com)
 * @version     2015-03-09
 * @since       1.0
 */
public class MapUtil {

    /**
     * 百度经纬度坐标
     */
    public static final String COORD_BD09LL = "bd09ll";
    /**
     * 百度墨卡托坐标
     */
    public static final String COORD_BD09MC = "bd09mc";
    /**
     * 经过国测局加密的坐标，即火星坐标，也是高德腾讯地图用的坐标。
     */
    public static final String COORD_GCJ02 = "gcj02";
    /**
     * gps获取的坐标
     */
    public static final String COORD_WGS84 = "wgs84";

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     */
    public static final boolean isOpenGPS(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 强制帮用户打开GPS
     */
    public static final void openGPS(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 获取所有已安装的地图软件
     */
    public static final List<MapApp> getInstalledMapApps(Context context) {
        List<MapApp> mapApps = new ArrayList<>();

        PackageManager pm = context.getPackageManager();
        MapApp[] mapTypes = MapApp.values();
        for (MapApp map : mapTypes) {
            PackageInfo info;
            try {
                info = pm.getPackageInfo(map.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                info = null;
            }
            if (info != null) {
                mapApps.add(map);
            }
        }
        return mapApps;
    }

    /**
     * 调起高德地图进行导航的intent
     * @param pkgName 调起的APP的包名
     * @param latitude 纬度
     * @param longitude 经度
     * @param name 地点名称
     * @param coordType 经纬坐标系类型
     * @return 返回可以调起高德地图进行导航的intent
     */
    public static Intent getGaodeIntent(String pkgName, double latitude, double longitude, String name, String coordType) {
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
                .append("&poiname=").append(name).append("&style=0");
        gaodeIntent.setData(Uri.parse(sb.toString()));
        gaodeIntent.setPackage("com.autonavi.minimap");
        return gaodeIntent;
    }

    /**
     * 调起百度地图进行导航的intent
     * @param pkgName 调起的APP的包名
     * @param latitude 纬度
     * @param longitude 经度
     * @param name 地点名称
     * @param coordType 经纬坐标系类型
     * @return 返回可以调起百度地图进行导航的intent
     */
    public static Intent getBaiduIntent(String pkgName, double latitude, double longitude, String name, String coordType) {
        StringBuilder sb = new StringBuilder();
        sb.append("intent://map/direction?")
                .append("destination=")
                .append("latlng:").append(latitude).append(",").append(longitude)
                .append("|name:").append(name)
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

}
