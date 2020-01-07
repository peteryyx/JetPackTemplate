package com.wili.jetpack.main.utils

import android.graphics.Bitmap

/**
 * bitmap缩放
 */
fun Bitmap.scale(newWidth: Int, newHeight: Int,recycle: Boolean = false): Bitmap{
    val ret = Bitmap.createScaledBitmap(this,newWidth,newHeight,true)
    if (recycle && !this.isRecycled) this.recycle()
    return ret
}