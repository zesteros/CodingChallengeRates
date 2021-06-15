package com.angelo.codingchallenge.presentation.di.core

import com.angelo.codingchallenge.domain.repository.rates.RatesRepository
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsRepository
import com.angelo.codingchallenge.domain.usecase.GetRatesUseCase
import com.angelo.codingchallenge.domain.usecase.GetSymbolsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGetSymbolsUseCase(currencyRepository: SymbolsRepository) : GetSymbolsUseCase = GetSymbolsUseCase(currencyRepository)

    @Provides
    @Singleton
    fun provideGetRatesUseCase(ratesRepository: RatesRepository) : GetRatesUseCase = GetRatesUseCase(ratesRepository)
}