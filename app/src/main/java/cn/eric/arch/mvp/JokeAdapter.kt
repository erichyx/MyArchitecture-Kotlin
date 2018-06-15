package cn.eric.arch.mvp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.eric.arch.R
import cn.eric.arch.entity.JokeEntity


/**
 * Created by eric on 2017/11/12.
 */

class JokeAdapter : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {
    var list: List<JokeEntity>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.joke_item, parent, false)
        return JokeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val entity = list!![position]
        holder.mTvId.text = entity.id
        holder.mTvCategory.text = entity.category
        holder.mTvJoke.text = entity.joke
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTvId: TextView = itemView.findViewById(R.id.tv_id)
        val mTvCategory: TextView = itemView.findViewById(R.id.category)
        val mTvJoke: TextView = itemView.findViewById(R.id.joke)
    }
}
