package com.diegocunha.warrengoalsapp.model

import com.diegocunha.warrengoalsapp.model.data.login.LoginBody
import com.diegocunha.warrengoalsapp.model.data.login.LoginResponse
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

val loginBody = LoginBody("email@email.com", "password")

val loginResponse = LoginResponse("asdf1234")

