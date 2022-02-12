package com.desuzed.brewerytestapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.desuzed.brewerytestapp.model.repo.RepoApp
import com.desuzed.brewerytestapp.ui.brewery_fragments.BreweryViewModel


class ViewModelFactory(private val repositoryApp: RepoApp) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(BreweryViewModel::class.java) -> {
                BreweryViewModel(repositoryApp) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}