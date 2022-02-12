package com.desuzed.brewerytestapp.model.retrofit.dto

import com.desuzed.brewerytestapp.model.EntityMapper

import com.google.gson.annotations.SerializedName
import com.desuzed.brewerytestapp.model.pojo.Error

class ErrorRetrofitDto {
    @SerializedName("status")
    var status: String = ""

    @SerializedName("code")
    var code: String = ""

    @SerializedName("message")
    var message: String = ""
}

class ErrorMapper : EntityMapper<ErrorRetrofitDto, Error> {
    override fun mapFromEntity(entity: ErrorRetrofitDto): Error  =
        Error(entity.status, entity.code, entity.message)
}
