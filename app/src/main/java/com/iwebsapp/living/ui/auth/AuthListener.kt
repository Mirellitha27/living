package com.iwebsapp.living.ui.auth

interface AuthListener {
    fun onStarted()
    fun onSuccess(data: String)
    fun onFailure(message: String)
    fun onMain()
}