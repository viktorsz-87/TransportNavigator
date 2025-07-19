package com.andronest.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_stops")
data class FavoriteStopEntity(

    @PrimaryKey
    @ColumnInfo(name="stop_location_id")
    val id: String,

    @ColumnInfo(name="name")
    val name: String,

    @ColumnInfo(name="latitude")
    val latitude: Double,

    @ColumnInfo(name="longitude")
    val longitude: Double,

    @ColumnInfo(name="is_favorite", defaultValue = "true")
    val isFavorite: Boolean = true,
)
