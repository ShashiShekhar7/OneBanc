package com.shashi.onebanc.repository

import androidx.lifecycle.MutableLiveData
import com.shashi.onebanc.model.Cuisine
import com.shashi.onebanc.network.CuisineApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CuisineRepository(private val charactersApiService: CuisineApiService) {

    val cuisineLiveData = MutableLiveData<Cuisine>()
    val cuisineLoadError = MutableLiveData<Boolean>()

    fun getCuisine() {
        val characters = charactersApiService.getCharacters()

        characters.enqueue(object : Callback<Cuisine> {
            override fun onResponse(
                call: Call<Cuisine>,
                response: Response<Cuisine>
            ) {
                if(response.code() == 200)
                    cuisineLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Cuisine>, t: Throwable) {
                cuisineLoadError.postValue(true)
            }

        })

    }
}