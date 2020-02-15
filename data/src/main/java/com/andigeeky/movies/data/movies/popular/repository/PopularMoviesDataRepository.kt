package com.andigeeky.movies.data.movies.popular.repository

import com.andigeeky.movies.data.movies.popular.model.mapEntity
import com.andigeeky.movies.data.movies.popular.source.PopularMoviesDataFactory
import com.andigeeky.movies.domain.movies.popular.model.PopularMovies
import com.andigeeky.movies.domain.movies.popular.repository.PopularMoviesRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class PopularMoviesDataRepository @Inject constructor(
    private val popularMoviesDataFactory: PopularMoviesDataFactory
) : PopularMoviesRepository {

    override fun getMovies(pageNumber: Int?): Flowable<PopularMovies?> {
        val apiCall = popularMoviesDataFactory
            .retrieveRemoteDataStore()
            .getMovies(pageNumber)
            ?.map { it.mapEntity() }
            ?.flatMap {
                saveMovies(it).toSingle { it }.toFlowable()
            }

        val dbCall = popularMoviesDataFactory
            .retrieveCacheDataStore()
            .getMovies(pageNumber)
            .map { it.mapEntity() }
            .toFlowable()

        return Flowable.concatArrayDelayError(dbCall, apiCall)
    }

    override fun clearMovies(): Completable {
        return popularMoviesDataFactory.retrieveCacheDataStore()
            .clearMovies()
    }

    override fun saveMovies(movies: PopularMovies?): Completable {
        return popularMoviesDataFactory.retrieveCacheDataStore()
            .saveMovies(movies?.mapEntity())
    }
}
