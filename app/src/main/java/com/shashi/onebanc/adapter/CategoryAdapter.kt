package com.shashi.onebanc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shashi.onebanc.R
import com.shashi.onebanc.model.Cuisine
import java.util.zip.Inflater

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var cuisineCategoryList : Cuisine = Cuisine()

    fun setCuisineList(cuisine: Cuisine) {
        cuisineCategoryList = cuisine
        notifyDataSetChanged()
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
        val cuisineItem = cuisineCategoryList[position]
        holder.image.load(cuisineItem.image) {
            crossfade(true)
            placeholder(R.drawable.food_placeholder)
        }
        holder.title.text = cuisineItem.category
    }

    override fun getItemCount(): Int {
        return cuisineCategoryList.size
    }
}