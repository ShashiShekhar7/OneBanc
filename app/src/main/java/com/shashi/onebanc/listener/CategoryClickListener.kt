package com.shashi.onebanc.listener

import com.shashi.onebanc.model.CuisineItem

interface CategoryClickListener {
    fun onCategoryClick(cuisineItem: CuisineItem)
}