package com.iwebsapp.living.ui.home

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.iwebsapp.living.R

class MainScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_scrolling)

        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (savedInstanceState == null) {
            callMainFragment()
            //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            //binding.setViewModel(viewModel)
            //binding.setLifecycleOwner(this)
        }
    }

    private fun callMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, HomeFragment.newInstance())
            .commitNow()
    }
}