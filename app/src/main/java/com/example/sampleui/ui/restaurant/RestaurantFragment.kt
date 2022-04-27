package com.example.sampleui.ui.restaurant

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.sampleui.R
import com.example.sampleui.databinding.RestaurantClass
import com.example.sampleui.model.CartModel
import com.example.sampleui.model.Offers
import com.example.sampleui.model.Restaurant
import com.example.sampleui.ui.base.BaseFragment
import com.example.sampleui.ui.main.MainViewModel
import com.example.sampleui.utils.Constants.OFFERS
import com.example.sampleui.utils.Constants.RESTAURANT
import com.example.sampleui.utils.Constants.SCREEN_ID
import com.example.sampleui.utils.DateUtils
import com.example.sampleui.utils.Helper
import com.example.sampleui.utils.Response
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : BaseFragment<RestaurantClass, Restaurant>(RestaurantClass::inflate),
    View.OnClickListener {
    private var lists: Restaurant = Restaurant()
    private val viewModel by viewModels<RestaurantViewModel>()
    private var amount: Double? = 0.0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        updateAmount()
        fetch()

        dataBinding.addTo.cart.setOnClickListener {
            addToCart()
        }
    }


    override fun fetch() {
        try {
            val screens = arguments?.getString(SCREEN_ID) as String
            Helper.print(screens)
            if (screens == RESTAURANT) {
                lists = arguments?.getSerializable(RESTAURANT) as Restaurant
            } else {
                val offers = arguments?.getSerializable(OFFERS) as Offers
                Helper.print(offers.toString())
                offers.sno?.let { getRestaurant(it) }
            }

            setCartModel()

            setAdapter()
        } catch (e: java.lang.Exception) {
            Helper.print("${e.message}")
        }
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

    private fun getRestaurant(id: String) {
        try {
            viewModel.getRestaurant(id).observe(viewLifecycleOwner, { response ->
                when (response) {
                    is Response.Loading -> {
                        Helper.print("Loading...")
                    }
                    is Response.Success -> {
                        Helper.print("${response.data}")
                        val temp = response.data as List<Restaurant>
                        lists = temp[0]
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



    private fun setCartModel() {
        Helper.print("setCartModel ")
        val cartModel = CartModel()
        cartModel.id = DateUtils.getTimestamp()
        cartModel.restaurantCode = lists.id
        cartModel.restaurantName = lists.title
        mainViewModel.setCartModel(cartModel)
    }

    private fun addToCart(){
        findNavController().navigateUp()
        findNavController().navigate(R.id.checkoutFragment)
    }

    override fun updateAmount() {
        mainViewModel.cartModel.observe(viewLifecycleOwner, { result ->
            amount = result.totalAmount
            Helper.print("updateAmount = $result")
            val temp = "%,.2f".format(amount)
            Helper.print("Total amount = $temp")
            if (amount==0.0){
                dataBinding.addTo.cart.isEnabled = false
                dataBinding.addTo.cart.setBackgroundResource(R.color.peach_orange)
            }else{
                dataBinding.addTo.cart.isEnabled = true
                dataBinding.addTo.cart.setBackgroundResource(R.color.orange_red)
            }
            dataBinding.addTo.amount.text = "$temp AED"
        })

    }

}