package com.android.githubprojectsdemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.githubprojectsdemo.data.utils.ConverterUtils
import com.android.githubprojectsdemo.domain.model.RepoModel

@Database(entities = [RepoModel::class], version = 1)
@TypeConverters(ConverterUtils::class)
abstract class Database : RoomDatabase() {

    abstract fun getRepoDAO(): RepoDao

}