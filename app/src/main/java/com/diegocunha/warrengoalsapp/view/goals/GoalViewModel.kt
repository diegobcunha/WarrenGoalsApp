package com.diegocunha.warrengoalsapp.view.goals

import com.diegocunha.warrengoalsapp.model.data.login.Goal

class GoalViewModel(private val goal: Goal) {

    val smallImage = goal.background.small

    val title = goal.name

    val goalDate = goal.goalDate
}