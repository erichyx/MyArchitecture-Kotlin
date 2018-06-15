package cn.eric.arch.entity

/**
 * Created by eric on 2017/11/12.
 */
data class JokeEntity(val id: String, val joke: String) {
    private val categories: List<String>? = null

    val category: String
        get() {
            val sb = StringBuilder("category:")
            categories?.forEach { sb.append(it) }
            return sb.toString()
        }
}
