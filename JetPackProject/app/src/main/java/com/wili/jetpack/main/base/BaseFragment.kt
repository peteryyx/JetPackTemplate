package com.wili.jetpack.main.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 *
 * @param TBinding fragment的layout文件中生成的对应XXBinding
 * @param TModel fragment对应的ViewModel
 * @param layout fragment布局id
 * @param brId layout中ViewModel的variable变量名在BR类对应生成的id
 * @param modelClass viewmodel的class文件
 *
 * @author EsonYu
 */
abstract class BaseFragment<TBinding : ViewDataBinding, TModel : ViewModel>(
    private val layout: Int,
    private val brId: Int,
    modelClass: Class<TModel>
) : Fragment(), BaseView {

    protected lateinit var binding: TBinding

    /**
     * 缓存界面，便于栈内复用
     */
    protected var viewHolder: View? = null

    /**
     * 检查界面是否缓存
     */
    protected var isViewCached = false

    protected val viewModel by lazy { ViewModelProviders.of(this).get(modelClass) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (viewHolder == null) {
            binding = DataBindingUtil.inflate(inflater, layout, container, false)
            //绑定ViewModel的生命周期
            binding.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!isViewCached) {
            binding.setVariable(brId, viewModel)
        }

    }


}
