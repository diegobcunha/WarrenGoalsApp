package com.diegocunha.warrengoalsapp.view.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.diegocunha.warrengoalsapp.model.data.login.LoginBody
import com.diegocunha.warrengoalsapp.model.repository.retrofit.WarrenRepository
import com.diegocunha.warrengoalsapp.model.repository.storage.preferences.PreferencesRepository
import com.diegocunha.warrengoalsapp.view.util.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val warrenRepository: WarrenRepository,
    private val preferencesRepository: PreferencesRepository
) : BaseViewModel() {

    val loginEnableMediator = MediatorLiveData<Boolean>()
    val userNameData = MutableLiveData<String>()
    val passwordData = MutableLiveData<String>()
    var userName = ""
    var password = ""


    init {
        loginEnableMediator.postValue(false)
        loginEnableMediator.addSource(userNameData) {
            userName = it
            onFormChanged()
        }

        loginEnableMediator.addSource(passwordData) {
            password = it
            onFormChanged()
        }
    }

    override fun onCleared() {
        super.onCleared()

        loginEnableMediator.removeSource(userNameData)
        loginEnableMediator.removeSource(passwordData)
    }


    suspend fun performLogin() = withContext(Dispatchers.IO) {
        val login = userName
        val password = password

        val loginBody = LoginBody(login, password)

        try {
            _isLoading.postValue(true)

            val response = warrenRepository.login(loginBody)

            if (response.isSuccessful) {
                _errorsRequest.postValue(false)
                response.body()?.let { loginResponse ->
                    preferencesRepository.saveAccessToken(loginResponse.accessToken)
                }
            } else {
                _errorsRequest.postValue(true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _errorsRequest.postValue(true)
        } finally {
            _isLoading.postValue(false)
        }
    }


    private fun onFormChanged() {
        loginEnableMediator.postValue(!userName.isNullOrEmpty() && !password.isNullOrEmpty())
    }


}