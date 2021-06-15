package com.angelo.codingchallenge.domain.repository.rates

import com.angelo.codingchallenge.data.model.Rates

interface RatesRepository {
    suspend fun getRates(base: String, symbols: String): Rates?
    suspend fun getRatesFromAPI(base: String, symbols: String): Rates
    suspend fun getRatesFromCache(base: String, symbols: String): Rates
}