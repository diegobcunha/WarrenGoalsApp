package com.diegocunha.warrengoalsapp.view.login

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.warrengoalsapp.helper.assertLiveDataEquals
import com.diegocunha.warrengoalsapp.model.loginResponse
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
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    val api = mock<WarrenAPI>()
    val sharedPreferences = mock<SharedPreferences>()
    val sharedPreferencesEditor = mock<SharedPreferences.Editor>()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        Mockito.`when`(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        Mockito.`when`(sharedPreferencesEditor.putString(anyString(), anyString()))
            .thenReturn(sharedPreferencesEditor)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `Should enable login button`() {
        val preferencesRepository = PreferencesRepository(sharedPreferences)
        val apiRepository = WarrenRepository(api, preferencesRepository)
        val viewModel = LoginViewModel(apiRepository, preferencesRepository)
        viewModel.userNameData.postValue("bbb@email.com")
        viewModel.passwordData.postValue("aaa")
        assertLiveDataEquals(true, viewModel.loginEnableMediator)
    }

    @Test
    fun `Should perform login successful`() = runBlocking {
        val preferencesRepository = PreferencesRepository(sharedPreferences)
        val apiRepository = WarrenRepository(api, preferencesRepository)


        Mockito.`when`(api.login(any())).thenReturn(Response.success(loginResponse))
        val viewModel = LoginViewModel(apiRepository, preferencesRepository)
        viewModel.userName = "aaa"
        viewModel.password = "bbb"

        withContext(Dispatchers.IO) {
            viewModel.performLogin()
            assertLiveDataEquals(false, viewModel.errorRequest)
        }
    }

    @Test
    fun `Should show error information`() = runBlocking {
        val preferencesRepository = PreferencesRepository(sharedPreferences)
        val apiRepository = WarrenRepository(api, preferencesRepository)

        Mockito.`when`(api.login(any())).thenReturn(
            Response.error(
                400, ResponseBody.create(
                    MediaType.get("application/json"), "{error: not fund}"
                )
            )
        )

        val viewModel = LoginViewModel(apiRepository, preferencesRepository)
        withContext(Dispatchers.IO) {
            viewModel.performLogin()
            assertLiveDataEquals(true, viewModel.errorRequest)
        }
    }

    @Test
    fun `Should save access token`() = runBlocking {
        val preferencesRepository = PreferencesRepository(sharedPreferences)
        val apiRepository = WarrenRepository(api, preferencesRepository)

        Mockito.`when`(api.login(any())).thenReturn(Response.success(loginResponse))
        Mockito.`when`(preferencesRepository.retrieveAccessToken())
            .thenReturn(loginResponse.accessToken)
        val viewModel = LoginViewModel(apiRepository, preferencesRepository)
        withContext(Dispatchers.IO) {
            viewModel.performLogin()
            Assert.assertEquals(
                loginResponse.accessToken,
                preferencesRepository.retrieveAccessToken()
            )
        }
    }

}