package cn.eric.basicore.arch.mvvm.uicontroller

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by eric on 2018/5/26
 */
abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel> : Fragment() {

    protected fun <T : ViewModel> takeViewModel(cls: Class<T>, factory: ViewModelProvider.Factory? = null): T {
        val t = ViewModelProviders.of(this, factory).get(cls)
        if (LifecycleObserver::class.java.isAssignableFrom(cls)) {
            lifecycle.addObserver(t as LifecycleObserver)
        }
        return t
    }

    @get:LayoutRes
    protected abstract val layoutId: Int
    protected abstract val viewModelId: Int
    protected abstract val viewModel: VM

    protected lateinit var viewDataBinding: VDB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.setVariable(viewModelId, viewModel)
        viewDataBinding.executePendingBindings()
    }
}
