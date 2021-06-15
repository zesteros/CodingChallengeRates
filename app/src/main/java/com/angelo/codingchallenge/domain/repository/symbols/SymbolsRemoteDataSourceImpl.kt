package com.angelo.codingchallenge.domain.repository.symbols

import com.angelo.codingchallenge.data.api.CurrencyService
import com.angelo.codingchallenge.data.model.SupportedSymbols
import retrofit2.Response

class SymbolsRemoteDataSourceImpl(
    private val currencyService: CurrencyService,
    private val apiKey: String
) : SymbolsRemoteDataSource {
    override suspend fun getSupportedSymbols(): Response<SupportedSymbols> =
        currencyService.getSupportedSymbols(apiKey)
}