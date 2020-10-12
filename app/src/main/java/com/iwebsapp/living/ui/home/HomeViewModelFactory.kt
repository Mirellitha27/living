package com.iwebsapp.living.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iwebsapp.living.data.repositories.ServicesRepository

class HomeViewModelFactory(
    private val repository: ServicesRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}