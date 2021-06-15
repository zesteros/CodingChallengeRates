package com.angelo.codingchallenge.presentation.di.core

import android.content.Context
import com.angelo.codingchallenge.presentation.di.rates.RatesSubComponent
import com.angelo.codingchallenge.presentation.di.symbols.SymbolsSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [SymbolsSubComponent::class, RatesSubComponent::class])
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext(): Context = context.applicationContext
}