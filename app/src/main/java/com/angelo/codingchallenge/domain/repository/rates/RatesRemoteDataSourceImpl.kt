package com.angelo.codingchallenge.domain.repository.rates

import com.angelo.codingchallenge.data.api.CurrencyService
import com.angelo.codingchallenge.data.model.Rates
import retrofit2.Response

class RatesRemoteDataSourceImpl(
    private val currencyService: CurrencyService,
    private val apiKey: String
) : RatesRemoteDataSource {
    override suspend fun getRates(
        base: String, symbols: String
    ): Response<Rates> =
        currencyService.getLatestRates(apiKey, base, symbols)
}