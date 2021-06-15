package com.angelo.codingchallenge.domain.repository.symbols

import com.angelo.codingchallenge.data.model.Rates
import com.angelo.codingchallenge.data.model.SupportedSymbols

interface SymbolsRepository {
    suspend fun getSymbols() : SupportedSymbols?
    suspend fun getSymbolsFromAPI() : SupportedSymbols
    suspend fun getSymbolsFromCache() : SupportedSymbols
}