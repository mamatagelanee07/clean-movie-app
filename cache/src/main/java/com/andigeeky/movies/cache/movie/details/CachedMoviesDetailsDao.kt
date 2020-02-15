package com.andigeeky.movies.cache.movie.details

import androidx.room.*
import com.andigeeky.movies.cache.movie.details.model.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CachedMoviesDetailsDao {

    @Transaction
    @Query("select * from CachedMovieDetails where id = :id")
    fun getMovieDetails(id : Int?): Flowable<EmbeddedCachedMovieDetails?>

    @Query("Delete from CachedMovieDetails")
    fun clearMovieDetails(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetails(movie: CachedMovieDetails?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenres(movie: List<CachedGenre?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenreJoins(movie: List<MovieGenreJoin?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieProductionCompanies(movie: List<CachedProductionCompany?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieProductionCompanyJoins(movie: List<MovieProductionCompanyJoin?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieProductionCountries(movie: List<CachedProductionCountry?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieProductionCountryJoins(movie: List<MovieProductionCountryJoin?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieSpokenLanguages(movie: List<CachedSpokenLanguage?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieSpokenLanguageJoin(movie: List<MovieSpokenLanguageJoin?>?)

    @Transaction
    fun insertMovieDetails(
        movie: EmbeddedCachedMovieDetails?
    ) {
        insertMovieDetails(movie?.movieDetails)
        insertMovieGenres(movie?.genres)
        insertMovieGenreJoins(movie?.genres?.map {
            MovieGenreJoin(
                movieId = movie.movieDetails.id,
                genreId = it.id
            )
        })
        insertMovieProductionCompanies(movie?.productionCompanies)
        insertMovieProductionCompanyJoins(movie?.productionCompanies?.map {
            MovieProductionCompanyJoin(
                movieId = movie.movieDetails.id,
                productionCompanyId = it.id
            )
        })

        insertMovieProductionCountries(movie?.productionCountries)
        insertMovieProductionCountryJoins(movie?.productionCountries?.map {
            MovieProductionCountryJoin(
                movieId = movie.movieDetails.id,
                productionCountryId = it.iso31661
            )
        })

        insertMovieSpokenLanguages(movie?.spokenLanguages)
        insertMovieSpokenLanguageJoin(movie?.spokenLanguages?.map {
            MovieSpokenLanguageJoin(
                movieId = movie.movieDetails.id,
                spokenLanguageId = it.iso6391
            )
        })
    }
}
