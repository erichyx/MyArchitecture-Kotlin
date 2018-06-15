package cn.eric.basicore.image.glide


import android.content.Context

/**
 * Created by hyx on 2017/5/15.
 */

object ImageLoaderManager {
    @Volatile
    private var sInstance: ImageLoader? = null
    private var sFactory: ImageFactory = GlideFactory()

    fun getImageLoader(context: Context): ImageLoader {
        if (sInstance == null) {
            synchronized(this) {
                if (sInstance == null) {
                    sInstance = sFactory.create(context)
                }
            }
        }
        return sInstance!!
    }

    interface ImageFactory {
        fun create(context: Context): ImageLoader
    }

    fun setFactory(factory: ImageFactory?) {
        if (factory == null) {
            throw NullPointerException("ImageFactory factory == null")
        }

        synchronized(this) {
            sFactory = factory
            sInstance = null
        }
    }

    private class GlideFactory : ImageFactory {
        override fun create(context: Context): ImageLoader {
            return GlideImageLoader(context)
        }
    }
}
