package com.andronest.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FavoritesDao{

    @Query("SELECT * FROM favorite_stops")
    suspend fun getAllStops(): List<FavoriteStopEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stop: FavoriteStopEntity)

    @Query("DELETE FROM favorite_stops where stop_location_id=:stopId")
    suspend fun delete(stopId: String)

}