package com.angelo.codingchallenge.presentation.di.core

import com.angelo.codingchallenge.data.repository.rates.RatesRepositoryImpl
import com.angelo.codingchallenge.data.repository.symbols.SymbolsRepositoryImpl
import com.angelo.codingchallenge.domain.repository.rates.RatesCacheDataSource
import com.angelo.codingchallenge.domain.repository.rates.RatesRemoteDataSource
import com.angelo.codingchallenge.domain.repository.rates.RatesRepository
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsCacheDataSource
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsRemoteDataSource
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSymbolsRepository(
        symbolsRemoteDataSource: SymbolsRemoteDataSource,
        symbolsCacheDataSource: SymbolsCacheDataSource
    ): SymbolsRepository = SymbolsRepositoryImpl(symbolsRemoteDataSource, symbolsCacheDataSource)

    @Provides
    @Singleton
    fun provideRatesRepository(
        ratesRemoteDataSource: RatesRemoteDataSource, ratesCacheDataSource: RatesCacheDataSource
    ): RatesRepository = RatesRepositoryImpl(ratesRemoteDataSource, ratesCacheDataSource)
}