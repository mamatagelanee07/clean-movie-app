package com.andigeeky.movies.cache.movie.popular.model

import androidx.room.*
import com.andigeeky.movies.cache.movie.db.convertors.IntListTypeConverter
import com.andigeeky.movies.data.movies.popular.model.MovieEntity
import com.andigeeky.movies.data.movies.popular.model.PopularMoviesEntity

data class CachedPopularMovies(
    @Embedded
    val page: CachedMoviePages,
    @Relation(
        parentColumn = "page",
        entity = CachedMovie::class,
        entityColumn = "id",
        associateBy = Junction(
            value = CachedPageWithMovies::class,
            parentColumn = "pageId",
            entityColumn = "movieId"
        )
    )
    val results: List<CachedMovie?>?
)

@Entity(
    primaryKeys = ["pageId", "movieId"]
)
data class CachedPageWithMovies(
    @ColumnInfo(index = true)
    val pageId: Int,
    @ColumnInfo(index = true)
    val movieId: Int
)

@Entity
data class CachedMoviePages(
    @PrimaryKey
    val page: Int,
    val totalPages: Int?,
    val totalResults: Int?
)

@Entity
@TypeConverters(IntListTypeConverter::class)
data class CachedMovie(
    @PrimaryKey
    val id: Int,
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)

fun PopularMoviesEntity.map() : CachedPopularMovies{
    return CachedPopularMovies(
        page = CachedMoviePages(
            page = this.page,
            totalResults = this.totalResults,
            totalPages = this.totalPages
        ),
        results = this.results?.map { it?.map() }
    )
}

fun CachedPopularMovies.map() : PopularMoviesEntity{
    return PopularMoviesEntity(
        page = this.page.page,
        results = this.results?.map { it?.map() },
        totalPages = this.page.totalPages,
        totalResults = this.page.totalResults
    )
}

internal fun MovieEntity.map() : CachedMovie{
    return CachedMovie(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

internal fun CachedMovie.map() : MovieEntity{
    return MovieEntity(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}
