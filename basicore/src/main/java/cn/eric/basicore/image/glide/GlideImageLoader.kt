package cn.eric.basicore.image.glide

import android.content.Context
import android.support.annotation.DrawableRes
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import java.io.File

import cn.eric.basicore.app.Configurator


/**
 * Created by hyx on 2017/5/27.
 */

class GlideImageLoader(context: Context) : ImageLoader {

    private val mOptions: RequestOptions = Configurator.requestOptions

    init {
        init(context)
    }

    override fun init(context: Context) {}

    override fun loadImage(context: Context, url: String?, view: ImageView) {
        Glide.with(context).load(url).apply(mOptions).into(view)
    }

    override fun loadImage(context: Context, file: File, view: ImageView) {
        Glide.with(context).load(file).apply(mOptions).into(view)
    }

    override fun loadImage(context: Context, @DrawableRes drawableId: Int, view: ImageView) {
        Glide.with(context).load(drawableId).apply(mOptions).into(view)
    }

    override fun loadImage(context: Context, url: String?, view: ImageView, width: Int, height: Int) {
        val requestOptions = mOptions.override(width, height)
        Glide.with(context).load(url).apply(requestOptions).into(view)
    }

    override fun loadImageEx(context: Context, url: String?, view: ImageView,
                             @DrawableRes loadingRes: Int, @DrawableRes errorRes: Int) {
        val requestOptions = mOptions.placeholder(loadingRes).error(errorRes)
        Glide.with(context).load(url).apply(requestOptions).into(view)
    }

    override fun loadCircleImage(context: Context, url: String?, view: ImageView) {
        val requestOptions = mOptions.circleCrop()
        Glide.with(context).load(url).apply(requestOptions).into(view)
    }

    override fun loadCircleImageEx(context: Context, url: String?, view: ImageView,
                                   @DrawableRes loadingRes: Int, @DrawableRes errorRes: Int) {
        val requestOptions = mOptions.placeholder(loadingRes).error(errorRes).circleCrop()
        Glide.with(context).load(url).apply(requestOptions).into(view)
    }

    override fun loadImageNoMemCache(context: Context, url: String?, view: ImageView) {
        val requestOptions = mOptions.skipMemoryCache(true)
        Glide.with(context).load(url).apply(requestOptions).into(view)
    }

    override fun loadImageNoMemCache(context: Context, url: String?, view: ImageView,
                                     @DrawableRes loadingRes: Int, @DrawableRes errorRes: Int) {
        val requestOptions = mOptions.placeholder(loadingRes).error(errorRes)
                .skipMemoryCache(true)
        Glide.with(context).load(url).apply(requestOptions).into(view)
    }

    override fun clearMemory(context: Context) {
        Glide.get(context).clearMemory()
    }

    override fun clearDiskCache(context: Context) {
        // 清理磁盘缓存，需要在子线程中执行
        Glide.get(context).clearDiskCache()
    }

    override fun getDiskCacheDir(context: Context): File? {
        return Glide.getPhotoCacheDir(context)
    }
}
