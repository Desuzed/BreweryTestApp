package com.desuzed.brewerytestapp.model.retrofit.dto

import com.desuzed.brewerytestapp.model.EntityMapper
import com.desuzed.brewerytestapp.model.pojo.Brewery
import com.google.gson.annotations.SerializedName

data class BreweryDto(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("brewery_type") var breweryType: String? = null,
    @SerializedName("street") var street: String? = null,
    @SerializedName("address_2") var address2: String? = null,
    @SerializedName("address_3") var address3: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("county_province") var countyProvince: String? = null,
    @SerializedName("postal_code") var postalCode: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("longitude") var longitude: String? = null,
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("website_url") var websiteUrl: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("created_at") var createdAt: String? = null
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