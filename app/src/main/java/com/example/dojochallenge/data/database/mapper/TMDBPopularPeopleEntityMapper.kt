package com.example.dojochallenge.data.database.mapper

import com.example.dojochallenge.data.database.entities.TMDBPopularPeopleEntity
import com.example.dojochallenge.data.model.TMDBPopularPeopleModel

object TMDBPopularPeopleEntityMapper : EntityMapper<List<TMDBPopularPeopleModel>, List<TMDBPopularPeopleEntity>> {

    override fun asEntity(domain: List<TMDBPopularPeopleModel>): List<TMDBPopularPeopleEntity> {
        return domain.mapIndexed { index, person ->
            TMDBPopularPeopleEntity(
                id = person.id,
                name = person.name,
                knowForDepartment = person.knowForDepartment,
                gender = person.gender,
                profilePathImage = person.profilePathImage,
                knownFor = person.knownFor,
                biography = person.biography,
                birthday = person.birthday,
                orderId = index
            )
        }
    }

    override fun asDomain(entity: List<TMDBPopularPeopleEntity>): List<TMDBPopularPeopleModel> {
        return entity.map { personEntity ->
            TMDBPopularPeopleModel(
                id = personEntity.id,
                name = personEntity.name,
                knowForDepartment = personEntity.knowForDepartment,
                gender = personEntity.gender,
                profilePathImage = personEntity.profilePathImage,
                knownFor = personEntity.knownFor,
                biography = personEntity.biography,
                birthday = personEntity.birthday
            )
        }
    }
}

fun List<TMDBPopularPeopleModel>.asEntity(): List<TMDBPopularPeopleEntity> {
    return TMDBPopularPeopleEntityMapper.asEntity(this)
}

fun List<TMDBPopularPeopleEntity>.asDomain(): List<TMDBPopularPeopleModel> {
    return TMDBPopularPeopleEntityMapper.asDomain(this)
}