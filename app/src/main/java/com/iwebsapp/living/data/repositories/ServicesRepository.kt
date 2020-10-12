package com.iwebsapp.living.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iwebsapp.living.data.db.AppDatabase
import com.iwebsapp.living.data.db.entities.Services
import com.iwebsapp.living.data.network.MyApi
import com.iwebsapp.living.data.network.SafeApiRequest
import com.iwebsapp.living.data.preferences.PreferenceProvider
import com.iwebsapp.living.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL = 6

class ServicesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

    private val services = MutableLiveData<List<Services>>()

    init {
        services.observeForever {
            saveServices(it)
        }
    }

    suspend fun getServices(): LiveData<List<Services>> {
        return withContext(Dispatchers.IO) {
            fetchServices()
            db.getServicesDao().getServices()
        }
    }

    private suspend fun fetchServices() {
        val lastSaveAt = prefs.getLastSaveAt()

        if (lastSaveAt == null || isFetchNeeded(LocalDateTime.parse(lastSaveAt))) {
            try {
                //5f129238c759070794bdbef3
                val response = apiRequest { api.getServices( "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXJyZW50Ijp7Il9pZCI6IjVmMjRhMzcyODc0NzRhMjZlZTBjMWEyNCIsInJvbCI6IlNldHRsZXIifSwiaWF0IjoxNjAyNDc2MDEzfQ.fkxqpr90SCmeP-j8avTvUmlcpy7GrDG9SgPfiX0DuL0") }
                Log.d("ServicesRepository", "data: " + response.data)
                services.postValue(response.data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveServices(services: List<Services>) {
        Coroutines.io {
            prefs.savelastSaveAt(LocalDateTime.now().toString())
            db.getServicesDao().saveAllService(services)
        }

    }
}