package com.diegocunha.warrengoalsapp.repository.retrofit

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.warrengoalsapp.model.data.login.LoginResponse
import com.diegocunha.warrengoalsapp.model.data.login.PortfolioResponse
import com.diegocunha.warrengoalsapp.model.loginBody
import com.diegocunha.warrengoalsapp.model.loginResponse
import com.diegocunha.warrengoalsapp.model.portfolioResponse
import com.diegocunha.warrengoalsapp.model.repository.retrofit.WarrenAPI
import com.diegocunha.warrengoalsapp.model.repository.retrofit.WarrenRepository
import com.diegocunha.warrengoalsapp.model.repository.storage.preferences.PreferencesRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WarrenRepositoryTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val api = mock<WarrenAPI>()
    private val sharedPreferences = mock<SharedPreferences>()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Should perform successful login`() = runBlocking {
        Mockito.`when`(api.login(any())).thenReturn(Response.success(loginResponse))
        val preferencesRepository = PreferencesRepository(sharedPreferences)
        val repository = WarrenRepository(api, preferencesRepository)
        withContext(Dispatchers.IO) {
            val response = repository.login(loginBody)
            Assert.assertEquals(response.body(), loginResponse)
        }
    }

    @Test
    fun `Should return an error at login`() = runBlocking {
        val responseError = Response.error<LoginResponse>(400, ResponseBody
                .create(MediaType
                        .get("application/json"),
                        "{error: wrong login or password}"))
        Mockito.`when`(api.login(any())).thenReturn(responseError)
        val preferencesRepository = PreferencesRepository(sharedPreferences)
        val repository = WarrenRepository(api, preferencesRepository)
        withContext(Dispatchers.IO) {
            val response = repository.login(loginBody)
            Assert.assertFalse(response.isSuccessful)
        }
    }

    @Test
    fun `Should return a list of portfolios`() = runBlocking {
        Mockito.`when`(api.getPortfolios(any())).thenReturn(Response.success(portfolioResponse))
        Mockito.`when`(sharedPreferences.getString(any(), any())).thenReturn("accessToken")
        val preferencesRepository = PreferencesRepository(sharedPreferences)
        val repository = WarrenRepository(api, preferencesRepository)
        withContext(Dispatchers.IO) {
            val response = repository.getPortfolios()
            Assert.assertEquals(response.body(), portfolioResponse)
        }
    }

    @Test
    fun `Should return an error at Portfolios`() = runBlocking {
        val responseError = Response.error<PortfolioResponse>(400, ResponseBody
                .create(MediaType
                        .get("application/json"),
                        "{error: error on request portfolios}"))

        Mockito.`when`(api.getPortfolios(any())).thenReturn(responseError)
        Mockito.`when`(sharedPreferences.getString(any(), any())).thenReturn("accessToken")

        val preferencesRepository = PreferencesRepository(sharedPreferences)
        val repository = WarrenRepository(api, preferencesRepository)

        withContext(Dispatchers.IO) {
            val response = repository.getPortfolios()
            Assert.assertFalse(response.isSuccessful)
        }
    }
}