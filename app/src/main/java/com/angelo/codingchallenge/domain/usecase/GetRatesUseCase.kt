package com.angelo.codingchallenge.domain.usecase

import com.angelo.codingchallenge.data.model.Rates
import com.angelo.codingchallenge.domain.repository.rates.RatesRepository

class GetRatesUseCase(
    private val ratesRepository: RatesRepository
) {
    suspend fun execute(
        base: String,
        symbols: String
    ): Rates? = ratesRepository.getRates(base, symbols)
}