package com.shashi.onebanc.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shashi.onebanc.R
import com.shashi.onebanc.listener.CategoryClickListener
import com.shashi.onebanc.model.Cuisine

class CategoryAdapter(private val categoryClickListener: CategoryClickListener) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val TAG = "CategoryAdapter"

    private var cuisineCategoryList : Cuisine = Cuisine()

    @SuppressLint("NotifyDataSetChanged")
    fun setCuisineList(cuisine: Cuisine) {
        cuisineCategoryList = cuisine
        notifyDataSetChanged()
    }

    fun getMiddle() : Int {
        val half = Int.MAX_VALUE/2
        return half - half % cuisineCategoryList.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.iv_category)
        val title : TextView = itemView.findViewById(R.id.tv_category_tile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cuisine_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        Log.d(TAG, "onBindViewHolder: $position")
        if (cuisineCategoryList.size != 0) {
            val cuisineItem = cuisineCategoryList[position % cuisineCategoryList.size]
            holder.image.load(cuisineItem.image) {
                crossfade(true)
                placeholder(R.drawable.food_placeholder)
            }
            holder.title.text = cuisineItem.category

            holder.itemView.setOnClickListener {
                categoryClickListener.onCategoryClick(cuisineItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}