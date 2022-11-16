package com.android.githubprojectsdemo.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.githubprojectsdemo.domain.model.RepoModel

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRepos(repos : List<RepoModel>)

    @Query("SELECT * FROM repository_table")
    suspend fun getRepos() : List<RepoModel>

    @Query("DELETE FROM repository_table")
    fun clear()
}