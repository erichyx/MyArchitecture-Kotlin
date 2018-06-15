package cn.eric.arch.api


import cn.eric.arch.data.local.MovieSubjectEntity
import cn.eric.arch.entity.JokeResultEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by eric on 2017/11/12.
 */

interface ApiService {
    @GET("http://api.icndb.com/jokes/random/{count}")
    fun getJokes(@Path("count") count: Int): Single<JokeResultEntity>


    @GET("https://api.douban.com/v2/movie/in_theaters")
    fun getShowingMovie(@Query("city") city: String): Single<MovieSubjectEntity>
}
