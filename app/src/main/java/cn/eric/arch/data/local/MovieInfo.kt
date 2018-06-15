package cn.eric.arch.data.local

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation


/**
 * Created by eric on 2018/6/3
 */
class MovieInfo {
    @Embedded
    var subject: SubjectsBean? = null

    @Relation(parentColumn = "subjectId", entityColumn = "movieId", entity = CastsBean::class)
    var casts: List<CastsBean>? = null

    @Relation(parentColumn = "subjectId", entityColumn = "movieId", entity = DirectorsBean::class)
    var directors: List<DirectorsBean>? = null

    @Relation(parentColumn = "subjectId", entityColumn = "movieId", entity = MovieGenre::class)
    var genres: List<MovieGenre>? = null

    fun toActorStr(): String {
        var actors = ""
        if (casts != null) {
            val sb = StringBuilder()
            val num = Math.min(3, casts!!.size)
            for (i in 0 until num) {
                sb.append(casts!![i].name)
                if (i < num - 1) {
                    sb.append(" / ")
                }
            }
            actors = sb.toString()
        }

        return actors
    }
}
