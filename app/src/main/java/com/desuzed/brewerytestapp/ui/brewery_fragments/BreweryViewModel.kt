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

class BreweryViewModel(private val repo: RepoApp) : ViewModel() {
    private val _breweryLiveData = MutableLiveData<Brewery>()
    val breweryLiveData: LiveData<Brewery> = _breweryLiveData

    private val _errorLiveData = MutableLiveData<Event<Error>>()
    val errorLiveData: LiveData<Event<Error>> = _errorLiveData


    fun fetchBrewery(id: String) = viewModelScope.launch {
        when (val fetchedResult = repo.getBrewery(id)) {
            is FetchedResult.Success -> _breweryLiveData.value = fetchedResult.returnedResult
            is FetchedResult.Fail -> _errorLiveData.value = Event(fetchedResult.failedResult)
        }
    }
}