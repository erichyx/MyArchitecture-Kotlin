package cn.eric.basicore.arch.mvvm

import android.arch.lifecycle.ViewModel
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes

/**
 * Created by eric on 2018/5/30
 */
interface MvvmControlBehavior<V : ViewDataBinding, out VM : ViewModel> {
    @get:LayoutRes
    val layoutId: Int

    val viewModelId: Int

    val viewModel: VM

    var viewDataBinding: V
}
