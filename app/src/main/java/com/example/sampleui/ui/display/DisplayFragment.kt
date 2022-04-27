package com.example.sampleui.ui.display

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampleui.databinding.DisplayClass
import com.example.sampleui.model.Category
import com.example.sampleui.model.DisplayModel
import com.example.sampleui.model.Restaurant
import com.example.sampleui.ui.base.BaseFragment
import com.example.sampleui.utils.Constants.CATEGORY
import com.example.sampleui.utils.Helper
import com.example.sampleui.utils.Response
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisplayFragment : BaseFragment<DisplayClass, Category>(DisplayClass::inflate),
    View.OnClickListener {
    private val viewModel by viewModels<DisplayViewModel>()
    private var lists: DisplayModel = DisplayModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        fetch()
        updateAmount()
    }

    override fun fetch() {
        val category = arguments?.getSerializable(CATEGORY) as Category
        category.title?.let { getRestaurants(it) }
    }

    private fun getRestaurants(title: String) {
        try {
            viewModel.getRestaurants(title).observe(viewLifecycleOwner, { response ->
                when (response) {
                    is Response.Success -> {
                        val tempList = response.data as List<Restaurant>
                        lists.restaurants = ArrayList()
                        lists.restaurants.addAll(tempList)
                        Helper.print("${lists.restaurants}")
                        setAdapter()
                    }
                    is Response.Error -> {
                        Helper.print(response.message)
                    }
                    else -> {
                        Helper.print("Error: $response")
                    }
                }
            })
        } catch (e: Exception) {
            Helper.print("${e.message}")
        }
    }

    override fun onClick(p0: View?) {

    }

    private fun setAdapter() {
        try {
            dataBinding.click = this
            dataBinding.itemclick = fragmentClickListener
            dataBinding.model = lists
        } catch (e: Exception) {
            Helper.print("${e.message}")
        }
    }

    override fun updateAmount() {
        mainViewModel.cartModel.observe(viewLifecycleOwner, { result ->
            if (result.totalAmount!! > 0.0) {
                dataBinding.linear.visibility = View.VISIBLE
                dataBinding.addTo.amount.text = result.totalAmount.toString()
            } else {
                dataBinding.linear.visibility = View.GONE
            }
        })


    }

}