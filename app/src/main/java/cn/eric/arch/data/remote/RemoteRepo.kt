package cn.eric.arch.data.remote


import android.arch.lifecycle.LiveData
import cn.eric.arch.api.ApiService
import cn.eric.arch.data.local.MovieDao
import cn.eric.arch.data.local.MovieInfo
import cn.eric.arch.data.local.MovieSubjectEntity
import cn.eric.arch.entity.JokeResultEntity
import cn.eric.basicore.net.createService
import io.reactivex.Single

/**
 * Created by eric on 2017/11/12.
 */

class RemoteRepo(private val movieDao: MovieDao) {

    val allMovies: LiveData<List<MovieInfo>>
        get() = movieDao.allMovies


    fun getJokes(count: Int): Single<JokeResultEntity> {
        return createService(ApiService::class.java).getJokes(count)
    }

    fun getShowingMovie(city: String): Single<MovieSubjectEntity> {
        return createService(ApiService::class.java).getShowingMovie(city)
    }

    fun insetAllMovies(item: List<MovieInfo>) {
        movieDao.insetAllMovies(item)
    }
}
