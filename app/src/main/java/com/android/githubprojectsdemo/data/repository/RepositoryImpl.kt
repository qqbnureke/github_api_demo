package com.android.githubprojectsdemo.data.repository

import com.android.githubprojectsdemo.data.db.Database
import com.android.githubprojectsdemo.data.model.unwrap
import com.android.githubprojectsdemo.data.network.GithubApi
import com.android.githubprojectsdemo.domain.Repository
import com.android.githubprojectsdemo.domain.model.RepoModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkApi: GithubApi, private val database: Database
) : Repository {
    override suspend fun getRepos(page: Int, query: String): List<RepoModel> {
        val repos = networkApi.getRepos(page, query.lowercase().ifEmpty { "android" }).unwrap()
        database.getRepoDAO().clear()
        database.getRepoDAO().addRepos(repos)
        return repos
    }

    override suspend fun getCachedRepos(): List<RepoModel> {
        return database.getRepoDAO().getRepos()
    }
}