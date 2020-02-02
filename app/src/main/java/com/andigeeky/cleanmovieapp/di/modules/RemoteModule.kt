package com.andigeeky.cleanmovieapp.di.modules

import com.andigeeky.cleanmovieapp.BuildConfig
import com.andigeeky.movies.data.movies.popular.source.PopularMoviesRemote
import com.andigeeky.movies.remote.movies.MoviesService
import com.andigeeky.movies.remote.movies.MoviesServiceFactory
import com.andigeeky.movies.remote.movies.popular.source.PopularMoviesRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module
abstract class RemoteModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideMoviesService(): MoviesService {
            return MoviesServiceFactory.make(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindPopularMoviesRemote(popularMoviesRemoteImpl: PopularMoviesRemoteImpl): PopularMoviesRemote
}
