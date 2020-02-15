package com.andigeeky.movies.cache.movie.details.model

import androidx.room.*
import com.andigeeky.movies.cache.movie.db.convertors.AnyTypeConverter
import com.andigeeky.movies.data.movies.details.model.MovieDetailsEntity

data class EmbeddedCachedMovieDetails(
   @Embedded val movieDetails: CachedMovieDetails,

   @Relation(
       parentColumn = "id",
       entityColumn = "id",
       associateBy = Junction(
           value = MovieGenreJoin::class,
           parentColumn = "movieId",
           entityColumn = "genreId"
       )
   )
    val genres: List<CachedGenre>?,

   @Relation(
       parentColumn = "id",
       entityColumn = "id",
       associateBy = Junction(
           value = MovieProductionCompanyJoin::class,
           parentColumn = "movieId",
           entityColumn = "productionCompanyId"
       )
   )
    val productionCompanies: List<CachedProductionCompany>?,

   @Relation(
       parentColumn = "id",
       entityColumn = "iso31661",
       associateBy = Junction(
           value = MovieProductionCountryJoin::class,
           parentColumn = "movieId",
           entityColumn = "productionCountryId"
       )
   )
    val productionCountries: List<CachedProductionCountry>?,

   @Relation(
       parentColumn = "id",
       entityColumn = "iso6391",
       associateBy = Junction(
           value = MovieSpokenLanguageJoin::class,
           parentColumn = "movieId",
           entityColumn = "spokenLanguageId"
       )
   )
    val spokenLanguages: List<CachedSpokenLanguage>?
)

@Entity
@TypeConverters(value = [AnyTypeConverter::class])
data class CachedMovieDetails(
    @PrimaryKey
    val id: Int,
    val adult: Boolean?,
    val backdropPath: String?,
    val belongsToCollection: Any?,
    val budget: Int?,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: Any?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)

internal fun EmbeddedCachedMovieDetails.map() : MovieDetailsEntity{
    return MovieDetailsEntity(
        adult = this.movieDetails.adult,
        belongsToCollection = this.movieDetails.belongsToCollection,
        budget = this.movieDetails.budget,
        id = this.movieDetails.id,
        popularity = this.movieDetails.popularity,
        posterPath = this.movieDetails.posterPath,
        revenue = this.movieDetails.revenue,
        runtime = this.movieDetails.runtime,
        video = this.movieDetails.video,
        voteAverage = this.movieDetails.voteAverage,
        voteCount = this.movieDetails.voteCount,
        backdropPath = this.movieDetails.backdropPath,
        genres = this.genres?.map { it.map() },
        homepage = this.movieDetails.homepage,
        imdbId = this.movieDetails.imdbId,
        originalLanguage = this.movieDetails.originalLanguage,
        originalTitle = this.movieDetails.originalTitle,
        overview = this.movieDetails.overview,
        productionCompanies = this.productionCompanies?.map { it.map() },
        productionCountries = this.productionCountries?.map { it.map() },
        releaseDate = this.movieDetails.releaseDate,
        spokenLanguages = this.spokenLanguages?.map { it.map() },
        status = this.movieDetails.status,
        tagline = this.movieDetails.tagline,
        title = this.movieDetails.title
    )
}


internal fun MovieDetailsEntity.map() : EmbeddedCachedMovieDetails{
    return EmbeddedCachedMovieDetails(
        movieDetails = CachedMovieDetails(
            adult = this.adult,
            belongsToCollection = this.belongsToCollection,
            budget = this.budget,
            id = this.id,
            popularity = this.popularity,
            posterPath = this.posterPath,
            revenue = this.revenue,
            runtime = this.runtime,
            video = this.video,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount,
            backdropPath = this.backdropPath,
            homepage = this.homepage,
            imdbId = this.imdbId,
            originalLanguage = this.originalLanguage,
            originalTitle = this.originalTitle,
            overview = this.overview,
            releaseDate = this.releaseDate,
            status = this.status,
            tagline = this.tagline,
            title = this.title
        ),
        genres = this.genres?.map { it.map() },
        productionCompanies = this.productionCompanies?.map { it.map() },
        productionCountries = this.productionCountries?.map { it.map() },
        spokenLanguages = this.spokenLanguages?.map { it.map() }
    )
}
