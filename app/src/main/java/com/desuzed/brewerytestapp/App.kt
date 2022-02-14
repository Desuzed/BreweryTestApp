package com.desuzed.brewerytestapp

import android.app.Application
import com.desuzed.brewerytestapp.model.ErrorProviderImpl
import com.desuzed.brewerytestapp.model.repo.LocalDataSourceImpl
import com.desuzed.brewerytestapp.model.repo.RemoteDataSourceImpl
import com.desuzed.brewerytestapp.model.repo.RepoApp
import com.desuzed.brewerytestapp.model.repo.RepoAppImpl

class App : Application() {
    private val repositoryApp by lazy {
        RepoAppImpl(
            RemoteDataSourceImpl(),
            LocalDataSourceImpl(ErrorProviderImpl(resources))
        )
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getRepo(): RepoApp = repositoryApp

    companion object {
        lateinit var instance: App
            private set
    }
}