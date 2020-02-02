package com.diegocunha.warrengoalsapp.view.home

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.warrengoalsapp.helper.assertLiveDataEquals
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
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    val api = mock<WarrenAPI>()
    val sharedPreferences = mock<SharedPreferences>()
    val preferencesRepository = PreferencesRepository(sharedPreferences)
    private val warrenRepository = WarrenRepository(api, preferencesRepository)

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
    fun `Should request list of goals`() = runBlocking {
        Mockito.`when`(sharedPreferences.getString(any(), any())).thenReturn("accessToken")
        Mockito.`when`(warrenRepository.getPortfolios()).thenReturn(Response.success(portfolioResponse))

        val viewModel = HomeViewModel(warrenRepository)
        withContext(Dispatchers.IO) {
            assertLiveDataEquals(portfolioResponse.portfolios, viewModel.portfolios)
        }

    }

}