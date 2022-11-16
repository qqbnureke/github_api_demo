package com.android.githubprojectsdemo.di

import androidx.lifecycle.ViewModel
import com.android.githubprojectsdemo.presentation.fragments.repo_list.RepoListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @ClassKey(RepoListViewModel::class)
    @IntoMap
    abstract fun bindRepoViewModel(repoViewModel: RepoListViewModel): ViewModel
}