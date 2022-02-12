package com.desuzed.brewerytestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.desuzed.brewerytestapp.R
import com.desuzed.brewerytestapp.model.repo.RemoteDataSourceImpl
import com.desuzed.brewerytestapp.model.repo.RepoAppImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repo = RepoAppImpl(RemoteDataSourceImpl())
        lifecycleScope.launchWhenCreated {
            val rawResponse = repo.getBreweries()
            Log.i("TAG", "onCreate: $rawResponse")
        }

    }
}