package com.example.dojochallenge.data.dto

import com.example.dojochallenge.data.model.KnownForImageModel
import com.example.dojochallenge.data.model.TMDBPopularPeopleModel
import com.google.gson.annotations.SerializedName

data class TMBDPopularPeopleListDTO(
    @SerializedName("page") val page: Int = 0,
    @SerializedName("results") val results: List<TMDBPopularPeopleDTO> = emptyList()
)

fun TMBDPopularPeopleListDTO.toDomainModelList(): List<TMDBPopularPeopleModel> {
    return results.map { it.toDomainModel() }
}

data class TMDBPopularPeopleDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("known_for_department") val knowForDepartment: String,
    @SerializedName("gender") val genderId: Int,
    @SerializedName("profile_path") val profilePathImage: String,
    @SerializedName("known_for") val knownFor: List<KnownForImageDTO>,
)

fun TMDBPopularPeopleDTO.toDomainModel(): TMDBPopularPeopleModel {
    return try {
        TMDBPopularPeopleModel(
            id = id,
            name = name,
            knowForDepartment = knowForDepartment,
            gender = if (genderId == 1) "Female" else "Male",
            profilePathImage = profilePathImage,
            knownFor = knownFor.map { it.toDomainModel() }
        )
    } catch (e: Exception) {
        TMDBPopularPeopleModel()
    }
}


data class KnownForImageDTO(
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPathImage: String,
)

fun KnownForImageDTO.toDomainModel(): KnownForImageModel {
    return try {
        KnownForImageModel(
            title = title,
            posterPathImage = posterPathImage
        )
    } catch (e: Exception) {
        KnownForImageModel()
    }
}

data class TMDBPeopleDetailsDTO(
    @SerializedName("biography") val biography: String,
    @SerializedName("birthday") val birthday: String
)