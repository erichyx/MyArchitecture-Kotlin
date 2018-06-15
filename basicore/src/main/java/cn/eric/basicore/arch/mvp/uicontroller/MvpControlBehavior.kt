package cn.eric.basicore.arch.mvp.uicontroller

import android.support.annotation.LayoutRes

import cn.eric.basicore.arch.mvp.presenter.BaseMvpPresenter
import cn.eric.basicore.arch.mvp.view.BaseMvpView

/**
 * Created by eric on 2018/5/30
 */
interface MvpControlBehavior {
    @get:LayoutRes
    val layoutId: Int

    fun initView()

    fun onPrepare()

    fun <V : BaseMvpView, P : BaseMvpPresenter<V>> getPresenter(cls: Class<P>, view: V): P
}
