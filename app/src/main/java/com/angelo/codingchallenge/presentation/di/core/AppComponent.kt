package com.angelo.codingchallenge.presentation.di.core

import com.angelo.codingchallenge.presentation.di.rates.RatesSubComponent
import com.angelo.codingchallenge.presentation.di.symbols.SymbolsSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    APIModule::class,
    CacheDataModule::class,
    RemoteDataModule::class,
    RepositoryModule::class,
    UseCaseModule::class])
interface AppComponent {
    fun symbolsSubComponent() : SymbolsSubComponent.Factory
    fun ratesSubComponent() : RatesSubComponent.Factory
}