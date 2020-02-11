package com.andigeeky.movies.remote.movies.details.source

import com.andigeeky.movies.data.movies.details.model.MovieDetailsEntity
import com.andigeeky.movies.data.movies.details.source.MovieDetailsRemote
import com.andigeeky.movies.remote.movies.MoviesService
import com.andigeeky.movies.remote.movies.details.model.map
import io.reactivex.Flowable
import javax.inject.Inject

class MovieDetailsRemoteImpl @Inject constructor(
    private val service: MoviesService
) : MovieDetailsRemote{

    override fun getMovieDetails(id: Int?): Flowable<MovieDetailsEntity?>? {
        return service.getMovieDetails(id)?.map {
            it.map()
        }
    }
}
