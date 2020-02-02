package com.andigeeky.movies.remote.movies.popular.source

import com.andigeeky.movies.data.movies.popular.model.MovieEntity
import com.andigeeky.movies.data.movies.popular.source.PopularMoviesRemote
import com.andigeeky.movies.remote.movies.MoviesService
import com.andigeeky.movies.remote.movies.popular.model.mapEntity
import io.reactivex.Flowable
import javax.inject.Inject

class PopularMoviesRemoteImpl @Inject constructor(
    private val service: MoviesService
) : PopularMoviesRemote{

    override fun getMovies(pageNumber: Int?): Flowable<List<MovieEntity?>?> {
        return service.getPopularMovies(pageNumber)?.map {
            it.results?.map { movie -> movie?.mapEntity() }
        } ?: Flowable.just(emptyList())
    }
}
