package cn.eric.arch.mvvm.bindingadapter


import android.content.Intent
import android.databinding.BindingAdapter
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import cn.eric.arch.data.local.RatingBean
import cn.eric.basicore.image.glide.ImageLoaderManager

/**
 * Created by eric on 2018/5/31
 */

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    ImageLoaderManager.getImageLoader(view.context).loadImage(view.context, url, view)
}

@BindingAdapter("android:rating")
fun rating(ratingBar: RatingBar, ratingBean: RatingBean) {
    ratingBar.rating = ratingBean.average.toFloat() * ratingBar.numStars / ratingBean.max
}

fun jumpUrl(view: View, url: String?) {
    val context = view.context
    val webPage = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webPage)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}