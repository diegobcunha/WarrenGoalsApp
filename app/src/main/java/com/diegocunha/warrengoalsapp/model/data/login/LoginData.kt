package com.diegocunha.warrengoalsapp.model.data.login

import com.google.gson.annotations.SerializedName

data class LoginBody(@SerializedName("email") val email: String,
                     @SerializedName("password") val password: String)

data class LoginResponse(@SerializedName("accessToken") val accessToken: String)