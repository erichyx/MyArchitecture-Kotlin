package cn.eric.arch.mvvm


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.util.Log


import cn.eric.arch.R
import cn.eric.arch.BR
import cn.eric.arch.data.local.MovieInfo
import cn.eric.arch.databinding.FragmentMvvmBinding
import cn.eric.basicore.arch.mvvm.fetcher.Resource
import cn.eric.basicore.arch.mvvm.uicontroller.BaseFragment
import cn.eric.basicore.arch.mvvm.uicontroller.BottomTabFragment

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : BaseFragment<FragmentMvvmBinding, MovieViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    private var movieAdapter: MovieItemAdapter? = null

    override val layoutId: Int
        get() = R.layout.fragment_mvvm

    override val viewModelId: Int
        get() = BR.viewModel

    override val viewModel by lazy {
        getViewModel(MovieViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.fragment = this
        movieAdapter = MovieItemAdapter()
        viewDataBinding.recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        viewDataBinding.recyclerView.adapter = movieAdapter

        subscribeUi(viewModel, true)
    }

    private fun subscribeUi(movieViewModel: MovieViewModel, isInit: Boolean) {
        movieViewModel.getShowingMovie(CITY, isInit).observe(this, Observer<Resource<List<MovieInfo>>> {
            when (it?.status) {
                Resource.Status.LOADING -> {
                    Log.d(TAG, "数据加载中：${it.data}")
                    viewDataBinding.isRefreshing = true
                    it.data?.let {
                        movieAdapter?.items = it
                    }
                }
                Resource.Status.SUCCESS -> it.data?.let {
                    Log.d(TAG, "数据完成：$it")
                    movieAdapter?.items = it
                }
                else -> {
                    Log.d(TAG, "数据加载失败：${it?.message}")
                }
            }
            viewDataBinding.isRefreshing = false
        })
    }

    override fun onRefresh() {
        subscribeUi(viewModel, false)
    }

    companion object {
        private const val CITY = "厦门"

        private const val TAG = "MovieFragment"

        fun newInstance(sceneId: Int): MovieFragment {
            val args = Bundle()
            args.putString(BottomTabFragment.ARGS_SCENE_ID, sceneId.toString())
            val fragment = MovieFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
