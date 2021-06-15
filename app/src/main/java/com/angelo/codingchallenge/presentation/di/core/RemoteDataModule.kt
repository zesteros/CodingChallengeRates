package com.angelo.codingchallenge.presentation.di.core

import com.angelo.codingchallenge.data.api.CurrencyService
import com.angelo.codingchallenge.domain.repository.rates.RatesRemoteDataSource
import com.angelo.codingchallenge.domain.repository.rates.RatesRemoteDataSourceImpl
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsRemoteDataSource
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {
    @Singleton
    @Provides
    fun provideSymbolsRemoteDataSource(
        currencyService: CurrencyService): SymbolsRemoteDataSource = SymbolsRemoteDataSourceImpl(currencyService, apiKey)

    @Singleton
    @Provides
    fun provideRatesRemoteDataSource(
        currencyService: CurrencyService): RatesRemoteDataSource = RatesRemoteDataSourceImpl(currencyService, apiKey)
}