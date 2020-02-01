package com.diegocunha.warrengoalsapp.view.splash

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegocunha.warrengoalsapp.model.repository.storage.preferences.PreferencesRepository
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    val sharedPreferences = mock<SharedPreferences>()


    @Test
    fun `Should move to Login Screen`() {
        val preferencesRepository = PreferencesRepository(sharedPreferences)
        Mockito.`when`(preferencesRepository.retrieveAccessToken()).thenReturn("")
        val viewModel = SplashViewModel(preferencesRepository)
        Assert.assertFalse(viewModel.isAlreadySignedIn)
    }
}