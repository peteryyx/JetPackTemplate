package com.wili.jetpack.main

import android.app.Application

/**
 * @author EsonYu
 */
class BaseApp : Application() {

    companion object {
        var context: Application? = null
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
        context = applicationContext as Application
    }




}