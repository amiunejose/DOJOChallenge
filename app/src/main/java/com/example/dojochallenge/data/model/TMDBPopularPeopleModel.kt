package com.example.dojochallenge.data.model

import com.example.dojochallenge.data.dto.TMDBPeopleDetailsDTO
import com.example.dojochallenge.data.utils.ImageMapper
import kotlinx.serialization.Serializable

@Serializable
data class TMDBPopularPeopleModel(
    val id: Int = 0,
    val name: String = "",
    val knowForDepartment: String = "",
    val gender: String = "",
    val profilePathImage: String = "",
    val knownFor: List<KnownForImageModel> = emptyList(),
    val biography: String = "",
    val birthday: String = ""
) {
    val fullProfilePathImageW300xH450: String
        get() = ImageMapper.getFullImagePathW300xH450(profilePathImage)

    val fullProfilePathImageW600xH900: String
        get() = ImageMapper.getFullImagePathW600xH900(profilePathImage)
}

fun TMDBPopularPeopleModel.updateWithDetail(detail: TMDBPeopleDetailsDTO): TMDBPopularPeopleModel {
    return this.copy(
        biography = detail.biography ?: this.biography,
        birthday = detail.birthday?: this.birthday
    )
}

@Serializable
data class KnownForImageModel(
    val title: String = "",
    val posterPathImage: String = "",
) {
    val fullPosterPathImageW300xH450: String
        get() = ImageMapper.getFullImagePathW300xH450(posterPathImage)

    val fullPosterPathImageW600xH900: String
        get() = ImageMapper.getFullImagePathW600xH900(posterPathImage)
}
