package com.angelo.codingchallenge.presentation.di.symbols

import androidx.appcompat.app.AppCompatActivity
import com.angelo.codingchallenge.presentation.symbols.SymbolsActivity
import dagger.Subcomponent

@SymbolsScope
@Subcomponent(modules = [SymbolsModule::class])
interface SymbolsSubComponent {
    fun inject(mainActivity: SymbolsActivity)
    @Subcomponent.Factory
    interface Factory {
        fun create(): SymbolsSubComponent
    }
}