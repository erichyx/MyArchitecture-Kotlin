package cn.eric.arch.mvp

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager


import cn.eric.arch.R
import cn.eric.arch.entity.JokeEntity
import cn.eric.basicore.arch.mvvm.uicontroller.BottomTabFragment
import cn.eric.basicore.arch.mvp.uicontroller.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_mvp.*

/**
 * Created by eric on 2018/1/7.
 */
class MvpFragment : BaseMvpFragment(), JokeView, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var jokeAdapter: JokeAdapter
    private lateinit var jokePresenter: JokePresenter

    override val layoutId: Int
        get() = R.layout.fragment_mvp

    override fun initView() {
        jokeAdapter = JokeAdapter()
        recycler_view.adapter = jokeAdapter
        recycler_view.layoutManager = LinearLayoutManager(activity)
        swipe_refresh_layout.setOnRefreshListener(this)
    }

    override fun onPrepare() {
        jokePresenter = getPresenter(JokePresenter::class.java, this)
        onRefresh()
    }

    override fun onRefresh() {
        jokePresenter.fetchJokes(20)
    }

    override fun setLoadingIndicator(active: Boolean) {
        swipe_refresh_layout.isRefreshing = active
    }

    override fun showJokes(data: List<JokeEntity>?) {
        jokeAdapter.list = data
    }

    companion object {
        fun newInstance(sceneId: Int): MvpFragment {
            val args = Bundle()
            args.putString(BottomTabFragment.ARGS_SCENE_ID, sceneId.toString())
            val fragment = MvpFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
