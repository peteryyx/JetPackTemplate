package com.wili.jetpack.main.utils

import android.app.Application
import android.widget.Toast
import androidx.annotation.StringRes
import com.hjq.toast.ToastUtils
import com.wili.jetpack.main.BaseApp

/**
 * 是否已经初始化Toast了
 */
private var isInitToast: Boolean = false

/**
 * 获取当前的Toast对象
 */
var currentToast: Toast? = ToastUtils.getToast()

/**
 * 延迟初始化Toast
 */
private fun initToast(block: () -> Unit) {
    BaseApp.context?.let {
        if (isInitToast) {
            block()
        } else {
            ToastUtils.init(BaseApp.context as Application)
            block()
        }
    }
}

/**
 * 设置Toast的样式
 *
 * @param layoutId 布局文件资源id
 * @param gravity 显示toast位置
 * @param xOffset 相对x坐标偏移
 * @param yOffset 相对y坐标偏移
 */
fun setToastStyle(layoutId: Int? = null, gravity: Int? = null, xOffset: Int = 0, yOffset: Int = 0) {
    layoutId?.let {
        ToastUtils.setView(layoutId)
    }
    gravity?.let {
        ToastUtils.setGravity(gravity,xOffset,yOffset)
    }
}

/**
 * 显示一个Toast
 *
 * @param resourceId 资源id
 */
fun showToast(@StringRes resourceId: Int) {
    initToast {
        ToastUtils.show(resourceId)
    }
}

/**
 * 显示一个Toast
 *
 * @param text 文本
 */
fun showToast(text: String) {
    initToast {
        ToastUtils.show(text)
    }
}

/**
 * 显示一个Toast
 *
 * @param obj 任意对象
 */
fun showToast(obj: Any) {
    initToast {
        ToastUtils.show(obj)
    }
}
