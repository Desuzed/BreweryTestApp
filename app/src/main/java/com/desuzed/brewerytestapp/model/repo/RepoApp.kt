package com.desuzed.brewerytestapp.model.repo

import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.desuzed.brewerytestapp.model.retrofit.dto.BreweryMapper
import com.desuzed.brewerytestapp.model.retrofit.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RepoAppImpl(
    private val remoteDataSource: RemoteDataSource
) : RepoApp {
    override suspend fun getBreweries(): List<Brewery> = withContext(Dispatchers.IO) {
        when (val response = remoteDataSource.fetchBreweries()) {
            is NetworkResponse.Success -> {
                val breweriesList = arrayListOf<Brewery>()
                response.body.forEach { dto ->
                    breweriesList.add(BreweryMapper().mapFromEntity(dto))
                }
                breweriesList
            }
            is NetworkResponse.ApiError -> TODO()
            is NetworkResponse.NetworkError -> TODO()
            is NetworkResponse.UnknownError -> TODO()
        }
    }

    override suspend fun getBrewery(id: String): Brewery = withContext(Dispatchers.IO) {
        when (val response = remoteDataSource.fetchBrewery(id)) {
            is NetworkResponse.Success -> BreweryMapper().mapFromEntity(response.body)
            is NetworkResponse.ApiError -> TODO()
            is NetworkResponse.NetworkError -> TODO()
            is NetworkResponse.UnknownError -> TODO()
        }
    }
}

interface RepoApp {
    suspend fun getBreweries(): List<Brewery>
    suspend fun getBrewery(id: String): Brewery
}