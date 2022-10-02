package com.example.rickandmorty.di


import com.example.rickandmorty.BuildConfig
import com.example.rickandmorty.data.api.RickAndMortyApi
import com.example.rickandmorty.data.repo.RickAndMortyRepositoryImpl
import com.example.rickandmorty.domain.repo.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApi(): RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRickAndMortyRepository(api: RickAndMortyApi): RickAndMortyRepository {
        return RickAndMortyRepositoryImpl(api)
    }

}