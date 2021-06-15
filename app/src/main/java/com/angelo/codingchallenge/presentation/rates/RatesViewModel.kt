package com.angelo.codingchallenge.presentation.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.angelo.codingchallenge.domain.usecase.GetRatesUseCase

class RatesViewModel(
    private val ratesUseCase: GetRatesUseCase
) : ViewModel() {

    val ratesLiveData: LiveData<HashMap<String, Double>>
        get() = ratesCalculated
    private var ratesInitial = MutableLiveData<HashMap<String, Double>>()
    private var ratesCalculated = MutableLiveData<HashMap<String, Double>>()

    fun getRates(base: String, symbols: String) = liveData {
        val rates = ratesUseCase.execute(base, symbols)
        if(rates?.success == true)
            ratesInitial.value = HashMap(rates?.rates)
        emit(rates)
    }

    fun calculateRates(input: String) {
        val number = input.toDouble()
        ratesCalculated.value = HashMap(ratesInitial.value)
        ratesCalculated.value?.entries?.forEach { it.setValue(it.value.times(number)) }
    }
}
