package cn.eric.basicore.net

import android.content.Context

import java.io.File
import java.util.concurrent.TimeUnit

import okhttp3.Cache
import okhttp3.OkHttpClient

/**
 * Created by hyx on 2017/2/16.
 */

class OkHttp3Utils {

    companion object {
        @Volatile
        private var sClient: OkHttpClient? = null

        /**
         * 获取okHttpClient的单实例
         *
         * @param context 上下文对象
         * @return OkHttpClient实例
         */
        fun getOkHttpClient(context: Context): OkHttpClient? {
            if (sClient == null) {
                synchronized(this) {
                    if (sClient == null) {
                        //设置缓存目录和大小
                        val cacheSize = 256 shl 20 // 256 MB
                        val cacheFile = File(context.cacheDir, "responses")
                        val cache = Cache(cacheFile, cacheSize.toLong())

                        val builder = OkHttpClient.Builder()
                                .connectTimeout(15, TimeUnit.SECONDS)
                                .readTimeout(20, TimeUnit.SECONDS)
                                .writeTimeout(20, TimeUnit.SECONDS)
                                .cache(cache)

                        sClient = builder.build()
                    }
                }
            }
            return sClient
        }
    }
}
