/*
 * Copyright (c) 2015. Xi'an iRain IOT Technology Service CO.,Ltd. All Rights Reserved.
 */

package com.githang.android.snippet.maps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;


/**
 * 地图工具类。
 * @author      msdx.android@qq.com
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
}
