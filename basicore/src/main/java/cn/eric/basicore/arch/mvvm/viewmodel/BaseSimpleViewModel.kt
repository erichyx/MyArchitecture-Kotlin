package cn.eric.basicore.arch.mvvm.viewmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by eric on 2018/5/26
 */
abstract class BaseSimpleViewModel<T> : ViewModel(), LifecycleObserver, SingleObserver<T> {
    private val mDisposable = CompositeDisposable()
    protected var errorMsg: String = ""

    override fun onSubscribe(d: Disposable) {
        mDisposable.add(d)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clear() {
        mDisposable.clear()
    }

    override fun onError(e: Throwable) {
        errorMsg = e.message ?: "unknown error"
    }
}
