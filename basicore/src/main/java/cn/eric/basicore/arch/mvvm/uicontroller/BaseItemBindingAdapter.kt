package cn.eric.basicore.arch.mvvm.uicontroller

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by eric on 2018/5/31
 */
abstract class BaseItemBindingAdapter<T, in B : ViewDataBinding> : RecyclerView.Adapter<BaseItemBindingAdapter.BaseBindingViewHolder>() {

    private var _items: MutableList<T> = arrayListOf()

    var items: List<T>?
        get() = _items
        set(value) {
            value?.let {
                _items.clear()
                _items.addAll(it)
                notifyDataSetChanged()
            }
        }

    protected abstract val variableId: Int

    override fun getItemCount(): Int {
        return _items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        val binding = DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), getLayoutResId(viewType), parent, false)
        return BaseBindingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<B>(holder.itemView)
        onBeforeBindItem(binding)
        onBindItem(binding, _items[position])
    }

    // 子类根据需要重写该方法
    protected open fun onBeforeBindItem(binding: B?) {

    }

    @LayoutRes
    protected abstract fun getLayoutResId(viewType: Int): Int

    protected fun onBindItem(binding: B?, item: T) {
        binding?.setVariable(variableId, item)
        binding?.executePendingBindings()
    }

    class BaseBindingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
