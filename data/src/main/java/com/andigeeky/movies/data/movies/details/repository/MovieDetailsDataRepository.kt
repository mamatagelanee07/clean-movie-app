package com.andigeeky.movies.data.movies.details.repository

import com.andigeeky.movies.data.movies.details.map
import com.andigeeky.movies.data.movies.details.source.MovieDetailsDataFactory
import com.andigeeky.movies.domain.movies.detail.model.MovieDetails
import com.andigeeky.movies.domain.movies.detail.repository.MovieDetailsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class MovieDetailsDataRepository @Inject constructor(
    private val movieDetailsDataFactory: MovieDetailsDataFactory
) : MovieDetailsRepository {

    override fun getMovieDetails(id: Int?): Flowable<MovieDetails?> {
        val apiCall = movieDetailsDataFactory
            .retrieveRemoteDataStore()
            .getMovieDetails(id)
            ?.map { it.map() }
            ?.flatMap {
                saveMovieDetails(it).toSingle { it }.toFlowable()
            }

        val dbCall = movieDetailsDataFactory
            .retrieveCacheDataStore()
            .getMovieDetails(id)
            .flatMap { Flowable.just(it.map()) }

        return Flowable.concat(dbCall, apiCall)
    }

    override fun clearMovieDetails(): Completable {
        return movieDetailsDataFactory.retrieveCacheDataStore()
            .clearMovieDetails()
    }

    override fun saveMovieDetails(details: MovieDetails?): Completable {
        return movieDetailsDataFactory.retrieveCacheDataStore()
            .saveMovieDetails(details?.map())
    }
}
