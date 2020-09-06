package com.iwebsapp.living.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.iwebsapp.living.R
import com.iwebsapp.living.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        //val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        //val api = MyApi(networkConnectionInterceptor)
        //val db = AppDatabase(this)
        //val repository = UserRepository(api, db)
        //val factory = AuthViewModelFactory(repository)

        /*val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel*/

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

    }
}
