package com.iwebsapp.living.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.iwebsapp.living.data.repositories.UserRepository
import com.iwebsapp.living.util.ApiException
import com.iwebsapp.living.util.Coroutines
import com.iwebsapp.living.util.NoInternetException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    var phone: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View)  {
        authListener?.onStarted()
        if (phone.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Telefono o contraseña invalidos")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLoginLiving(phone!!, password!!)
                authResponse.data?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }
}