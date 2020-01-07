package com.wili.jetpack.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wili.jetpack.main.R
import com.wili.jetpack.main.model.UserInfo
import com.wili.jetpack.main.utils.getString

open class ObservableViewModel : ViewModel() {



    var userData: MutableLiveData<UserInfo> = MutableLiveData()

//    fun resetUserData(){
//        val user = UserInfo(listOf("张三","李四","王五").random(),(0..100).random())
//
//        userData.value = user
//    }

    fun reset2UserData(){

        val user = UserInfo(listOf("张三","李四","王五").random(),(0..100).random())
        userData.postValue(user)

    }

}