package com.angelo.codingchallenge.presentation.symbols

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.angelo.codingchallenge.domain.usecase.GetSymbolsUseCase

class SymbolsViewModel(
    private val symbolsUseCase: GetSymbolsUseCase
) : ViewModel() {
    private lateinit var symbols: Set<String>

    private lateinit var symbolsMapOriginal: Map<String, String>
    private lateinit var symbolsMapSearch: Map<String, String>


    fun getSymbol() = liveData {
        val symbol = symbolsUseCase.execute()
        symbolsMapOriginal = symbol?.symbols!!
        emit(symbol)
    }

    fun searchInSymbols(input: String?) = liveData {
        symbolsMapSearch = HashMap(symbolsMapOriginal)
        if (input != null && input.isNotBlank())
            symbolsMapSearch =
                symbolsMapSearch.filter {
                    it.key.toLowerCase().contains(input.toLowerCase()) || it.value.toLowerCase()
                        .contains(input.toLowerCase())
                }
        emit(symbolsMapSearch)
    }

    fun setSymbolsList(keys: Set<String>) {
        this.symbols = keys
    }

    fun getSymbolsList(): String {
        return symbols.joinToString { it }
    }
}