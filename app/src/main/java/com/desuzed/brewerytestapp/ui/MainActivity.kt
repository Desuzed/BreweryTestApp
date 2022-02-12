package com.desuzed.brewerytestapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.desuzed.brewerytestapp.App
import com.desuzed.brewerytestapp.R
import com.desuzed.brewerytestapp.model.repo.RemoteDataSourceImpl
import com.desuzed.brewerytestapp.model.repo.RepoAppImpl

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenCreated {
            val rawResponse = App.instance.getRepo().getBreweries()
            Log.i("TAG", "onCreate: $rawResponse")
        }

    }
}