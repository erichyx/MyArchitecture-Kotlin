package cn.eric.arch.data.local

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created by eric on 2018/5/31
 */
/**
 * count : 20
 * start : 0
 * total : 15
 * subjects : [{"rating":{"max":10,"average":7.2,"stars":"40","min":0},"genres":["喜剧","奇幻"],"title":"超时空同居","casts":[{"alt":"https://movie.douban.com/celebrity/1312940/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg"},"name":"雷佳音","id":"1312940"},{"alt":"https://movie.douban.com/celebrity/1275756/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg"},"name":"佟丽娅","id":"1275756"},{"alt":"https://movie.douban.com/celebrity/1325751/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg"},"name":"张衣","id":"1325751"}],"collectCount":80453,"originalTitle":"超时空同居","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1331887/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg"},"name":"苏伦","id":"1331887"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg"},"alt":"https://movie.douban.com/subject/27133303/","id":"27133303"}]
 * title : 正在上映的电影-厦门
 */
data class MovieSubjectEntity(var count: Int = 0,
                              var start: Int = 0,
                              var total: Int = 0,
                              var title: String? = null,
                              var subjects: List<SubjectsBean>? = null)

/**
 * rating : {"max":10,"average":7.2,"stars":"40","min":0}
 * genres : ["喜剧","奇幻"]
 * title : 超时空同居
 * casts : [{"alt":"https://movie.douban.com/celebrity/1312940/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg"},"name":"雷佳音","id":"1312940"},{"alt":"https://movie.douban.com/celebrity/1275756/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1367210981.95.jpg"},"name":"佟丽娅","id":"1275756"},{"alt":"https://movie.douban.com/celebrity/1325751/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1499435823.67.jpg"},"name":"张衣","id":"1325751"}]
 * collectCount : 80453
 * originalTitle : 超时空同居
 * subtype : movie
 * directors : [{"alt":"https://movie.douban.com/celebrity/1331887/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg"},"name":"苏伦","id":"1331887"}]
 * year : 2018
 * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg"}
 * alt : https://movie.douban.com/subject/27133303/
 * id : 27133303
 */
@Entity(tableName = "tb_movie",
        indices = [(Index(value = ["subjectId"], unique = true))])
data class SubjectsBean(@Embedded var rating: RatingBean? = null,
                        var title: String? = null,
                        @SerializedName("collect_count")
                        var collectCount: Int = 0,
                        @SerializedName("original_title")
                        var originalTitle: String? = null,
                        var subtype: String? = null,
                        var year: String? = null,
                        @Embedded var images: ImagesBean? = null,
                        var alt: String? = null,
                        @PrimaryKey(autoGenerate = true) var autoId: Int = 0,
                        @SerializedName("id") var subjectId: Int = 0,
                        @Ignore var genres: List<String>? = null,
                        @Ignore var casts: List<CastsBean>? = null,
                        @Ignore var directors: List<DirectorsBean>? = null
)

/**
 * max : 10
 * average : 7.2
 * stars : 40
 * min : 0
 */
data class RatingBean constructor(var max: Int = 0, var average: Double = 0.0, var min: Int = 0) {
    @Ignore
    constructor() : this(0)
}

/**
 * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg
 * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg
 * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2520331478.jpg
 */
data class ImagesBean constructor(var small: String? = null,
                                  var large: String? = null,
                                  var medium: String? = null) {
    @Ignore
    constructor() : this("")

}

@Entity(tableName = "tb_movie_genre",
        indices = [(Index(value = ["movieId"]))],
        foreignKeys = [(ForeignKey(entity = SubjectsBean::class,
                parentColumns = arrayOf("subjectId"),
                childColumns = arrayOf("movieId"),
                onDelete = ForeignKey.CASCADE))])
data class MovieGenre constructor(@PrimaryKey(autoGenerate = true) var autoId: Int = 0,
                                  var movieId: Int = 0,
                                  var genre: String? = null) {
    @Ignore
    constructor() : this(0)

    companion object {

        fun transform(movieId: Int, genres: List<String>?): List<MovieGenre>? {
            if (genres == null) return null

            val movieGenreList = ArrayList<MovieGenre>(genres.size)
            genres.forEach {
                val movieGenre = MovieGenre()
                movieGenre.movieId = movieId
                movieGenre.genre = it
                movieGenreList.add(movieGenre)
            }
            return movieGenreList
        }
    }
}

/**
 * alt : https://movie.douban.com/celebrity/1312940/
 * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg"}
 * name : 雷佳音
 * id : 1312940
 */
@Entity(tableName = "tb_movie_casts",
        indices = [(Index(value = ["movieId"]))],
        foreignKeys = [(ForeignKey(entity = SubjectsBean::class,
                parentColumns = arrayOf("subjectId"),
                childColumns = arrayOf("movieId"),
                onDelete = ForeignKey.CASCADE))])
data class CastsBean constructor(var alt: String? = null,
                                 @Embedded var avatars: AvatarsBean? = null,
                                 var name: String? = null,
                                 var id: String? = null,
                                 @PrimaryKey(autoGenerate = true) var autoId: Int = 0,
                                 var movieId: Int = 0
) {
    @Ignore
    constructor() : this("")
}

/**
 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg
 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg
 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1503986232.61.jpg
 */
data class AvatarsBean constructor(var small: String? = null,
                                   var large: String? = null,
                                   var medium: String? = null) {
    @Ignore
    constructor() : this("")
}

/**
 * alt : https://movie.douban.com/celebrity/1331887/
 * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520837465.56.jpg"}
 * name : 苏伦
 * id : 1331887
 */
@Entity(tableName = "tb_movie_director",
        indices = [(Index(value = ["movieId"]))],
        foreignKeys = [(ForeignKey(entity = SubjectsBean::class,
                parentColumns = arrayOf("subjectId"),
                childColumns = arrayOf("movieId"),
                onDelete = ForeignKey.CASCADE))])
data class DirectorsBean constructor(var alt: String? = null,
                                     @Embedded var avatars: AvatarsBean? = null,
                                     var name: String? = null,
                                     var id: Int = 0,
                                     @PrimaryKey(autoGenerate = true) var autoId: Int = 0,
                                     var movieId: Int = 0) {
    @Ignore
    constructor() : this("")
}
