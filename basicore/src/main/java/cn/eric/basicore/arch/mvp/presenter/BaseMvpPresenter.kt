package cn.eric.basicore.arch.mvp.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent

import cn.eric.basicore.arch.mvp.view.BaseMvpView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by eric on 2018/1/6.
 */

abstract class BaseMvpPresenter<V : BaseMvpView> : LifecycleObserver {

    var mvpView: V? = null
        protected set
    protected var disposable = CompositeDisposable()

    fun inject(mView: V) {
        this.mvpView = mView
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory() {
        disposable.clear()
        mvpView = null
    }
}
