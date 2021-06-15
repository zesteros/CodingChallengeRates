package com.angelo.codingchallenge.domain.repository.symbols

import com.angelo.codingchallenge.data.model.Rates
import com.angelo.codingchallenge.data.model.SupportedSymbols

class SymbolsCacheDataSourceImpl : SymbolsCacheDataSource {
    private var symbols = SupportedSymbols(true, HashMap(), null)

    override suspend fun getSymbolsFromCache(): SupportedSymbols = symbols

    override suspend fun saveSymbolsToCache(symbols: SupportedSymbols) {
        this.symbols = symbols
    }
}