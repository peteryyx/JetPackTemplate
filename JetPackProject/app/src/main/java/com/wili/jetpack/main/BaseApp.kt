package com.wili.jetpack.main

import android.app.Application
import android.content.Context

/**
 * @author EsonYu
 */
class BaseApp : Application() {

    companion object {
        var context: Context? = null
            private set


    }

    override fun onCreate() {
        super.onCreate()
        initContext()
    }

    /**
     * 初始化App全局Context
     */
    private fun initContext() {
        context = applicationContext
    }




}