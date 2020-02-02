package com.andigeeky.cleanmovieapp.di.modules

import com.andigeeky.movies.data.executor.JobExecutor
import com.andigeeky.movies.data.movies.popular.repository.PopularMoviesDataRepository
import com.andigeeky.movies.domain.executor.ThreadExecutor
import com.andigeeky.movies.domain.movies.popular.repository.PopularMoviesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindPopularMoviesRepository(popularMoviesRepository: PopularMoviesDataRepository):
            PopularMoviesRepository

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor
}
