package com.shashi.onebanc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shashi.onebanc.model.Cuisine
import com.shashi.onebanc.repository.CuisineRepository

class HomeViewModel(private val repository: CuisineRepository) : ViewModel() {

    init {
        repository.getCuisine()
    }

    val cuisines : LiveData<Cuisine>
        get() {
            return repository.cuisineLiveData
        }

    val cuisinesLoadError : LiveData<Boolean>
        get() {
            return repository.cuisineLoadError
        }
}