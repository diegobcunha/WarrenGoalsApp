package com.diegocunha.warrengoalsapp.model.repository.retrofit

import com.diegocunha.warrengoalsapp.model.data.login.LoginBody
import com.diegocunha.warrengoalsapp.model.data.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WarrenAPI {

    @POST("account/login")
    suspend fun login(@Body loginBody: LoginBody): Response<LoginResponse>
}