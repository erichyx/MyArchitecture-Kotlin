package cn.eric.arch.data.local

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.support.annotation.WorkerThread


/**
 * Created by eric on 2018/5/31
 */
@Database(entities = [(SubjectsBean::class),
    (CastsBean::class),
    (DirectorsBean::class),
    (MovieGenre::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    private val mIsDatabaseCreated = MutableLiveData<Boolean>()

    val databaseCreated: LiveData<Boolean>
        get() = mIsDatabaseCreated

    abstract fun movieDao(): MovieDao

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }

    @WorkerThread
    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    companion object {
        private var sInstance: AppDatabase? = null
        const val DATABASE_NAME = "arch.db"

        fun instance(context: Context): AppDatabase {
            if (sInstance == null) {
                synchronized(this) {
                    if (sInstance == null) {
                        sInstance = buildDatabase(context.applicationContext)
                        sInstance!!.updateDatabaseCreated(context.applicationContext)
                    }
                }
            }
            return sInstance!!
        }

        private fun buildDatabase(appContext: Context): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // 该方法在线程中执行
                            val database = AppDatabase.instance(appContext)
                            database.setDatabaseCreated()
                        }
                    }).build()
        }
    }
}
