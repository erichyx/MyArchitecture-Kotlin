package cn.eric.basicore.arch.mvp.uicontroller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cn.eric.basicore.arch.mvp.factory.PresenterFactory
import cn.eric.basicore.arch.mvp.presenter.BaseMvpPresenter
import cn.eric.basicore.arch.mvp.view.BaseMvpView

/**
 * Created by eric on 2018/1/6.
 */

abstract class BaseMvpFragment : Fragment(), MvpControlBehavior {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        onPrepare()
    }

    override fun <V : BaseMvpView, P : BaseMvpPresenter<V>> getPresenter(cls: Class<P>, view: V): P {
        val presenter = PresenterFactory.create(cls, view)
        lifecycle.addObserver(presenter)
        return presenter
    }
}
