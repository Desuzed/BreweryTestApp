package com.desuzed.brewerytestapp.model.repo

import com.desuzed.brewerytestapp.model.retrofit.dto.BreweryDto
import com.desuzed.brewerytestapp.model.retrofit.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RepoAppImpl(
    private val remoteDataSource: RemoteDataSource
) : RepoApp {
    override suspend fun getBreweries(): List<BreweryDto> = withContext(Dispatchers.IO) {
        when (val response = remoteDataSource.fetchBreweries()){
            is NetworkResponse.Success -> response.body
            is NetworkResponse.ApiError -> TODO()
            is NetworkResponse.NetworkError -> TODO()
            is NetworkResponse.UnknownError -> TODO()
        }
    }
}

interface RepoApp {
    suspend fun getBreweries(): List<BreweryDto>
}