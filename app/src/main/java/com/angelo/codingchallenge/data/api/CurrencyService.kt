package com.angelo.codingchallenge.data.api

import com.angelo.codingchallenge.data.model.Rates
import com.angelo.codingchallenge.data.model.SupportedSymbols
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("/symbols")
    suspend fun getSupportedSymbols(@Query("access_key") apiKey: String): Response<SupportedSymbols>

    @GET("/latest")
    suspend fun getLatestRates(
        @Query("access_key") apiKey: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String,
    ): Response<Rates>
}