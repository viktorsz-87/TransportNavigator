package com.andronest.di

import com.andronest.repository.TravelRepository
import com.andronest.retrofit.ResRobotAPI
import com.andronest.retrofit.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideTravelRepository(resRobotAPI: ResRobotAPI): TravelRepository {
        return TravelRepository(resRobotAPI)
    }

}