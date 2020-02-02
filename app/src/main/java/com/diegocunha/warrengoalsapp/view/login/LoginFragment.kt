package com.diegocunha.warrengoalsapp.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.diegocunha.warrengoalsapp.R
import com.diegocunha.warrengoalsapp.databinding.FragmentLoginBinding
import com.diegocunha.warrengoalsapp.view.util.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.loginEnableMediator.observe(viewLifecycleOwner, Observer {
            binding.loginButton.isEnabled = it
        })

        binding.loginButton.setOnClickListener {
            lifecycleScope.launch {
                hideKeyboard()
                viewModel.performLogin()
            }
        }

        viewModel.errorRequest.observe(viewLifecycleOwner, Observer {
            if (it) {
                Snackbar.make(binding.container, R.string.error_login, Snackbar.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        })


        return binding.root
    }
}