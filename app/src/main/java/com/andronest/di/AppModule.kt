package com.andronest.di

import com.andronest.repository.TravelRepository
import com.andronest.retrofit.RetrofitApi
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
    fun provideRetrofitInstance(): RetrofitApi {
        return RetrofitInstance.retrofitApi
    }

    @Singleton
    @Provides
    fun provideTravelRepository(retrofitApi: RetrofitApi): TravelRepository {
        return TravelRepository(retrofitApi)
    }

}