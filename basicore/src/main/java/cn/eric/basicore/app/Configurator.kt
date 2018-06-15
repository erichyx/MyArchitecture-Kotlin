package cn.eric.basicore.app

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.util.ArrayMap
import com.bumptech.glide.request.RequestOptions

/**
 * Created by eric on 2018/6/11
 */
object Configurator {
    private var isConfiged = false
    private val configs = ArrayMap<ConfigKey, Any>()

    private enum class ConfigKey {
        BASE_URL, APP_CONTEXT, IMG_REQUEST_OPTIONS
    }

    var requestOptions: RequestOptions
        get() {
            var requestOptions: RequestOptions? = configs[ConfigKey.IMG_REQUEST_OPTIONS] as RequestOptions?
            if (requestOptions == null) {
                requestOptions = RequestOptions()
            }

            return requestOptions
        }
        private set(value) {
            configs[ConfigKey.IMG_REQUEST_OPTIONS] = value
        }

    val baseUrl: String
        get() = getConfig(ConfigKey.BASE_URL) as String

    val appContext: Context
        get() = getConfig(ConfigKey.APP_CONTEXT) as Context

    fun appContext(context: Context): Configurator {
        setConfig(ConfigKey.APP_CONTEXT, context)
        return this
    }

    fun baseUrl(baseUrl: String): Configurator {
        setConfig(ConfigKey.BASE_URL, baseUrl)
        return this
    }

    fun imagePlaceHolder(@DrawableRes loadingRes: Int, @DrawableRes errorRes: Int): Configurator {
        requestOptions = requestOptions.placeholder(loadingRes).error(errorRes)
        return this
    }

    fun configure() {
        isConfiged = true
    }

    private fun checkConfig() {
        if (!isConfiged) {
            throw RuntimeException("Configuration is not ready, please call configure().")
        }
    }

    private fun setConfig(key: ConfigKey, value: Any) {
        configs[key] = value
    }

    private fun getConfig(key: ConfigKey): Any {
        checkConfig()
        return configs[key] ?: throw NullPointerException(key.toString() + " IS NULL")
    }
}