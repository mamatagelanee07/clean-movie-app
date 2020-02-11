package com.andigeeky.movies.data.movies.details.source

import javax.inject.Inject

open class MovieDetailsDataFactory @Inject constructor(
    private val movieDetailsCache : MovieDetailsCache,
    private val movieDetailsRemote: MovieDetailsRemote
){
    open fun retrieveCacheDataStore(): MovieDetailsCache {
        return movieDetailsCache
    }

    open fun retrieveRemoteDataStore(): MovieDetailsRemote {
        return movieDetailsRemote
    }
}
