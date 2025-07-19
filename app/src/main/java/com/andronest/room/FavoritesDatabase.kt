package com.andronest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FavoriteStopEntity::class], version = 4)
abstract class FavoritesDatabase() : RoomDatabase() {
    abstract fun Dao(): FavoritesDao

    companion object {

        @Volatile private var instance: FavoritesDatabase? = null

        fun getInstance(context: Context): FavoritesDatabase{

             return instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(
                    context = context,
                    name = "favorites_db",
                    klass = FavoritesDatabase::class.java)
                    .fallbackToDestructiveMigration(true)
                    .build().also {
                        instance = it
                }
            }
        }
    }
}


