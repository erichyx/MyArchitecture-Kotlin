package cn.eric.arch.mvp

import cn.eric.arch.data.remote.RemoteRepo
import cn.eric.arch.entity.JokeResultEntity
import cn.eric.basicore.arch.mvp.presenter.BaseSimpleMvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by eric on 2018/1/7.
 */

class JokePresenter(private val jokeRepo: RemoteRepo) : BaseSimpleMvpPresenter<JokeView, JokeResultEntity>() {

    fun fetchJokes(count: Int) {
        mvpView?.setLoadingIndicator(true)
        jokeRepo.getJokes(count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)
    }

    override fun onSuccess(jokeResultEntity: JokeResultEntity) {
        mvpView?.setLoadingIndicator(false)
        mvpView?.showJokes(jokeResultEntity.jokeList)

    }

    override fun onError(e: Throwable) {
        mvpView?.setLoadingIndicator(false)
    }
}
