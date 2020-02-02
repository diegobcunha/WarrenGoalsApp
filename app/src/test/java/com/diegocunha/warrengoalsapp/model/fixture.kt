package com.diegocunha.warrengoalsapp.model

import com.diegocunha.warrengoalsapp.model.data.login.*

val loginBody = LoginBody("email@email.com", "password")

val loginResponse = LoginResponse("asdf1234")

val backGround = Background("thumb", "small", "regular")

val goal = Goal("id", "name", 0.00, 0.00, "date", backGround)

val portfolioResponse = PortfolioResponse(arrayListOf(goal))



