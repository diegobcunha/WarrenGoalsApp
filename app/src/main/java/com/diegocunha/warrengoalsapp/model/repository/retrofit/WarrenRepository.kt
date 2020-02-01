package com.diegocunha.warrengoalsapp.model.repository.retrofit

import android.content.SharedPreferences
import com.diegocunha.warrengoalsapp.model.data.login.LoginBody
import com.diegocunha.warrengoalsapp.model.data.login.LoginResponse
import com.diegocunha.warrengoalsapp.model.repository.storage.preferences.PreferencesRepository
import retrofit2.Response

class WarrenRepository(
    private val api: WarrenAPI,
    private val preferences: PreferencesRepository
) {

    suspend fun login(body: LoginBody): Response<LoginResponse> {
        return api.login(body)
    }
}