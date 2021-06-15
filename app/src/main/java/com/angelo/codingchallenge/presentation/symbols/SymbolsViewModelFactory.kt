package com.angelo.codingchallenge.presentation.symbols

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.angelo.codingchallenge.domain.usecase.GetSymbolsUseCase

class SymbolsViewModelFactory(
    private val symbolsUseCase: GetSymbolsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SymbolsViewModel(symbolsUseCase) as T
    }
}