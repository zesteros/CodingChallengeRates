package com.angelo.codingchallenge.data.repository.rates


import android.util.Log
import com.angelo.codingchallenge.data.model.*
import com.angelo.codingchallenge.domain.repository.rates.RatesCacheDataSource
import com.angelo.codingchallenge.domain.repository.rates.RatesRemoteDataSource
import com.angelo.codingchallenge.domain.repository.rates.RatesRepository
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
@PrepareForTest(RatesRepositoryImpl::class, Log::class)
class RatesRepositoryImplTest {

    private val ratesRemoteDataSource: RatesRemoteDataSource =
        mock(RatesRemoteDataSource::class.java)
    private val ratesCacheDataSource: RatesCacheDataSource =
        mock(RatesCacheDataSource::class.java)
    private lateinit var currencyRepository: RatesRepository

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        mockStatic(Log::class.java)
        Dispatchers.setMain(testDispatcher)
        currencyRepository =
            RatesRepositoryImpl(ratesRemoteDataSource, ratesCacheDataSource)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getRatesFromAPI when response is not empty`() {
        val rates = HashMap<String, String>()
        rates["MXN"] = "Mexican Peso"
        rates["USD"] = "US Dollar"
        val response = Rates("", "", HashMap(), true, 0, null)
        ratesRemoteDataSource.stub {
            onBlocking {
                getRates("EUR", "USD,MXN")
            }.thenReturn(Response.success(response)).then {
                runBlockingTest {
                    currencyRepository.getRatesFromAPI("EUR", "USD,MXN")
                    verify(ratesRemoteDataSource).getRates("EUR", "USD,MXN")
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getSymbolFromAPI when response is empty`() {
        ratesRemoteDataSource.stub {
            onBlocking {
                getRates("EUR", "USD,MXN")
            }.thenReturn(Response.error(404, ResponseBody.create(null, ""))).then {
                runBlockingTest {
                    currencyRepository.getRatesFromAPI("EUR", "USD,MXN")
                    verify(ratesRemoteDataSource, never()).getRates("EUR", "USD,MXN")
                }
            }
        }
    }
}
