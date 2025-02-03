package com.afp.scarab.api
import com.afp.scarab.model.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {
    @GET("region/{region}")
    suspend fun getCountriesByRegion(@Path("region") region: String): List<Country>

    @GET("name/{name}")
    suspend fun getCountryDetails(@Path("name") name: String): List<Country>
}