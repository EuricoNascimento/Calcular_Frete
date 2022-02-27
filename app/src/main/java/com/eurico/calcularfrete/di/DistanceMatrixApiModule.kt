package com.eurico.calcularfrete.di

import com.eurico.calcularfrete.data.api.ApiConstants
import com.eurico.calcularfrete.data.api.DistanceMatrixApi
import com.eurico.calcularfrete.data.reporsitory.DistanceMatrixRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DistanceMatrixApiModule {
/*
    @Singleton
    @Provides
    fun provideDistanceMatrixRepository(
        api: DistanceMatrixApi
    ) = DistanceMatrixRepository(api)


    @Singleton
    @Provides
    fun providesDistanceMatrixApi(): DistanceMatrixApi{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(DistanceMatrixApi::class.java)

    }
*/

    @Provides
    @Singleton
    fun provideRepository(builder: Retrofit.Builder): DistanceMatrixApi{
        return builder
            .build()
            .create(DistanceMatrixApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}