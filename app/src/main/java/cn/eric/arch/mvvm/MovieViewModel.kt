package cn.eric.arch.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.net.Uri
import android.widget.TextView

import java.util.ArrayList

import cn.eric.arch.app.BasicApp
import cn.eric.arch.data.local.MovieGenre
import cn.eric.arch.data.local.MovieInfo
import cn.eric.arch.data.local.MovieSubjectEntity
import cn.eric.arch.data.remote.RemoteRepo
import cn.eric.basicore.arch.mvvm.fetcher.DataFetcher
import cn.eric.basicore.arch.mvvm.fetcher.Resource
import cn.eric.basicore.arch.mvvm.viewmodel.BaseSimpleViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by eric on 2017/11/12.
 */

class MovieViewModel : BaseSimpleViewModel<MovieSubjectEntity>() {
    private lateinit var observableMovies: MutableLiveData<MovieSubjectEntity>
    private val remoteRepo = RemoteRepo()
    private val database = BasicApp.database

    fun getShowingMovie(city: String, isInit: Boolean): LiveData<Resource<List<MovieInfo>>> {
        return object : DataFetcher<List<MovieInfo>, MovieSubjectEntity>() {

            override fun loadCache(): LiveData<List<MovieInfo>>? {
                return if (isInit) database.movieDao().allMovies else null
            }

            override fun createCall(): LiveData<MovieSubjectEntity> {
                return createRealCall(city)
            }

            override fun transform(source: MovieSubjectEntity): List<MovieInfo> {
                val movieInfos = ArrayList<MovieInfo>()
                val subjects = source.subjects
                subjects?.forEach {
                    val movieInfo = MovieInfo()
                    movieInfo.subject = it
                    movieInfo.genres = MovieGenre.transform(it.subjectId, it.genres)
                    movieInfo.casts = it.casts
                    movieInfo.directors = it.directors
                    movieInfos.add(movieInfo)
                }
                return movieInfos
            }

            override fun saveResult(item: List<MovieInfo>) {
                database.movieDao().insetAllMovies(item)
            }

            override fun onFetchFailInfo(): String {
                return errorMsg
            }
        }.result
    }

    private fun createRealCall(city: String): LiveData<MovieSubjectEntity> {
        // 这里必须每次实例化一个新对象，否则会导致再次调用时数据变化的回调累积
        observableMovies = MutableLiveData()
        remoteRepo.getShowingMovie(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this)

        return observableMovies
    }

    override fun onSuccess(movieSubjectEntity: MovieSubjectEntity) {
        observableMovies.value = movieSubjectEntity
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        observableMovies.value = null
    }
}
