package com.shashi.onebanc.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shashi.onebanc.R
import com.shashi.onebanc.adapter.CategoryAdapter
import com.shashi.onebanc.network.CuisineApiService
import com.shashi.onebanc.network.RetrofitHelper
import com.shashi.onebanc.repository.CuisineRepository
import com.shashi.onebanc.viewmodel.HomeViewModel
import com.shashi.onebanc.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val categoryAdapter = CategoryAdapter()

        val recyclerView : RecyclerView = view.findViewById(R.id.rv_category)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
            setHasFixedSize(true)
        }

        val cuisineApiService = RetrofitHelper.getInstance().create(CuisineApiService::class.java)
        val repository = CuisineRepository(cuisineApiService)

        viewModel = ViewModelProvider(this, HomeViewModelFactory(repository))[HomeViewModel::class.java]

        viewModel.cuisines.observe(viewLifecycleOwner, {
            categoryAdapter.setCuisineList(it)
            Log.d(TAG, "onCreateView: $it")
        })

        viewModel.cuisinesLoadError.observe(viewLifecycleOwner, {
            Log.d(TAG, "onCreateView: Error : $it")
        })

        return view
    }

}