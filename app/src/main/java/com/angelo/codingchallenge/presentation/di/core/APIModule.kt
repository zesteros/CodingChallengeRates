package com.angelo.codingchallenge.presentation.di.core

import com.angelo.codingchallenge.data.api.CurrencyService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class APIModule(private val baseUrl: String) {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).build()
    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit) = retrofit.create(CurrencyService::class.java)
}