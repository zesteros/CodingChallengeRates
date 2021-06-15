package com.angelo.codingchallenge.presentation.di.rates

import com.angelo.codingchallenge.domain.usecase.GetRatesUseCase
import com.angelo.codingchallenge.presentation.di.symbols.SymbolsScope
import com.angelo.codingchallenge.presentation.rates.RatesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RatesModule {
    @RatesScope
    @Provides
    fun provideRatesViewModelFactory(ratesUseCase: GetRatesUseCase): RatesViewModelFactory =
        RatesViewModelFactory(ratesUseCase)
}