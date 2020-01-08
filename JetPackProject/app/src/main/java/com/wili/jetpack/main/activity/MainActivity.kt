package com.wili.jetpack.main.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.qmuiteam.qmui.arch.QMUIActivity
import com.wili.jetpack.main.R
import com.wili.jetpack.main.databinding.ActivityMainBinding
import com.wili.jetpack.main.utils.showToast
import com.wili.jetpack.main.viewmodel.ObservableViewModel

class MainActivity : QMUIActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(ObservableViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity,
            R.layout.activity_main
        ).also {
            it.viewmodel = viewModel
//            it.button2.text = "不同方式更新"
//            it.button.text = "随机信息"
            it.lifecycleOwner = this@MainActivity
        }

        viewModel.userData.observe(this, Observer {
//            binding.tipTextTemplate.text = getStringTemplate(R.string.string_format,it.name,it.age)
            showToast(it.name)
        })


    }
}
