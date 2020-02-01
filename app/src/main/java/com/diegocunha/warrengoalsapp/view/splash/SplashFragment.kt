package com.diegocunha.warrengoalsapp.view.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arctouch.codechallenge.view.util.BaseFragment
import com.diegocunha.warrengoalsapp.R
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e( "SplashFragment", "Move to login")
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!viewModel.isAlreadySignedIn) {
            Log.e( "SplashFragment", "Move to login")
        }
    }
}