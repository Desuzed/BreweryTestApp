package com.desuzed.brewerytestapp.ui.brewery_fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.desuzed.brewerytestapp.model.pojo.Error
import com.desuzed.brewerytestapp.model.pojo.FetchedResult
import com.desuzed.brewerytestapp.model.repo.RepoApp
import com.desuzed.brewerytestapp.ui.Event
import kotlinx.coroutines.launch

class BreweryListViewModel(private val repo: RepoApp) : ViewModel() {
    private val _breweriesListLiveData = MutableLiveData<List<Brewery>>()
    val breweriesListLiveData: LiveData<List<Brewery>> = _breweriesListLiveData

    private val _errorLiveData = MutableLiveData<Event<Error>> ()
    val errorLiveData: LiveData<Event<Error>> = _errorLiveData

    fun fetchBreweriesList() = viewModelScope.launch {
        when (val fetchedResult = repo.getBreweries()) {
            is FetchedResult.Success -> _breweriesListLiveData.value = fetchedResult.returnedResult
            is FetchedResult.Fail -> _errorLiveData.value = Event(fetchedResult.failedResult)
        }

    }
}