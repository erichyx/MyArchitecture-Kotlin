package cn.eric.arch.app

import android.app.Application
import cn.eric.arch.R
import cn.eric.arch.data.local.AppDatabase
import cn.eric.basicore.app.AppExecutors
import cn.eric.basicore.app.Configurator

/**
 * Created by hyx on 2017/11/12.
 */

class BasicApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // 进行基础核心库配置
        Configurator.appContext(this)
                .baseUrl("https://www.github.com/")
                .imagePlaceHolder(R.drawable.ic_stub, R.drawable.ic_error)
                .configure()

        // 每次启动的时候先去删数据
        AppExecutors.diskIO.execute { database.movieDao().deleteAllMovie() }
    }

    companion object {

        val database: AppDatabase
            get() {
                val appContext = Configurator.appContext
                return AppDatabase.instance(appContext)
            }
    }
}
