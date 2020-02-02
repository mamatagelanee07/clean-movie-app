package com.andigeeky.cleanmovieapp.di.modules

import android.app.Application
import androidx.room.Room
import com.andigeeky.movies.cache.movie.db.MoviesDatabase
import com.andigeeky.movies.cache.movie.popular.source.PopularMoviesCacheImpl
import com.andigeeky.movies.data.movies.popular.source.PopularMoviesCache
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Module that provides all dependencies from the cache package/layer.
 */
@Module
abstract class CacheModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideMoviesDatabase(application: Application): MoviesDatabase {
            return Room.databaseBuilder(
                application.applicationContext,
                MoviesDatabase::class.java, MoviesDatabase.NAME
            ).build()
        }
    }

    @Binds
    abstract fun bindPopularMoviesCache(agenciesCacheImpl: PopularMoviesCacheImpl): PopularMoviesCache
}
