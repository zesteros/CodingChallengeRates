package com.angelo.codingchallenge.data.repository.rates

import android.util.Log
import com.angelo.codingchallenge.data.model.Rates
import com.angelo.codingchallenge.domain.repository.rates.RatesCacheDataSource
import com.angelo.codingchallenge.domain.repository.rates.RatesRemoteDataSource
import com.angelo.codingchallenge.domain.repository.rates.RatesRepository

class RatesRepositoryImpl(
    private val ratesRemoteDataSource: RatesRemoteDataSource,
    private val ratesCacheDataSource: RatesCacheDataSource
) : RatesRepository {

    private val TAG: String =
        RatesRepository::class.java.simpleName

    override suspend fun getRates(base: String, symbols: String): Rates? =
        getRatesFromCache(base, symbols)

    override suspend fun getRatesFromAPI(base: String, symbols: String): Rates {
        lateinit var rates: Rates
        try {
            val response = ratesRemoteDataSource.getRates(base, symbols)
            val body = response.body()
            rates = body ?: getRatesFromCache(base, symbols)
        } catch (exception: Exception) {
            Log.i(TAG, exception.message.toString())
            exception.printStackTrace()
            rates = Rates("", "", HashMap(), true, 0, null)
        }
        return rates
    }

    override suspend fun getRatesFromCache(base: String, symbols: String): Rates {
        lateinit var rates: Rates
        try {
            var ratesFromCache = ratesCacheDataSource.getRatesFromCache()
            if (ratesFromCache.rates?.isNotEmpty() == true) {
                rates = ratesFromCache
            } else {
                rates = getRatesFromAPI(base, symbols)
                ratesCacheDataSource.saveRatesToCache(rates)
            }
        } catch (exception: Exception) {
            Log.i(TAG, exception.message.toString())
            exception.printStackTrace()
            rates = Rates("", "", HashMap(), true, 0, null)
        }
        return rates
    }
}