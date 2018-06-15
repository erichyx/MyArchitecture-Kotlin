package cn.eric.arch.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction


import java.util.ArrayList

/**
 * Created by eric on 2018/5/31
 */
@Dao
abstract class MovieDao {
    @get:Query("SELECT * FROM tb_movie")
    @get:Transaction
    abstract val allMovies: LiveData<List<MovieInfo>>

    @Query("DELETE FROM tb_movie")
    abstract fun deleteAllMovie()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(list: List<SubjectsBean>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCasts(list: List<CastsBean>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDirectors(list: List<DirectorsBean>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertGenres(list: List<MovieGenre>)

    @Transaction
    open fun insetAllMovies(movieInfoList: List<MovieInfo>) {

        val subjectList = ArrayList<SubjectsBean>()
        val castsList = ArrayList<CastsBean>()
        val genresList = ArrayList<MovieGenre>()
        val directorList = ArrayList<DirectorsBean>()

        movieInfoList.forEach {
            val subjectsBean = it.subject
            if (subjectsBean != null) {
                subjectList.add(subjectsBean)

                val casts = subjectsBean.casts
                casts?.forEach {
                    it.movieId = subjectsBean.subjectId
                }
                casts?.let {
                    castsList.addAll(it)
                }

                val genres = subjectsBean.genres
                genres?.let {
                    val transform = MovieGenre.transform(subjectsBean.subjectId, it)
                    if (transform != null) {
                        genresList.addAll(transform)
                    }
                }

                val directors = subjectsBean.directors
                directors?.forEach {
                    it.movieId = subjectsBean.subjectId
                }
                directors?.let {
                    directorList.addAll(it)
                }
            }
        }

        insertMovies(subjectList)
        insertGenres(genresList)
        insertCasts(castsList)
        insertDirectors(directorList)
    }
}
