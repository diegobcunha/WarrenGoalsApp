package com.diegocunha.warrengoalsapp.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.diegocunha.warrengoalsapp.databinding.FragmentHomeBinding
import com.diegocunha.warrengoalsapp.view.goals.GoalsAdapter
import com.diegocunha.warrengoalsapp.view.util.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val goalsAdapter = GoalsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.toolbar.apply {
            title = "Warren App Goals"
        }

        binding.goalsRecyclerView.adapter = goalsAdapter

        viewModel.portfolios.observe(viewLifecycleOwner, Observer {
            goalsAdapter.setItems(it)
        })

        return binding.root
    }
}