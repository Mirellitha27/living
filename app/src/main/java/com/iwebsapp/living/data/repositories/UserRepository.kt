package com.iwebsapp.living.data.repositories

import com.iwebsapp.living.data.db.AppDatabase
import com.iwebsapp.living.data.network.MyApi
import com.iwebsapp.living.data.network.SafeApiRequest

class UserRepository(private val api: MyApi, private val db: AppDatabase) : SafeApiRequest() {
}