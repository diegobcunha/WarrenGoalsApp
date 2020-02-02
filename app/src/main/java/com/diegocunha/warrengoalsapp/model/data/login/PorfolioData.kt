package com.diegocunha.warrengoalsapp.model.data.login

import com.google.gson.annotations.SerializedName

data class PortfolioResponse(@SerializedName("portfolios") val portfolios: ArrayList<Goal>)

data class Goal(@SerializedName("_id") val id: String,
                @SerializedName("name") val name: String,
                @SerializedName("totalBalance") val totalBalance: Double,
                @SerializedName("goalAmount") val goalAmount: Double,
                @SerializedName("goalDate") val goalDate: String,
                @SerializedName("background") val background: Background)

data class Background(@SerializedName("thumb") val thumb: String,
                      @SerializedName("small") val small: String,
                      @SerializedName("regula") val regular: String)