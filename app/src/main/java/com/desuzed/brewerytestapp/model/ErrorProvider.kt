package com.desuzed.brewerytestapp.model

import android.content.res.Resources
import com.desuzed.brewerytestapp.R
import com.desuzed.brewerytestapp.model.ErrorProvider.Companion.API_ERROR
import com.desuzed.brewerytestapp.model.ErrorProvider.Companion.NETWORK_ERROR
import com.desuzed.brewerytestapp.model.ErrorProvider.Companion.UNKNOWN_ERROR

class ErrorProviderImpl(private val resources: Resources) : ErrorProvider {
    override fun parseCode(code: Int): String {
        return when (code) {
            NETWORK_ERROR -> resources.getString(R.string.network_error)
            API_ERROR -> resources.getString(R.string.api_error)
            UNKNOWN_ERROR -> resources.getString(R.string.unknown_error)
            else -> resources.getString(R.string.unknown_error)
        }
    }
}

interface ErrorProvider {
    fun parseCode(code: Int): String

    companion object {
        const val NETWORK_ERROR = 1
        const val API_ERROR = 2
        const val UNKNOWN_ERROR = 3
    }
}

