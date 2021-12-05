package com.shashi.onebanc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shashi.onebanc.model.Cuisine
import com.shashi.onebanc.model.Item
import com.shashi.onebanc.repository.CuisineRepository

class HomeViewModel(private val repository: CuisineRepository) : ViewModel() {
    private val TAG = "HomeViewModel"
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

    fun getTop3() : MutableList<Item> {
        Log.d(TAG, "getTop3: called")
        val top3item : MutableList<Item> = mutableListOf()
        for (cuisineItem in cuisines.value!!)
            for (item in cuisineItem.item)
                if (item.isTop3.toString() == "true") {
                    Log.d(TAG, "getTop3: " + item.name)
                    top3item.add(item)
                }
        return top3item
    }
}