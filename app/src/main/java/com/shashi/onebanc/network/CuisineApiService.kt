package com.shashi.onebanc.network


import com.shashi.onebanc.model.Cuisine
import retrofit2.Call
import retrofit2.http.GET

interface CuisineApiService {

    @GET("cuisine.json")
    fun getCharacters() : Call<Cuisine>
}