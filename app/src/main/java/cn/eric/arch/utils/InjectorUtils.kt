package cn.eric.arch.utils

import android.content.Context
import cn.eric.arch.data.local.AppDatabase
import cn.eric.arch.data.remote.RemoteRepo
import cn.eric.arch.mvvm.viewmodels.MovieViewModelFactory

/**
 * Created by eric on 2018/9/23
 */
object InjectorUtils {
    fun providerMovieModelFactory(context: Context): MovieViewModelFactory {
        return MovieViewModelFactory(RemoteRepo(AppDatabase.instance(context).movieDao()))
    }
}