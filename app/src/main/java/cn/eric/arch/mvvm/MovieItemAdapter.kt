package cn.eric.arch.mvvm


import cn.eric.arch.R
import cn.eric.arch.BR
import cn.eric.arch.data.local.MovieInfo
import cn.eric.arch.databinding.MovieItemBinding
import cn.eric.basicore.arch.mvvm.uicontroller.BaseItemBindingAdapter

/**
 * Created by eric on 2018/5/31
 */
class MovieItemAdapter : BaseItemBindingAdapter<MovieInfo, MovieItemBinding>() {

    override val variableId: Int
        get() = BR.movieInfo

    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.movie_recycle_item
    }

    override fun onBeforeBindItem(binding: MovieItemBinding?) {
        // 这里可以做一些额外的数据绑定操作（可选）
    }
}
