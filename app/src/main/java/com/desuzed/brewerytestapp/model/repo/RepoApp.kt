package com.desuzed.brewerytestapp.model.repo

import com.desuzed.brewerytestapp.model.ErrorProvider
import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.desuzed.brewerytestapp.model.pojo.Error
import com.desuzed.brewerytestapp.model.pojo.FetchedResult
import com.desuzed.brewerytestapp.model.retrofit.dto.BreweryMapper
import com.desuzed.brewerytestapp.model.retrofit.dto.ErrorMapper
import com.desuzed.brewerytestapp.model.retrofit.dto.ErrorRetrofitDto
import com.desuzed.brewerytestapp.model.retrofit.network.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RepoAppImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RepoApp {
    override suspend fun getBreweries(): FetchedResult<List<Brewery>, Error> =
        withContext(Dispatchers.IO) {
            when (val response = remoteDataSource.fetchBreweries()) {
                is NetworkResponse.Success -> {
                    val breweriesList = arrayListOf<Brewery>()
                    response.body.forEach { dto ->
                        breweriesList.add(BreweryMapper().mapFromEntity(dto))
                    }
                    FetchedResult.Success(breweriesList)
                }
                is NetworkResponse.ApiError -> apiError(response.body)
                is NetworkResponse.NetworkError -> networkError()
                is NetworkResponse.UnknownError -> unknownError()
            }
        }

    override suspend fun getBrewery(id: String): FetchedResult<Brewery, Error> =
        withContext(Dispatchers.IO) {
            when (val response = remoteDataSource.fetchBrewery(id)) {
                is NetworkResponse.Success -> FetchedResult.Success(
                    BreweryMapper().mapFromEntity(
                        response.body
                    )
                )
                is NetworkResponse.ApiError -> apiError(response.body)
                is NetworkResponse.NetworkError -> networkError()
                is NetworkResponse.UnknownError -> unknownError()
            }
        }

    override fun parseCode(code: Int): String = localDataSource.parseCode(code)


    private fun apiError(errorDto: ErrorRetrofitDto): FetchedResult.Fail<Error> =
        FetchedResult.Fail(ErrorMapper().mapFromEntity(errorDto), "API error")

    private fun networkError(): FetchedResult.Fail<Error> =
        FetchedResult.Fail(
            Error(parseCode(ErrorProvider.NETWORK_ERROR)),
            "Network error"
        )

    private fun unknownError(): FetchedResult.Fail<Error> =
        FetchedResult.Fail(
            Error(parseCode(ErrorProvider.UNKNOWN_ERROR)),
            "Unknown error"
        )


}

interface RepoApp : LocalDataSource {
    suspend fun getBreweries(): FetchedResult<List<Brewery>, Error>
    suspend fun getBrewery(id: String): FetchedResult<Brewery, Error>
}