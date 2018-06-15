package cn.eric.arch.mvp


import cn.eric.arch.entity.JokeEntity
import cn.eric.basicore.arch.mvp.view.BaseMvpView

/**
 * Created by eric on 2018/1/6.
 */

interface JokeView : BaseMvpView {

    fun showJokes(data: List<JokeEntity>?)
    fun setLoadingIndicator(active: Boolean)
}
