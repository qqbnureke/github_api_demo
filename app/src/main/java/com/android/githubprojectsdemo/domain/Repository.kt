package com.android.githubprojectsdemo.domain

import com.android.githubprojectsdemo.domain.model.RepoModel

interface Repository {
    suspend fun getRepos(page: Int, query: String): List<RepoModel>

    suspend fun getCachedRepos(): List<RepoModel>
}