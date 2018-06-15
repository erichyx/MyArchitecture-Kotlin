package cn.eric.arch.data.remote


import cn.eric.arch.api.ApiService
import cn.eric.arch.data.local.MovieSubjectEntity
import cn.eric.arch.entity.JokeResultEntity
import cn.eric.basicore.net.ServiceGenerator
import io.reactivex.Single

/**
 * Created by eric on 2017/11/12.
 */

class RemoteRepo {
    fun getJokes(count: Int): Single<JokeResultEntity> {
        return ServiceGenerator.createService(ApiService::class.java).getJokes(count)
    }

    fun getShowingMovie(city: String): Single<MovieSubjectEntity> {
        return ServiceGenerator.createService(ApiService::class.java).getShowingMovie(city)
    }
}
