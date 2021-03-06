package com.wili.jetpack.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.wili.jetpack.main.model.UserInfo

open class ObservableViewModel(stated: SavedStateHandle) : BaseViewModel(stated) {

    var userData: MutableLiveData<UserInfo> = MutableLiveData()

    fun reset2UserData(){

        val user = UserInfo(listOf("张三","李四","王五").random(),(0..100).random())
        userData.postValue(user)

    }

}