package cn.eric.basicore.arch.mvvm.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by eric on 2018/5/31
 */
abstract class BaseItemBindingAdapter<T, B : ViewDataBinding>(diffCallback: DiffUtil.ItemCallback<T>) : ListAdapter<T, BaseItemBindingAdapter.BaseBindingViewHolder>(diffCallback) {

    protected abstract val variableId: Int
    protected abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        val binding = DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), layoutId, parent, false)
        return BaseBindingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<B>(holder.itemView)
        onBeforeBindItem(binding)
        onBindItem(binding, getItem(position))
    }

    // 子类根据需要重写该方法
    protected open fun onBeforeBindItem(binding: B?) {

    }

    private fun onBindItem(binding: B?, item: T) {
        binding?.setVariable(variableId, item)
        binding?.executePendingBindings()
    }

    class BaseBindingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
