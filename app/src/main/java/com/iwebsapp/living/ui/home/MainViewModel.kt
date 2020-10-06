package com.iwebsapp.living.ui.home

import androidx.lifecycle.ViewModel
import com.iwebsapp.living.data.repositories.ServicesRepository
import com.iwebsapp.living.util.lazyDeferred

class MainViewModel(repository: ServicesRepository) : ViewModel() {
    val services by lazyDeferred {
        repository.getServices()
    }
}