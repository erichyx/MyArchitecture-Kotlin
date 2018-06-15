package cn.eric.basicore.net


import android.content.Context

import cn.eric.basicore.app.Configurator

/**
 * Created by hyx on 2017/5/15.
 */

object ServiceGenerator {

    fun <T> createService(serviceClass: Class<T>): T {
        val appContext = Configurator.appContext
        return Retrofit2Utils.getInstance(appContext).create(serviceClass)
    }
}
