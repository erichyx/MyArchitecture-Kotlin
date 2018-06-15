package cn.eric.basicore.arch.mvp.view

/**
 * Created by eric on 2018/5/25
 */
interface CommMvpView<T> : BaseMvpView {
    fun setLoadingIndicator(active: Boolean)
    fun onSucess(data: T)
    fun onFailure(error: String)
}
