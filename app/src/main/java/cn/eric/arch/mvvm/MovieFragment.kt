package cn.eric.arch.mvvm


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.StructuredPostal.CITY
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
                    if (it.data != null) {
                        movieAdapter?.items = it.data
                    }
                }
                Resource.Status.SUCCESS -> if (it.data != null) {
                    Log.d(TAG, "数据完成：${it.data}")
                    movieAdapter?.items = it.data
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

        fun newInstance(): MovieFragment {
            val args = Bundle()
            val fragment = MovieFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
