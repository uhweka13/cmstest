package com.beans.cmstest.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beans.cmstest.data.localdb.BeansEntity
import com.beans.cmstest.data.repository.BeanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BeansViewModel(private val repository: BeanRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(true) // Start as loading
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val beans: StateFlow<List<BeansEntity>> = repository.beans
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
        .also { flow ->
            viewModelScope.launch {
                flow.collect { beans ->
                    Log.d("BeanViewModel", "Beans updated: ${beans.size} items")
                    _isLoading.value = false
                }
            }
        }

    // Navigation event for item clicks
    private val _navigateToDetail = MutableStateFlow<Int?>(null)
    val navigateToDetail: StateFlow<Int?> = _navigateToDetail.asStateFlow()

    private val _beanById = MutableStateFlow<BeansEntity?>(null)
    val beanById: StateFlow<BeansEntity?> = _beanById.asStateFlow()

    fun getBeanById(id: Int) {
        Log.d("BeanViewModel", "Bean clicked get: ID=${id}")
        viewModelScope.launch {
            repository.getBeanById(id).collect { bean ->
                Log.d("BeanViewModel", "Bean by ID $id: ${bean?.flavorName ?: "Not found"}")
                _beanById.value = bean
            }
        }
    }

    fun onBeanClicked(beanId: Int) {
        Log.d("BeanViewModel", "Bean clicked: ID=$beanId")
        _navigateToDetail.value = beanId
    }

    fun onNavigatedToDetail() {
        Log.d("BeanViewModel", "Navigated to detail, clearing state")
        _navigateToDetail.value = null
    }
}