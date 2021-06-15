package com.angelo.codingchallenge.presentation.di.symbols

import com.angelo.codingchallenge.domain.usecase.GetSymbolsUseCase
import com.angelo.codingchallenge.presentation.symbols.SymbolsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SymbolsModule {
    @SymbolsScope
    @Provides
    fun provideSymbolsViewModelFactory(symbolsUseCase: GetSymbolsUseCase): SymbolsViewModelFactory =
        SymbolsViewModelFactory(symbolsUseCase)
}