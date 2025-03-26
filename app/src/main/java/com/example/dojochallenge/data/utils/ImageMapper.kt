package com.example.dojochallenge.data.utils

object ImageMapper {
    private const val WIDTH_300 = "300"
    private const val HEIGHT_450 = "450"
    private const val WIDTH_600 = "600"
    private const val HEIGHT_900 = "900"
    private const val MAIN_PATH = "https://media.themoviedb.org/t/p/"

    private fun getFullImagePath(profilePathImage: String, width: String, height: String): String {
        return "${MAIN_PATH}w${width}_and_h${height}_bestv2$profilePathImage"
    }

    fun getFullImagePathW300xH450(profilePathImage: String): String {
        return getFullImagePath(profilePathImage, WIDTH_300, HEIGHT_450)
    }

    fun getFullImagePathW600xH900(profilePathImage: String): String {
        return getFullImagePath(profilePathImage, WIDTH_600, HEIGHT_900)
    }

}