package cn.eric.basicore.arch.mvvm.uicontroller

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import cn.eric.basicore.arch.mvvm.MvvmControlBehavior

/**
 * Created by eric on 2018/5/29
 */
abstract class BaseActivity<B : ViewDataBinding, out VM : ViewModel> : AppCompatActivity(), MvvmControlBehavior<B, VM> {

    protected fun <T : ViewModel> provideViewModel(cls: Class<T>): T {
        val t = ViewModelProviders.of(this).get(cls)
        if (LifecycleObserver::class.java.isAssignableFrom(cls)) {
            lifecycle.addObserver(t as LifecycleObserver)
        }
        return t
    }

    override lateinit var viewDataBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewDataBinding.setVariable(viewModelId, viewModel)
    }
}
