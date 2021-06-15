package com.angelo.codingchallenge.presentation.rates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angelo.codingchallenge.domain.usecase.GetRatesUseCase
import com.angelo.codingchallenge.presentation.symbols.SymbolsViewModel

class RatesViewModelFactory(
    private val ratesUseCase: GetRatesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RatesViewModel(ratesUseCase) as T
    }
}
