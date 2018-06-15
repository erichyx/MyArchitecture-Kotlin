package cn.eric.basicore.arch.mvvm.viewmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by eric on 2018/5/26
 */
abstract class BaseViewModel<T> : ViewModel(), LifecycleObserver {
    protected var mDisposable = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clear() {
        mDisposable.clear()
    }
}
