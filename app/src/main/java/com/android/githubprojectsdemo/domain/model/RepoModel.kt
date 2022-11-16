package com.android.githubprojectsdemo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "repository_table")
data class RepoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val repoName: String,
    val owner: String,
    val starCount: Int,
    val url: String,
    val description: String,
    var createdDate: Date,
    var updatedDate: Date,
    var topicList: List<String>
) : Serializable