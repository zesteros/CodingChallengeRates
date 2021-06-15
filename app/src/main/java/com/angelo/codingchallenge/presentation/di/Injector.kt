package com.angelo.codingchallenge.presentation.di

import com.angelo.codingchallenge.presentation.di.rates.RatesSubComponent
import com.angelo.codingchallenge.presentation.di.symbols.SymbolsSubComponent

interface Injector {
    fun createSymbolsSubComponent() : SymbolsSubComponent
    fun createRatesSubComponent() : RatesSubComponent
}