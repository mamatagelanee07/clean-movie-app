package com.andigeeky.movies.cache.movie.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andigeeky.movies.cache.movie.popular.CachedPopularMoviesDao
import com.andigeeky.movies.cache.movie.popular.model.CachedMovie
import javax.inject.Inject

@Database(entities = [CachedMovie::class], version = 1)
abstract class MoviesDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedPopularMoviesDao(): CachedPopularMoviesDao

    private var database: MoviesDatabase? = null

    private val sLock = Any()

    fun getInstance(context: Context): MoviesDatabase {
        if (database == null) {
            synchronized(sLock) {
                if (database == null) {
                    database = Room.databaseBuilder(context.applicationContext,
                            MoviesDatabase::class.java,NAME)
                            .build()
                }
                return database!!
            }
        }
        return database!!
    }

    companion object{
        const val NAME = "movies.db"
    }

}
