package com.desuzed.brewerytestapp.ui.brewery_fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.desuzed.brewerytestapp.model.repo.RepoApp
import kotlinx.coroutines.launch

class BreweryViewModel(private val repo: RepoApp) : ViewModel() {
    private val _breweryLiveData = MutableLiveData<Brewery>()
    val breweryLiveData: LiveData<Brewery> = _breweryLiveData

    private val _breweriesListLiveData = MutableLiveData<List<Brewery>>()
    val breweriesListLiveData: LiveData<List<Brewery>> = _breweriesListLiveData

    fun fetchBreweriesList() = viewModelScope.launch {
        _breweriesListLiveData.value = repo.getBreweries()
    }

    fun fetchBrewery(id: String) = viewModelScope.launch {
        _breweryLiveData.value = repo.getBrewery(id)
    }
}