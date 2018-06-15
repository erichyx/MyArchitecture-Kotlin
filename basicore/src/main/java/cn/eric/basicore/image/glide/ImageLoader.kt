package cn.eric.basicore.image.glide

import android.content.Context
import android.support.annotation.DrawableRes
import android.widget.ImageView

import java.io.File

/**
 * Created by hyx on 2017/5/15.
 */

interface ImageLoader {
    fun init(context: Context)

    fun loadImage(context: Context, url: String?, view: ImageView)

    fun loadImage(context: Context, file: File, view: ImageView)

    fun loadImage(context: Context, @DrawableRes drawableId: Int, view: ImageView)

    fun loadImage(context: Context, url: String?, view: ImageView, width: Int, height: Int)

    fun loadImageEx(context: Context, url: String?, view: ImageView, @DrawableRes loadingRes: Int, @DrawableRes errorRes: Int)

    fun loadCircleImage(context: Context, url: String?, view: ImageView)

    fun loadCircleImageEx(context: Context, url: String?, view: ImageView, @DrawableRes loadingRes: Int, @DrawableRes errorRes: Int)

    fun loadImageNoMemCache(context: Context, url: String?, view: ImageView)

    fun loadImageNoMemCache(context: Context, url: String?, view: ImageView, @DrawableRes loadingRes: Int, @DrawableRes errorRes: Int)

    fun clearMemory(context: Context)

    fun clearDiskCache(context: Context)

    fun getDiskCacheDir(context: Context): File?
}
