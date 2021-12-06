package com.shashi.onebanc.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.shashi.onebanc.R
import com.shashi.onebanc.adapter.CategoryAdapter
import com.shashi.onebanc.listener.CategoryClickListener
import com.shashi.onebanc.model.CuisineItem
import com.shashi.onebanc.model.Item
import com.shashi.onebanc.network.CuisineApiService
import com.shashi.onebanc.network.RetrofitHelper
import com.shashi.onebanc.repository.CuisineRepository
import com.shashi.onebanc.util.AddRemoveItem
import com.shashi.onebanc.viewmodel.HomeViewModel
import com.shashi.onebanc.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment(), CategoryClickListener {

    private val TAG = "HomeFragment"

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val categoryAdapter = CategoryAdapter(this)

        val viewPager2 : ViewPager2 = view.findViewById(R.id.view_pager_category)
        val dummyCard : CardView = view.findViewById(R.id.dummy_category)
        val rlTop1Details : RelativeLayout = view.findViewById(R.id.rl_top1)
        val rlTop2Details : RelativeLayout = view.findViewById(R.id.rl_top2)
        val rlTop3Details : RelativeLayout = view.findViewById(R.id.rl_top3)


        setViewPager(viewPager2, categoryAdapter)

        val cuisineApiService = RetrofitHelper.getInstance().create(CuisineApiService::class.java)
        val repository = CuisineRepository(cuisineApiService)


        viewModel = ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]

        viewModel.cuisines.observe(viewLifecycleOwner, {
            categoryAdapter.setCuisineList(it)

            Log.d(TAG, "onCreateView: Middle : ${categoryAdapter.getMiddle()}")
            viewPager2.visibility = View.VISIBLE

            viewPager2.currentItem = categoryAdapter.getMiddle()
            dummyCard.visibility = View.GONE
            rlTop1Details.visibility = View.VISIBLE
            rlTop2Details.visibility = View.VISIBLE
            rlTop3Details.visibility = View.VISIBLE

            val top3Item = viewModel.getTop3()

            if (top3Item.size > 0) {
                val imageTop1: ImageView = view.findViewById(R.id.iv_top_1)
                val foodNameTop1: TextView = view.findViewById(R.id.tv_top1_name)
                val ratingTop1: TextView = view.findViewById(R.id.tv_top1_rating)
                val priceTop1: TextView = view.findViewById(R.id.tv_top1_price)

                setTopItem(top3Item[0], imageTop1, foodNameTop1, ratingTop1, priceTop1)

            }

            if (top3Item.size > 1) {
                val imageTop1: ImageView = view.findViewById(R.id.iv_top_2)
                val foodNameTop2: TextView = view.findViewById(R.id.tv_top2_name)
                val ratingTop2: TextView = view.findViewById(R.id.tv_top2_rating)
                val priceTop2: TextView = view.findViewById(R.id.tv_top2_price)

                setTopItem(top3Item[1], imageTop1, foodNameTop2, ratingTop2, priceTop2)
            }

            if (top3Item.size > 2) {
                val imageTop1: ImageView = view.findViewById(R.id.iv_top_3)
                val foodNameTop3: TextView = view.findViewById(R.id.tv_top3_name)
                val ratingTop3: TextView = view.findViewById(R.id.tv_top3_rating)
                val priceTop3: TextView = view.findViewById(R.id.tv_top3_price)

                setTopItem(top3Item[2], imageTop1, foodNameTop3, ratingTop3, priceTop3)
            }

            addRemoveItem(view)

        })

        viewModel.cuisinesLoadError.observe(viewLifecycleOwner, {
            Log.d(TAG, "onCreateView: Error : $it")
        })

        return view
    }

    private fun setTopItem(item:Item, image:ImageView, foodName:TextView, rating:TextView, price:TextView) {
        image.load(item.image) {
            crossfade(true)
            placeholder(R.drawable.food_placeholder)
        }

        foodName.text = item.name
        foodName.setTypeface(foodName.typeface, Typeface.BOLD_ITALIC)
        rating.text = item.rating.toString()
        price.text = item.price.toString()
    }

    private fun setViewPager(viewPager : ViewPager2, categoryAdapter: CategoryAdapter) {

        viewPager.apply {
            adapter = categoryAdapter
            clipToPadding = false
            offscreenPageLimit = 3
        }
    }

    private fun addRemoveItem(view: View) {
        val removeTop1 : ImageView = view.findViewById(R.id.iv_top_1_remove)
        val removeTop2 : ImageView = view.findViewById(R.id.iv_top_2_remove)
        val removeTop3 : ImageView = view.findViewById(R.id.iv_top_3_remove)

        val addTop1 : ImageView = view.findViewById(R.id.iv_top_1_add)
        val addTop2 : ImageView = view.findViewById(R.id.iv_top_2_add)
        val addTop3 : ImageView = view.findViewById(R.id.iv_top_3_add)

        val qtyTop1 : TextView = view.findViewById(R.id.tv_top1_qty)
        val qtyTop2 : TextView = view.findViewById(R.id.tv_top2_qty)
        val qtyTop3 : TextView = view.findViewById(R.id.tv_top3_qty)

        val addRemoveItem = AddRemoveItem()

        removeTop1.setOnClickListener {
            addRemoveItem.removeItem(qtyTop1)
        }
        removeTop2.setOnClickListener {
            addRemoveItem.removeItem(qtyTop2)
        }
        removeTop3.setOnClickListener {
            addRemoveItem.removeItem(qtyTop3)
        }

        addTop1.setOnClickListener {
            addRemoveItem.addItem(qtyTop1)
        }
        addTop2.setOnClickListener {
            addRemoveItem.addItem(qtyTop2)
        }
        addTop3.setOnClickListener {
            addRemoveItem.addItem(qtyTop3)
        }


    }

    override fun onCategoryClick(cuisineItem: CuisineItem) {
        val itemFragment = ItemFragment()
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, itemFragment)
            commit()
        }
    }

}