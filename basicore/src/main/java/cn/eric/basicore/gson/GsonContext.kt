package cn.eric.basicore.gson

import com.google.gson.GsonBuilder

/**
 * Created by hyx on 2017/6/9.
 */

val gsonSingleton by lazy {
    GsonBuilder()
            .registerTypeAdapter(Int::class.java, IntegerAdapter())
            .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerAdapter())
            .registerTypeAdapter(Long::class.java, LongAdapter())
            .registerTypeAdapter(Long::class.javaPrimitiveType, LongAdapter())
            .registerTypeAdapter(Double::class.java, DoubleAdapter())
            .registerTypeAdapter(Double::class.javaPrimitiveType, DoubleAdapter())
            .create()
}
