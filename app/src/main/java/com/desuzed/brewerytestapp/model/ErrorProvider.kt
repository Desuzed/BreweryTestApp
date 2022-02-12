package com.desuzed.brewerytestapp.model

import android.content.res.Resources
import com.desuzed.brewerytestapp.model.ErrorProvider.Companion.NETWORK_ERROR

class ErrorProviderImpl(private val resources: Resources) : ErrorProvider {
    override fun parseCode(code: Int): String {
        return when (code) {
            NETWORK_ERROR -> ""
            else -> {
                ""
            }
        }
    }
}

interface ErrorProvider {
    fun parseCode(code: Int): String

    companion object {
        const val NETWORK_ERROR = 10
    }
}

