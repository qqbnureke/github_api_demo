package com.android.githubprojectsdemo.di

import com.android.githubprojectsdemo.data.repository.RepositoryImpl
import com.android.githubprojectsdemo.domain.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepoModule {
    @Singleton
    @Binds
    fun bindRepository(baseRepositoryImpl: RepositoryImpl): Repository
}