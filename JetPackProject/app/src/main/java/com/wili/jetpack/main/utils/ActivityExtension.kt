package com.wili.jetpack.main.utils

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * 屏幕截图
 */
fun Activity.screenShot(isDeleteStatusBar: Boolean = true): Bitmap {

    val decorView = this.window.decorView
    //开启中间绘制缓存（有硬件加速，可能会影响性能）
    decorView.isDrawingCacheEnabled = true
    decorView.buildDrawingCache()

    val bmp = decorView.drawingCache
    val dm = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(dm)
    var ret: Bitmap? = null
    ret = if (isDeleteStatusBar) {
        val resources = this.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        val statusBarHeight = resources.getDimensionPixelSize(resourceId)
        Bitmap.createBitmap(
            bmp,
            0,
            statusBarHeight,
            dm.widthPixels,
            dm.heightPixels - statusBarHeight
        )
    } else {
        Bitmap.createBitmap(bmp,0,0,dm.widthPixels,dm.heightPixels)
    }
    decorView.destroyDrawingCache()
    return ret!!

}

/**
 * 当前是否是竖屏
 */
fun Activity.isPortrait(): Boolean{
    return resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}

/**
 * 当前是否是横屏
 */
fun Activity.isLandscape(): Boolean{
    return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

/**
 * 设置竖屏
 */
fun Activity.setPortrait(){
    this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}

/**
 * 设置横屏
 */
fun Activity.setLandscape() {
    this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

/**
 * 设置全屏
 */
fun Activity.setFullScreen() {
    this.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
}

/**
 * 显示软键盘
 */
fun Activity.showKeyboard() {
    val imm: InputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.requestFocus()
    }
    imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
}

/**
 * 隐藏软键盘
 */
fun Activity.hideKeyboard() {
    val imm: InputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
