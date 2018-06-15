package cn.eric.basicore.arch.mvvm.uicontroller

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cn.eric.basicore.arch.mvvm.MvvmControlBehavior

/**
 * Created by eric on 2018/5/26
 */
abstract class BaseFragment<B : ViewDataBinding, out VM : ViewModel> : Fragment(), MvvmControlBehavior<B, VM> {

    protected fun <T : ViewModel> getViewModel(cls: Class<T>): T {
        val t = ViewModelProviders.of(this).get(cls)
        if (LifecycleObserver::class.java.isAssignableFrom(cls)) {
            lifecycle.addObserver(t as LifecycleObserver)
        }
        return t
    }

    override lateinit var viewDataBinding: B

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
