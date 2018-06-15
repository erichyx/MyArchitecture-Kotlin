package cn.eric.basicore.arch.mvp.factory

import cn.eric.basicore.arch.mvp.presenter.BaseMvpPresenter
import cn.eric.basicore.arch.mvp.view.BaseMvpView

/**
 * Created by eric on 2018/5/25
 */
object PresenterFactory {

    fun <V : BaseMvpView, P : BaseMvpPresenter<V>> create(cls: Class<P>, view: V): P {
        try {
            val p = cls.newInstance()
            p.inject(view)
            return p
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException(cls.name + " can't be instantiated")
        }

    }
}
