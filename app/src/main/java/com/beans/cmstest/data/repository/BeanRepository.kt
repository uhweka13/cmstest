package com.beans.cmstest.data.repository


import android.util.Log
import com.beans.cmstest.data.localdb.BeansDao
import com.beans.cmstest.data.localdb.BeansEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class BeanRepository(
    private val beansDao: BeansDao,
    private val apiService: ApiService
) {
    val beans: Flow<List<BeansEntity>> = flow {
        Log.e("beans_data", "apiBeans")
        // Emit cached data first
        emit(beansDao.getAllBeans().firstOrNull() ?: emptyList())
        // Fetch from API and update database
        try {
            val apiBeans = apiService.getBeans()
            Log.e("beans_data", "${apiBeans.items}")
            beansDao.insertPosts(apiBeans.items)
            emit(apiBeans.items)
        } catch (e: Exception) {
            Log.e("beans_data", "apiBeans")
            // Emit cached data on failure
            emit(beansDao.getAllBeans().firstOrNull() ?: emptyList())
        }
    }

    fun getBeanById(id: Int): Flow<BeansEntity?> {

        return flow {
            val bean = beansDao.getAllBeans().firstOrNull()?.find { it.beanId == id }
            Log.d("BeanRepository", "Bean by ID $id: ${bean?.flavorName ?: "Not found"}")
            emit(bean)
        }
    }
}