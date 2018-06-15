package cn.eric.arch.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by eric on 2017/11/12.
 */

data class JokeResultEntity(var type: String?,
                            @SerializedName("value") var jokeList: List<JokeEntity>?)
