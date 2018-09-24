package cn.eric.arch.mvvm.bindingadapter

import android.support.v7.util.DiffUtil
import cn.eric.arch.data.local.MovieInfo

/**
 * Created by eric on 2018/9/23
 */
class MovieInfoDiffCallback : DiffUtil.ItemCallback<MovieInfo>() {
    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.subject?.subjectId == newItem.subject?.subjectId
    }

    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.subject == newItem.subject
    }
}