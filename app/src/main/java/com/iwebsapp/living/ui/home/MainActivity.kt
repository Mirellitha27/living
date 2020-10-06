package com.iwebsapp.living.ui.home

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.iwebsapp.living.R
import com.iwebsapp.living.data.db.AppDatabase
import com.iwebsapp.living.data.db.entities.Services
import com.iwebsapp.living.data.network.MyApi
import com.iwebsapp.living.data.network.NetworkConnectionInterceptor
import com.iwebsapp.living.data.preferences.PreferenceProvider
import com.iwebsapp.living.data.repositories.ServicesRepository
import com.iwebsapp.living.databinding.ActivityMainBinding
import com.iwebsapp.living.util.Coroutines
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.content_scrolling.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    //private val prefs: PreferenceProvider
    override val kodein by kodein()
    private val factory: MainViewModelFactory by instance()
    //lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel
    private val prefe: PreferenceProvider
        get() {
            TODO()
        }

    /*companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding.activityViewModel = mainActivityViewModel
        //binding.lifecycleOwner = this
        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = ServicesRepository(api, db, prefe)
        val factory = MainViewModelFactory(repository)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        //binding.service = viewModel

        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        /*findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        viewModel.services.await().observe(this, Observer {
            initRecyclerView(it.toServiceItem())

        })
    }

    private fun initRecyclerView(serviceItem: List<ServicesItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(serviceItem)
        }

        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<Services>.toServiceItem() : List<ServicesItem> {
        return this.map {
            ServicesItem(it)
        }
    }

}