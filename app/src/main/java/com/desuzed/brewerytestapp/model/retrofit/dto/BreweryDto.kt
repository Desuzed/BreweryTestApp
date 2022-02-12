package com.desuzed.brewerytestapp.model.retrofit.dto

import com.desuzed.brewerytestapp.model.EntityMapper
import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.google.gson.annotations.SerializedName

data class BreweryDto(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("brewery_type") var breweryType: String = "",
    @SerializedName("street") var street: String = "",
    @SerializedName("address_2") var address2: String = "",
    @SerializedName("address_3") var address3: String = "",
    @SerializedName("city") var city: String = "",
    @SerializedName("state") var state: String = "",
    @SerializedName("county_province") var countyProvince: String = "",
    @SerializedName("postal_code") var postalCode: String = "",
    @SerializedName("country") var country: String = "",
    @SerializedName("longitude") var longitude: String = "",
    @SerializedName("latitude") var latitude: String = "",
    @SerializedName("phone") var phone: String = "",
    @SerializedName("website_url") var websiteUrl: String = "",
    @SerializedName("updated_at") var updatedAt: String = "",
    @SerializedName("created_at") var createdAt: String = ""
)

class BreweryMapper : EntityMapper<BreweryDto, Brewery> {
    override fun mapFromEntity(entity: BreweryDto): Brewery {
        return Brewery(
            entity.id,
            entity.name,
            entity.breweryType,
            entity.street,
            entity.address2,
            entity.address3,
            entity.city,
            entity.state,
            entity.countyProvince,
            entity.postalCode,
            entity.country,
            entity.longitude,
            entity.latitude,
            entity.phone,
            entity.websiteUrl,
            entity.updatedAt,
            entity.createdAt
        )
    }
}