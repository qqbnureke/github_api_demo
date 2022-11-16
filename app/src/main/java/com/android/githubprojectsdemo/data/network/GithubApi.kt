package com.android.githubprojectsdemo.data.network

import com.android.githubprojectsdemo.data.model.ResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/repositories?per_page=20&sort=stars")
    suspend fun getRepos(@Query("page") page: Int, @Query("q") query: String): Response<ResponseWrapper>
}