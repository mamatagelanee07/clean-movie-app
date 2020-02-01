package com.andigeeky.movies.data.movies.popular.source

import javax.inject.Inject

open class PopularMoviesDataFactory @Inject constructor(
    private val agencyCache : PopularMoviesCache,
    private val agenciesRemote: PopularMoviesRemote
){
    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): PopularMoviesCache {
        return agencyCache
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): PopularMoviesRemote {
        return agenciesRemote
    }
}
