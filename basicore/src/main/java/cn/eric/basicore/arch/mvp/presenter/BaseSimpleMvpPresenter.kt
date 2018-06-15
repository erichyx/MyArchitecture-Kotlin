package cn.eric.basicore.arch.mvp.presenter

import cn.eric.basicore.arch.mvp.view.BaseMvpView
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * Created by eric on 2018/1/6.
 */

abstract class BaseSimpleMvpPresenter<V : BaseMvpView, T> : BaseMvpPresenter<V>(), SingleObserver<T> {

    override fun onSubscribe(d: Disposable) {
        disposable.add(d)
    }
}
