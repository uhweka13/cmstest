package com.beans.cmstest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.beans.cmstest.data.repository.BeanRepository

class BeanViewModelFactory(private val repository: BeanRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeansViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeansViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}