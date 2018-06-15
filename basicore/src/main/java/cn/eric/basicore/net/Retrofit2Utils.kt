package cn.eric.basicore.net

import android.content.Context

import cn.eric.basicore.app.Configurator
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by hyx on 2017/2/16.
 */

object Retrofit2Utils {
    private var sRetrofit: Retrofit? = null

    fun getInstance(context: Context): Retrofit {
        if (sRetrofit == null) {
            synchronized(Retrofit2Utils::class.java) {
                if (sRetrofit == null) {
                    val baseUrl = Configurator.baseUrl
                    sRetrofit = Retrofit.Builder().baseUrl(baseUrl)
                            .client(OkHttp3Utils.getOkHttpClient(context)!!)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                }
            }
        }
        return sRetrofit!!
    }
}
