package com.diegocunha.warrengoalsapp.repository.storage

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
class PreferencesRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val sharedPreferences = mock<SharedPreferences>()


    @Test
    fun `Should return true if already signed in`() {
        val repository = PreferencesRepository(sharedPreferences)
        Mockito.`when`(repository.retrieveAccessToken()).thenReturn("asdf1234")
        Assert.assertTrue(repository.isAlreadySignedIn())


    }

}