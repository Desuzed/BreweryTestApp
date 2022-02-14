package com.desuzed.brewerytestapp.model.repo

import com.desuzed.brewerytestapp.model.ErrorProvider

class LocalDataSourceImpl(private val errorProvider: ErrorProvider) : LocalDataSource {

    override fun parseCode(code: Int): String = errorProvider.parseCode(code)

}

interface LocalDataSource : ErrorProvider