package com.angelo.codingchallenge.domain.repository.rates

import com.angelo.codingchallenge.data.model.Rates

class RatesCacheDataSourceImpl : RatesCacheDataSource {

    private var rates = Rates("", ",", HashMap(), true, 0, null)

    override suspend fun getRatesFromCache(): Rates = rates

    override suspend fun saveRatesToCache(rates: Rates) {
        this.rates = rates
    }
}