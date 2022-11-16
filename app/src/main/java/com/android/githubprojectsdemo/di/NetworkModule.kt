package com.android.githubprojectsdemo.di

import com.android.githubprojectsdemo.data.network.GithubApi
import com.android.githubprojectsdemo.data.repository.RepositoryImpl
import com.android.githubprojectsdemo.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesFakerAPI(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }
}