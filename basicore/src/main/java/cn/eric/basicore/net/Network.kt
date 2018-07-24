package cn.eric.basicore.net

import cn.eric.basicore.app.Configurator
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by hyx on 2017/2/16.
 */

val okHttpClient: OkHttpClient by lazy {
    //设置缓存目录和大小
    val cacheSize = 256 shl 20 // 256 MB
    val cacheFile = File(Configurator.appContext.cacheDir, "responses")
    val cache = Cache(cacheFile, cacheSize.toLong())

    OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .cache(cache)
            .build()
}

val retrofitClient: Retrofit by lazy {
    val baseUrl = Configurator.baseUrl
    Retrofit.Builder().baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}

fun <T> createService(serviceClass: Class<T>): T {
    return retrofitClient.create(serviceClass)
}
