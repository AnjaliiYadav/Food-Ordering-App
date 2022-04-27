package com.example.sampleui.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampleui.R
import com.example.sampleui.RvClickListener
import com.example.sampleui.adapter.GlobalAdapter
import com.example.sampleui.databinding.HomeClass
import com.example.sampleui.model.Category
import com.example.sampleui.model.HomeModel
import com.example.sampleui.model.Offers
import com.example.sampleui.model.Restaurant
import com.example.sampleui.ui.base.BaseFragment
import com.example.sampleui.utils.Constants
import com.example.sampleui.utils.Helper
import com.example.sampleui.utils.Response
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeClass, HomeModel>(HomeClass::inflate), View.OnClickListener {
    private val viewModel by viewModels<HomeViewModel>()
    private var lists: HomeModel = HomeModel()
    private var amount: Double? = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setAdapter()
        fetch()
    }

    override fun fetch() {
        getCategory()
        getOffer()
    }

    private fun getOffer() {
        viewModel.getOffers().observe(viewLifecycleOwner, { response ->
            when (response) {
                is Response.Success<*> -> {
                    val tempList = response.data as List<Offers>
                    lists.offers = ArrayList()
                    lists.offers.addAll(tempList)
                    setAdapter()
                }

                is Response.Error -> {
                    Helper.print(response.message)
                }
            }
        })
    }

    private fun getCategory() {
        try {
            viewModel.getCategories().observe(viewLifecycleOwner, { response ->
                when (response) {
                    is Response.Success<*> -> {
                        val tempList = response.data as List<Category>
                        lists.category = ArrayList()
                        lists.category.addAll(tempList)
                        setAdapter()
                    }

                    is Response.Error -> {
                        Helper.print(response.message)
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

    @SuppressLint("SetTextI18n")
    override fun updateAmount() {

        mainViewModel.cartModel.observe(viewLifecycleOwner, { result ->
            amount = result.totalAmount
            if (amount!! > 0.0) {
                val temp = "%,.2f".format(amount)
                dataBinding.linear.visibility = View.VISIBLE
                dataBinding.addTo.amount.text = "$temp AED"
            } else {
                dataBinding.linear.visibility = View.GONE
            }
        })


    }

}