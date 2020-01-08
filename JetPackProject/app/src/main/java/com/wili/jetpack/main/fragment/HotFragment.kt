package com.wili.jetpack.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wili.jetpack.main.BR
import com.wili.jetpack.main.R
import com.wili.jetpack.main.base.BaseFragment
import com.wili.jetpack.main.databinding.FragmentHotBinding
import com.wili.jetpack.main.viewmodel.ObservableViewModel

class HotFragment : BaseFragment<FragmentHotBinding, ObservableViewModel>(
    R.layout.fragment_hot,
    BR.HotViewModel,
    ObservableViewModel::class.java
) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (viewHolder == null){
            viewHolder = binding.root
        }
        return viewHolder
    }

    override fun initData(savedInstanceState: Bundle) {
    }

    override fun initObservable() {
    }

}