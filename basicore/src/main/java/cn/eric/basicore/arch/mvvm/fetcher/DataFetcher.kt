package cn.eric.basicore.arch.mvvm.fetcher

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import android.util.Log

import java.util.concurrent.Executor

import cn.eric.basicore.app.AppExecutors


/**
 * Created by eric on 2018/5/31
 */

abstract class DataFetcher<ResultType, RequestType> {

    val result = MediatorLiveData<Resource<ResultType>>()

    // 从缓存中加载
    @MainThread
    protected abstract fun loadCache(): LiveData<ResultType>?

    // 创建API调用
    @MainThread
    protected abstract fun createCall(): LiveData<RequestType>

    @MainThread
    protected abstract fun transform(source: RequestType): ResultType

    @WorkerThread
    protected abstract fun saveResult(item: ResultType)

    init {
        // 加载中
        result.value = Resource.loading(null)

        val dbSource = this.loadCache()
        if (dbSource != null) {
            result.addSource(dbSource) { data ->
                Log.d(TAG, "获取到缓存数据$data")
                // 此时数据加载完成，移除缓存源监听
                result.removeSource(dbSource)

                if (shouldFetch(data)) {
                    fetchFromNetwork(dbSource)
                } else {
                    // 从缓存数据中获取到有效数据
                    Log.d(TAG, "只显示缓存数据$data")
                    result.value = Resource.success(data)
                }
            }
        } else {
            fetchFromNetwork(null)
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>?) {

        // 重新附加缓存数据源，获取最新的变化值
        if (dbSource != null) {
            result.addSource(dbSource) { newData ->
                Log.d(TAG, "显示缓存数据$newData")
                result.value = Resource.loading(newData)
            }
        }

        val apiResponse = createCall()
        // 增加网络源监听
        result.addSource(apiResponse) { response ->
            // 移除网络源监听
            result.removeSource(apiResponse)
            if (dbSource != null) {
                result.removeSource(dbSource) // 网络数据返回之后移除数据源
            }

            Log.d(TAG, "获取到网络数据$response")
            if (response == null) {
                result.value = Resource.error(onFetchFailInfo(), null)
                return@addSource
            }

            val data = transform(response)
            sExecutor.execute({
                Log.d(TAG, "保存网络数据$data")
                saveResult(data)
                result.postValue(Resource.success(data))
            })
        }
    }

    // 默认都从网络加载，子类可以重写此行为
    @MainThread
    protected fun shouldFetch(data: ResultType?): Boolean {
        return true
    }

    // 获取失败信息
    @MainThread
    abstract fun onFetchFailInfo(): String

    companion object {
        private val TAG = "DataFetcher"

        private val sExecutor = AppExecutors.diskIO
    }
}
