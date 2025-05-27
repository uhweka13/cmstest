package com.beans.cmstest.data.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BeansDao {
    @Query("SELECT * FROM beans")
    fun getAllBeans(): Flow<List<BeansEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<BeansEntity>)
}