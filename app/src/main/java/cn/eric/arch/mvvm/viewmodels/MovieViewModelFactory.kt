package cn.eric.arch.mvvm.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import cn.eric.arch.data.remote.RemoteRepo

/**
 * Created by eric on 2018/9/23
 */
class MovieViewModelFactory(private val repository: RemoteRepo) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}