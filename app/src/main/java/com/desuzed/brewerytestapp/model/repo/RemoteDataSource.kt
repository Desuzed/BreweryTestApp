package com.desuzed.brewerytestapp.model.repo

import com.desuzed.brewerytestapp.model.retrofit.BreweryService
import com.desuzed.brewerytestapp.model.retrofit.dto.BreweryDto
import com.desuzed.brewerytestapp.model.retrofit.dto.ErrorRetrofitDto
import com.desuzed.brewerytestapp.model.retrofit.network.NetworkResponse

class RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun fetchBreweries(): NetworkResponse<List<BreweryDto>, ErrorRetrofitDto> =
        BreweryService
            .getInstance()
            .fetchBreweriesList()

    override suspend fun fetchBrewery(id : String): NetworkResponse<BreweryDto, ErrorRetrofitDto> =
        BreweryService
            .getInstance()
            .fetchBrewery(id)
}

interface RemoteDataSource {
    suspend fun fetchBreweries(): NetworkResponse<List<BreweryDto>, ErrorRetrofitDto>
    suspend fun fetchBrewery(id : String): NetworkResponse<BreweryDto, ErrorRetrofitDto>
}