package com.wili.jetpack.main.utils

import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.wili.jetpack.main.BaseApp

/**
 * 获取屏幕的像素密度
 */
var density: Float = 0f
    private set
    get() {
        BaseApp.context?.let {
            return it.resources.displayMetrics.density
        }
        return field
    }

/**
 * 获取资源文件中的颜色值
 */
fun getColor(resourceId: Int): Int {
    return ContextCompat.getColor(BaseApp.context!!, resourceId)
}

/**
 * 获取资源文件中的字符串
 */
fun getString(@StringRes resourceId: Int): String {
    return BaseApp.context!!.getString(resourceId)
}

/**
 * 获取资源文件中的字符串模板
 */
fun getStringTemplate(@StringRes resourceId: Int, vararg value: Any): String {
    return BaseApp.context!!.getString(resourceId, *value)
}

/**
 * dp值转px
 */
fun dp2px(dpValue: Float): Int {
    return (dpValue / density + 0.5f).toInt()
}

/**
 * px值转dp
 */
fun px2dp(pxValue: Float): Int {
    return (pxValue / density + 0.5f).toInt()
}

/**
 * px值转sp
 */
fun px2sp(pxValue: Float): Int {
    return (pxValue / density + 0.5f).toInt()
}
