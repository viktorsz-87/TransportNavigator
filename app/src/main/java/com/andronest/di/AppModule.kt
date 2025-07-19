package com.andronest.di

import android.content.Context
import com.andronest.repository.TravelRepository
import com.andronest.retrofit.ResRobotAPI
import com.andronest.retrofit.RetrofitInstance
import com.andronest.room.FavoritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Singleton
    @Provides
    fun provideRetrofitInstance(): ResRobotAPI {
        return RetrofitInstance.resRobotAPI
    }

    @Singleton
    @Provides
    fun provideTravelRepository(resRobotAPI: ResRobotAPI, database: FavoritesDatabase): TravelRepository {
        return TravelRepository(resRobotAPI, database.Dao())
    }

    @Singleton
    @Provides
    fun provideDatabaseInstance(@ApplicationContext context: Context): FavoritesDatabase{
        return FavoritesDatabase.getInstance(context)
    }

}