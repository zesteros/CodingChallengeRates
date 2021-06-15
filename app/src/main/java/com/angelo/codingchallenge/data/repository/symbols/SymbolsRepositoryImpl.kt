package com.angelo.codingchallenge.data.repository.symbols

import android.util.Log
import com.angelo.codingchallenge.data.model.Rates
import com.angelo.codingchallenge.data.model.SupportedSymbols
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsCacheDataSource
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsRemoteDataSource
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsRepository

class SymbolsRepositoryImpl(
    private val symbolsRemoteDataSource: SymbolsRemoteDataSource,
    private val symbolsCacheDataSource: SymbolsCacheDataSource
) : SymbolsRepository {

    private val TAG: String =
        SymbolsRepository::class.java.simpleName

    override suspend fun getSymbols(): SupportedSymbols? = getSymbolsFromCache()

    override suspend fun getSymbolsFromAPI(): SupportedSymbols {
        lateinit var symbols: SupportedSymbols
        try {
            val response = symbolsRemoteDataSource.getSupportedSymbols()
            val body = response.body()
            symbols = body ?: getSymbolsFromCache()
        } catch (exception: Exception) {
            Log.i(TAG, exception.message.toString())
            exception.printStackTrace()
            symbols = SupportedSymbols(true, HashMap(), null)
        }
        return symbols
    }

    override suspend fun getSymbolsFromCache(): SupportedSymbols {
        lateinit var symbols: SupportedSymbols
        try {
            var supportedSymbols = symbolsCacheDataSource.getSymbolsFromCache()
            if (supportedSymbols != null && supportedSymbols.symbols.isNotEmpty()) {
                symbols = supportedSymbols
            } else {
                symbols = getSymbolsFromAPI()
                symbolsCacheDataSource.saveSymbolsToCache(symbols)
            }
        } catch (exception: Exception) {
            Log.i(TAG, exception.message.toString())
            exception.printStackTrace()
            symbols = SupportedSymbols(true, HashMap(), null)
        }
        return symbols
    }


}