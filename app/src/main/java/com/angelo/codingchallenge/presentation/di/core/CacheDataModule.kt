package com.angelo.codingchallenge.presentation.di.core

import com.angelo.codingchallenge.domain.repository.rates.RatesCacheDataSource
import com.angelo.codingchallenge.domain.repository.rates.RatesCacheDataSourceImpl
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsCacheDataSource
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideSymbolsCacheDataModule(): SymbolsCacheDataSource = SymbolsCacheDataSourceImpl()

    @Singleton
    @Provides
    fun provideRatesCacheDataModule(): RatesCacheDataSource = RatesCacheDataSourceImpl()
}