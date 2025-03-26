package com.example.dojochallenge.data.model

import com.example.dojochallenge.data.utils.ImageMapper
import com.google.gson.annotations.SerializedName

data class TMBDPopularPeopleListResponse(
    @SerializedName("page") val page: Int = 0,
    @SerializedName("results") val results: List<TMDBPeopleModel> = emptyList()
)


data class TMDBPeopleModel(
    @SerializedName("name") val name: String = "",
    @SerializedName("known_for_department") val knowForDepartment: String = "",
    @SerializedName("gender") val genderId: Int = 2,
    @SerializedName("profile_path") val profilePathImage: String = "",
    @SerializedName("known_for") val knownFor: List<KnownForImage> = emptyList()
) {
    val fullProfilePathImageW300xH450: String
        get() = ImageMapper.getFullImagePathW300xH450(profilePathImage)

    val fullProfilePathImageW600xH900: String
        get() = ImageMapper.getFullImagePathW600xH900(profilePathImage)

    val gender: String
        get() = if (genderId == 1) FEMALE else MALE

    companion object {
        private const val FEMALE = "Female"
        private const val MALE = "Male"
    }

}


data class KnownForImage(
    @SerializedName("title") val title: String = "",
    @SerializedName("poster_path") val posterPathImage: String = "",
) {
    val fullPosterPathImageW300xH450: String
        get() = ImageMapper.getFullImagePathW300xH450(posterPathImage)

    val fullPosterPathImageW600xH900: String
        get() = ImageMapper.getFullImagePathW600xH900(posterPathImage)
}
