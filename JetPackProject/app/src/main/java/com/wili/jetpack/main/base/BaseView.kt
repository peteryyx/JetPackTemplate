package com.wili.jetpack.main.base

import android.os.Bundle

interface BaseView {


    /**
     * 初始化ViewModel数据
     */
    fun initData(savedInstanceState: Bundle)

    /**
     * 初始化观察者
     */
    fun initObservable()


}