package com.angelo.codingchallenge.domain.usecase

import com.angelo.codingchallenge.data.model.SupportedSymbols
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsRepository

class GetSymbolsUseCase(private val currencyRepository: SymbolsRepository) {
    suspend fun execute() : SupportedSymbols? = currencyRepository.getSymbols()
}