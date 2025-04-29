package com.example.dojochallenge.data.database.mapper

import com.example.dojochallenge.data.database.entities.TMDBMovieEntity
import com.example.dojochallenge.data.model.TMDBMovieModel

object TMDBMovieEntityMapper : EntityMapper<List<TMDBMovieModel>, List<TMDBMovieEntity>> {

    override fun asEntity(domain: List<TMDBMovieModel>): List<TMDBMovieEntity> {
        return domain.mapIndexed { index, movie ->
            TMDBMovieEntity(
                id = movie.id,
                originalTitle = movie.originalTitle,
                overview = movie.overview,
                posterPathImage = movie.posterPathImage,
                releaseDate = movie.releaseDate,
                voteAverage = movie.voteAverage,
                category = movie.category,
                orderId = index
            )
        }
    }

    override fun asDomain(entity: List<TMDBMovieEntity>): List<TMDBMovieModel> {
        return entity.map { movieEntity ->
            TMDBMovieModel(
                id = movieEntity.id,
                originalTitle = movieEntity.originalTitle,
                overview = movieEntity.overview,
                posterPathImage = movieEntity.posterPathImage,
                releaseDate = movieEntity.releaseDate,
                voteAverage = movieEntity.voteAverage,
                category = movieEntity.category
            )
        }
    }
}

fun List<TMDBMovieModel>.asEntity(): List<TMDBMovieEntity> {
    return TMDBMovieEntityMapper.asEntity(this)
}

fun List<TMDBMovieEntity>.asDomain(): List<TMDBMovieModel> {
    return TMDBMovieEntityMapper.asDomain(this)
}