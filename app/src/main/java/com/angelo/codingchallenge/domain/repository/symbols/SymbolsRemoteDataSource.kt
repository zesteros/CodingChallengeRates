package com.angelo.codingchallenge.domain.repository.symbols

import com.angelo.codingchallenge.data.model.SupportedSymbols
import retrofit2.Response

interface SymbolsRemoteDataSource {
    suspend fun getSupportedSymbols() : Response<SupportedSymbols>
}