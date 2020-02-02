package com.diegocunha.warrengoalsapp.view.goals

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.diegocunha.warrengoalsapp.databinding.GoalItemBinding
import com.diegocunha.warrengoalsapp.model.data.login.Goal
import com.diegocunha.warrengoalsapp.view.databinding.ReactiveAdapter

class GoalsAdapter:ReactiveAdapter<Goal, GoalItemBinding>() {

    override fun getBinding(context: Context, parent: ViewGroup, viewType: Int): GoalItemBinding {
        return GoalItemBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun bind(binding: GoalItemBinding, item: Goal) {
        val viewModel = GoalViewModel(item)
        binding.viewModel = viewModel
    }
}