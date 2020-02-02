package com.diegocunha.warrengoalsapp.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.diegocunha.warrengoalsapp.model.data.login.Goal
import com.diegocunha.warrengoalsapp.model.repository.retrofit.WarrenRepository
import com.diegocunha.warrengoalsapp.view.util.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val warrenRepository: WarrenRepository) : BaseViewModel() {

    private val _portfolios = MutableLiveData<ArrayList<Goal>>()
    val portfolios: LiveData<ArrayList<Goal>> = _portfolios

    init {
        viewModelScope.launch {
            loadRepositories()
        }

    }

    private suspend fun loadRepositories() = withContext(Dispatchers.IO) {
        try {
            _isLoading.postValue(true)
            val response = warrenRepository.getPortfolios()

            if (response.isSuccessful) {
                _portfolios.postValue(response.body()?.portfolios)
            } else {
                _errorsRequest.postValue(true)
            }
        } catch (e: Exception) {
            _errorsRequest.postValue(true)
        } finally {
            _isLoading.postValue(false)
        }
    }
}