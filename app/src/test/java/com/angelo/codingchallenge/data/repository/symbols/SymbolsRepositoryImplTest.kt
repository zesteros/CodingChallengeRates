package com.angelo.codingchallenge.data.repository.symbols

import android.util.Log
import com.angelo.codingchallenge.data.model.*
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsCacheDataSource
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsRemoteDataSource
import com.angelo.codingchallenge.domain.repository.symbols.SymbolsRepository
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.powermock.api.mockito.PowerMockito.mockStatic
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.Response

@RunWith(PowerMockRunner::class)
@PrepareForTest(SymbolsRepositoryImpl::class, Log::class)
class SymbolsRepositoryImplTest {

    private val symbolsRemoteDataSource: SymbolsRemoteDataSource =
        mock(SymbolsRemoteDataSource::class.java)
    private val symbolsCacheDataSource: SymbolsCacheDataSource =
        mock(SymbolsCacheDataSource::class.java)
    private lateinit var currencyRepository: SymbolsRepository

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        mockStatic(Log::class.java)
        Dispatchers.setMain(testDispatcher)
        currencyRepository =
            SymbolsRepositoryImpl(symbolsRemoteDataSource, symbolsCacheDataSource)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getSymbolFromAPI when response is not empty`() {
        val symbols = HashMap<String, String>()
        symbols["MXN"] = "Mexican Peso"
        symbols["USD"] = "US Dollar"
        val response = SupportedSymbols(true, symbols, null)
        symbolsRemoteDataSource.stub {
            onBlocking {
                getSupportedSymbols()
            }.thenReturn(Response.success(response)).then {
                runBlockingTest {
                    currencyRepository.getSymbolsFromAPI()
                    verify(symbolsRemoteDataSource).getSupportedSymbols()
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getSymbolFromAPI when response is empty`() {
        symbolsRemoteDataSource.stub {
            onBlocking {
                getSupportedSymbols()
            }.thenReturn(Response.error(404, ResponseBody.create(null, ""))).then {
                runBlockingTest {
                    currencyRepository.getSymbolsFromAPI()
                    verify(symbolsRemoteDataSource, never()).getSupportedSymbols()
                }
            }
        }
    }
}