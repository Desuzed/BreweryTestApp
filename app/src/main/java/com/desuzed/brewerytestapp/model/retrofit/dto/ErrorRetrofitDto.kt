package com.desuzed.brewerytestapp.model.retrofit.dto

import com.desuzed.brewerytestapp.model.EntityMapper
import com.desuzed.brewerytestapp.model.pojo.Error
import com.google.gson.annotations.SerializedName

class ErrorRetrofitDto {
    @SerializedName("message")
    var message: String? = null
}

class ErrorMapper : EntityMapper<ErrorRetrofitDto, Error> {
    override fun mapFromEntity(entity: ErrorRetrofitDto) = Error(entity.message)
}
