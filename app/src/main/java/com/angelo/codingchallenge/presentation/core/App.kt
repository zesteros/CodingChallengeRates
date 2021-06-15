package com.angelo.codingchallenge.presentation.core

import android.app.Application
import com.angelo.codingchallenge.BuildConfig
import com.angelo.codingchallenge.presentation.di.Injector
import com.angelo.codingchallenge.presentation.di.core.*
import com.angelo.codingchallenge.presentation.di.rates.RatesSubComponent
import com.angelo.codingchallenge.presentation.di.symbols.SymbolsSubComponent

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(applicationContext))
            .aPIModule(APIModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()

    }

    override fun createSymbolsSubComponent(): SymbolsSubComponent =
        appComponent.symbolsSubComponent().create()

    override fun createRatesSubComponent(): RatesSubComponent =
        appComponent.ratesSubComponent().create()
}