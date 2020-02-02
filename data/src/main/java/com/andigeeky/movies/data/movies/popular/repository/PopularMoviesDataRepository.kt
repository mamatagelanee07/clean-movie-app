package com.andigeeky.movies.data.movies.popular.repository

import com.andigeeky.movies.data.movies.popular.model.map
import com.andigeeky.movies.data.movies.popular.model.mapEntity
import com.andigeeky.movies.data.movies.popular.source.PopularMoviesDataFactory
import com.andigeeky.movies.domain.movies.popular.model.Movie
import com.andigeeky.movies.domain.movies.popular.repository.PopularMoviesRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class PopularMoviesDataRepository @Inject constructor(
    private val popularMoviesDataFactory: PopularMoviesDataFactory
) : PopularMoviesRepository {

    override fun getMovies(params: Int?): Flowable<List<Movie?>?> {
        val apiCall = popularMoviesDataFactory
            .retrieveRemoteDataStore()
            .getMovies(params)
            .flatMap {
                Flowable.just(it.map { movie -> movie?.map() })
            }.flatMap {
                saveMovies(it).toSingle { it }.toFlowable()
            }

        val dbCall = popularMoviesDataFactory
            .retrieveCacheDataStore()
            .getMovies(params)
            .flatMap { Flowable.just(it.map { movie -> movie.map() }) }

        return apiCall //Flowable.merge(apiCall, dbCall)
    }

    override fun clearMovies(): Completable {
        return popularMoviesDataFactory.retrieveCacheDataStore()
            .clearMovies()
    }

    override fun saveMovies(movies: List<Movie?>?): Completable {
        return popularMoviesDataFactory.retrieveCacheDataStore()
            .saveMovies(movies?.map { it?.mapEntity() })
    }
}
