package com.example.dojochallenge.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dojochallenge.data.model.KnownForImageModel

@Entity
data class TMDBPopularPeopleEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "known_for_department") val knowForDepartment: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "profile_path") val profilePathImage: String,
    @ColumnInfo(name = "known_for") val knownFor: List<KnownForImageModel>,
    @ColumnInfo(name = "biography") val biography: String,
    @ColumnInfo(name = "birthday") val birthday: String
)
