package com.angelo.codingchallenge.domain.repository.rates

import com.angelo.codingchallenge.data.model.Rates
import retrofit2.Response

interface RatesRemoteDataSource {
    suspend fun getRates(base: String, symbols: String): Response<Rates>
}