package cn.eric.basicore.arch.mvp.uicontroller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import cn.eric.basicore.arch.mvp.factory.PresenterFactory
import cn.eric.basicore.arch.mvp.presenter.BaseMvpPresenter
import cn.eric.basicore.arch.mvp.view.BaseMvpView

/**
 * Created by eric on 2018/1/6.
 */

abstract class BaseMvpActivity : AppCompatActivity(), MvpControlBehavior {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId)

        initView()
        onPrepare()
    }

    override fun <V : BaseMvpView, P : BaseMvpPresenter<V>> getPresenter(cls: Class<P>, view: V): P {
        val presenter = PresenterFactory.create(cls, view)
        lifecycle.addObserver(presenter)
        return presenter
    }
}
