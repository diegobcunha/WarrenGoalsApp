package com.diegocunha.warrengoalsapp

import android.app.Application
import com.diegocunha.warrengoalsapp.dependency.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WarrenGoalsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WarrenGoalsApplication)
            modules(appModule)
        }
    }
}