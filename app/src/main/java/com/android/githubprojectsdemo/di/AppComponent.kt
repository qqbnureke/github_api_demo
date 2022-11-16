package com.android.githubprojectsdemo.di

import android.content.Context
import com.android.githubprojectsdemo.presentation.AppApplication
import com.android.githubprojectsdemo.presentation.MainActivity
import com.android.githubprojectsdemo.presentation.fragments.repo_list.RepoListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, ViewModelModule::class, RepoModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

    fun inject(repoListFragment: RepoListFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }
}