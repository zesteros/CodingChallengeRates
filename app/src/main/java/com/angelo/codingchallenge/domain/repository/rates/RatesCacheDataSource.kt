package com.angelo.codingchallenge.domain.repository.rates

import com.angelo.codingchallenge.data.model.Rates

interface RatesCacheDataSource {
    suspend fun getRatesFromCache() : Rates
    suspend fun saveRatesToCache(rates: Rates)
}