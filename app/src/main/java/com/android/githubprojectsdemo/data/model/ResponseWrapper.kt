package com.android.githubprojectsdemo.data.model

import com.android.githubprojectsdemo.domain.model.RepoModel
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import java.util.*

data class ResponseWrapper(val items: List<RepoDto>)

data class RepoDto(
    val name: String,
    val owner: OwnerDto,
    @SerializedName("stargazers_count")
    val starCount: Int,
    @SerializedName("html_url")
    val url: String,
    val description: String?,
    @SerializedName("created_at")
    val createdDate: Date,
    @SerializedName("updated_at")
    val updatedDate: Date,
    val topics: List<String>
)

data class OwnerDto(val login: String)

class NetworkException(val code: Int, message: String?) : Exception(message)

fun Response<ResponseWrapper>.unwrap(): List<RepoModel> {
    if (isSuccessful && body() != null) {
        val responseWrapper = body() as ResponseWrapper
        val repoList = ArrayList<RepoModel>()
        responseWrapper.items.forEach { dto ->
            repoList.add(
                RepoModel(
                    0,
                    dto.name,
                    dto.owner.login,
                    dto.starCount,
                    dto.url,
                    dto.description ?: "",
                    dto.createdDate,
                    dto.updatedDate,
                    dto.topics
                )
            )
        }
        return repoList
    } else {
        throw NetworkException(this.code(), this.errorBody().toString())
    }
}