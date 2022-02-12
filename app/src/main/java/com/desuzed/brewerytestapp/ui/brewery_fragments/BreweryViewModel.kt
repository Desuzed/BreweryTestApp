package com.desuzed.brewerytestapp.ui.brewery_fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.desuzed.brewerytestapp.model.repo.RepoApp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BreweryViewModel(private val repo: RepoApp) : ViewModel() {
    private val _breweriesListStateFlow = MutableStateFlow(emptyList<Brewery>())
    val breweriesListStateFlow: StateFlow<List<Brewery>> = _breweriesListStateFlow

    private val _breweryStateFlow = MutableStateFlow(Brewery.emptyBrewery())
    val breweryStateFlow: StateFlow<Brewery> = _breweryStateFlow

    fun fetchBreweriesList() = viewModelScope.launch {
        _breweriesListStateFlow.value = repo.getBreweries()
    }

    fun fetchBrewery(id: String) = viewModelScope.launch {
        _breweryStateFlow.value = repo.getBrewery(id)
    }
}