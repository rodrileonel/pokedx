package com.rodrigo.crashcourse.di

import com.rodrigo.crashcourse.data.PokeApi
import com.rodrigo.crashcourse.data.PokeRepository
import com.rodrigo.crashcourse.data.PokeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit= Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApi(retrofit: Retrofit): PokeApi = retrofit.create(PokeApi::class.java)

    @Provides
    fun provideRepo(api: PokeApi): PokeRepository = PokeRepositoryImpl(api)

}

