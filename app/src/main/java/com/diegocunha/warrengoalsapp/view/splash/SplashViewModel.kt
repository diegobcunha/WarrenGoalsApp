package com.diegocunha.warrengoalsapp.view.splash

import com.arctouch.codechallenge.view.util.BaseViewModel
import com.diegocunha.warrengoalsapp.model.repository.storage.preferences.PreferencesRepository

class SplashViewModel(private val preferencesRepository: PreferencesRepository) : BaseViewModel() {

    val isAlreadySignedIn: Boolean = preferencesRepository.isAlreadySignedIn()
}