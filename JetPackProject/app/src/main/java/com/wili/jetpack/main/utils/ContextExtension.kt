package com.wili.jetpack.main.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.telephony.TelephonyManager
import android.view.WindowManager

/**
 * 获取屏幕宽度
 */
fun Context.getScreenWidth(): Int {
    val wm: WindowManager = this.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        ?: return resources.displayMetrics.widthPixels

    val point = Point()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        wm.defaultDisplay.getRealSize(point)
    } else {
        wm.defaultDisplay.getSize(point)
    }
    return point.x
}

/**
 * 获取屏幕高度
 */
fun Context.getScreenHeight(): Int {
    val wm: WindowManager = this.getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        ?: return resources.displayMetrics.heightPixels
    val point = Point()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        wm.defaultDisplay.getRealSize(point)
    } else {
        wm.defaultDisplay.getSize(point)
    }
    return point.y
}

/**
 * 打开网络设置
 *
 * @param isNeedResult 是否需要返回值，如果需要则传入的必须是Activity
 */
fun Context.openNetworkSettings(isNeedResult: Boolean = false) {
    if (isNeedResult) {
        (this as? Activity)?.let {
            startActivityForResult(
            Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK),0)
        }
    } else {
        startActivity(
            Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}

/**
 * 判断网络是否连接
 */
@SuppressLint("MissingPermission")
fun Context.isConnectedNetwork(): Boolean {
    val connect = this.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connect?.let {
        return it.activeNetworkInfo.isConnected
    }
    return false
}

/**
 * 判断wifi是否打开
 */
@SuppressLint("MissingPermission")
fun Context.isWifiEnabled(): Boolean {
    val connect = this.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    val mgrTel = this.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager
    connect?.activeNetworkInfo?.let {
        return it.state == NetworkInfo.State.CONNECTED || mgrTel?.networkType == TelephonyManager.NETWORK_TYPE_UMTS
    }
    return false
}

/**
 * 判断Gps是否打开
 */
fun Context.isGpsEnabled(): Boolean {
    val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as? LocationManager

    locationManager?.let {
        val accessibleProviders = it.getProviders(true)
        return accessibleProviders != null && accessibleProviders.size > 0
    }
    return false
}
