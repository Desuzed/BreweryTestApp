package com.desuzed.brewerytestapp.model.pojo

sealed class FetchedResult<out S : Any, out F : Any> {
    class Success<S : Any>(val returnedResult: S) : FetchedResult<S, Nothing>()
    class Fail<F : Any>(val failedResult: F, val errorType: String) : FetchedResult<Nothing, F>()
}
