package cn.eric.basicore.arch.observer

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by eric on 2018/1/7.
 */

abstract class BaseSingleObserver<T>() : SingleObserver<T> {
    protected var errorMsg: String? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        errorMsg = e.message
    }
}
