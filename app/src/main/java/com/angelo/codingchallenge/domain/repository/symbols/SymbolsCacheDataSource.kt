package com.angelo.codingchallenge.domain.repository.symbols

import com.angelo.codingchallenge.data.model.Rates
import com.angelo.codingchallenge.data.model.SupportedSymbols

interface SymbolsCacheDataSource {
    suspend fun getSymbolsFromCache() : SupportedSymbols
    suspend fun saveSymbolsToCache(symbols : SupportedSymbols)
}