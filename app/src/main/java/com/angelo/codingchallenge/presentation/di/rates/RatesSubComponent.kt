package com.angelo.codingchallenge.presentation.di.rates

import androidx.appcompat.app.AppCompatActivity
import com.angelo.codingchallenge.presentation.di.symbols.SymbolsSubComponent
import com.angelo.codingchallenge.presentation.rates.RatesActivity
import dagger.Subcomponent
@RatesScope
@Subcomponent(modules = [RatesModule::class])
interface RatesSubComponent {
    fun inject(ratesActivity: RatesActivity)
    @Subcomponent.Factory
    interface Factory {
        fun create(): RatesSubComponent
    }
}