package com.iwebsapp.living.data.repositories

import com.iwebsapp.living.data.db.AppDatabase
import com.iwebsapp.living.data.network.MyApi
import com.iwebsapp.living.data.network.SafeApiRequest
import com.iwebsapp.living.data.network.responses.AuthResponse

class UserRepository(private val api: MyApi, private val db: AppDatabase) : SafeApiRequest() {

    suspend fun userLoginLiving(phone: String, password: String): AuthResponse {
        return apiRequest { api.loginliving(phone, password) }
    }

    //suspend fun saveToken(data: String) = db.getTokenDao().upsert(data)

    //fun getToken() = db.getTokenDao().getToken()

}