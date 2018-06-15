package cn.eric.arch.mvvm.bindingadapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView


import cn.eric.arch.data.local.RatingBean
import cn.eric.basicore.image.glide.ImageLoaderManager

/**
 * Created by eric on 2018/5/31
 */
object AppBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        ImageLoaderManager.getImageLoader(view.context).loadImage(view.context, url, view)
    }

    @JvmStatic
    @BindingAdapter("android:rating")
    fun rating(ratingBar: RatingBar, ratingBean: RatingBean) {
        ratingBar.rating = ratingBean.average.toFloat() * ratingBar.numStars / ratingBean.max
    }

    @JvmStatic
    @BindingAdapter("jumpUrl")
    fun jumpUrl(view: TextView, url: String?) {
        /*Context context = view.getContext();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }*/
    }
}
