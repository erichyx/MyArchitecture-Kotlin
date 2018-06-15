package cn.eric.basicore.arch.mvp.uicontroller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by eric on 2017/11/13.
 */

abstract class LazyFragment : Fragment() {
    private var mIsPrepared: Boolean = false

    fun lazyLoad() {
        if (userVisibleHint && mIsPrepared) {
            //异步初始化，在初始化后显示正常UI
            loadData()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        mIsPrepared = true
        return rootView
    }

    protected abstract fun loadData()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoad()
        }
    }
}
