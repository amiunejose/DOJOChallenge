package com.example.dojochallenge.data.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dojochallenge.data.model.KnownForImageModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@ProvidedTypeConverter
class KnownForImageConverter @Inject constructor(private val json: Json) {

    @TypeConverter
    fun toKnownForList(value: String): List<KnownForImageModel> {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromKnownForList(value: List<KnownForImageModel>): String {
        return json.encodeToString(value)
    }
}