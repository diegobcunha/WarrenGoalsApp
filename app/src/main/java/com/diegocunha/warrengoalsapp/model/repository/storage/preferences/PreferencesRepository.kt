package com.diegocunha.warrengoalsapp.model.repository.storage.preferences

import android.content.SharedPreferences

class PreferencesRepository(private val sharedPreferences: SharedPreferences) {

    private val ACCESS_TOKEN_KEY = "accessToken"

    fun saveAccessToken(accessToken: String) {
        sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply()
    }

    fun retrieveAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, "")
    }

    fun isAlreadySignedIn(): Boolean {
        return !retrieveAccessToken().isNullOrEmpty()
    }
}