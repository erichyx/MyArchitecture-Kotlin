package cn.eric.basicore.app

import android.os.Handler
import android.os.Looper

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by eric on 2018/5/31
 */

class AppExecutors private constructor() {

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable?) {
            mainThreadHandler.post(command)
        }
    }

    companion object {

        val diskIO: Executor by lazy {
            Executors.newSingleThreadExecutor()
        }

        val networkIO: Executor by lazy {
            Executors.newFixedThreadPool(3)
        }

        val mainThread: Executor by lazy {
            MainThreadExecutor()
        }
    }
}
