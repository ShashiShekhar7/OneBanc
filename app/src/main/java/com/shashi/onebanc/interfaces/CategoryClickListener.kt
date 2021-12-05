package com.shashi.onebanc.interfaces

import com.shashi.onebanc.model.CuisineItem

interface CategoryClickListener {
    fun onCategoryClick(cuisineItem: CuisineItem)
}