package com.diegocunha.warrengoalsapp.dependency


import androidx.preference.PreferenceManager
import com.diegocunha.warrengoalsapp.BuildConfig
import com.diegocunha.warrengoalsapp.model.repository.retrofit.WarrenAPI
import com.diegocunha.warrengoalsapp.model.repository.retrofit.WarrenRepository
import com.diegocunha.warrengoalsapp.model.repository.storage.preferences.PreferencesRepository
import com.diegocunha.warrengoalsapp.view.home.HomeViewModel
import com.diegocunha.warrengoalsapp.view.login.LoginViewModel
import com.diegocunha.warrengoalsapp.view.splash.SplashViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    factory {

        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    factory { PreferenceManager.getDefaultSharedPreferences(get()) }

    factory { GsonBuilder().create() }

    factory {
        val retrofit: Retrofit = get()
        retrofit.create(WarrenAPI::class.java)
    }

    factory {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single { PreferencesRepository(get()) }

    single { WarrenRepository(get(), get()) }

    viewModel { SplashViewModel(get()) }

    viewModel { LoginViewModel(get(), get()) }

    viewModel { HomeViewModel(get()) }


}