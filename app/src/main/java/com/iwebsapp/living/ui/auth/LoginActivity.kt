package com.iwebsapp.living.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iwebsapp.living.MainActivity
import com.iwebsapp.living.R
import com.iwebsapp.living.data.db.AppDatabase
import com.iwebsapp.living.data.network.MyApi
import com.iwebsapp.living.data.network.NetworkConnectionInterceptor
import com.iwebsapp.living.data.repositories.UserRepository
import com.iwebsapp.living.databinding.ActivityLoginBinding
import com.iwebsapp.living.util.hide
import com.iwebsapp.living.util.show
import com.iwebsapp.living.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(data: String) {
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }

    override fun onMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
