package com.beans.cmstest.data.repository

import com.beans.cmstest.data.localdb.BeansEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("beans")
    suspend fun getBeans(
        @Query("pageIndex") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10
    ): ApiResponse
}

data class ApiResponse(
    val totalCount: Int,
    val pageSize: Int,
    val currentPage: Int,
    val totalPages: Int,
    val items: List<BeansEntity>
)