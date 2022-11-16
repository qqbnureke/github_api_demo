package com.android.githubprojectsdemo.presentation

import android.app.Application
import com.android.githubprojectsdemo.di.AppComponent
import com.android.githubprojectsdemo.di.DaggerAppComponent

class AppApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
    }
}