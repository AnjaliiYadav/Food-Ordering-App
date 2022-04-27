package com.example.sampleui.ui.checkout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.sampleui.databinding.CheckoutClass
import com.example.sampleui.model.CartModel
import com.example.sampleui.model.HomeModel
import com.example.sampleui.ui.base.BaseFragment
import com.example.sampleui.ui.main.MainViewModel
import com.example.sampleui.utils.Helper
import com.google.rpc.Help
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CheckoutFragment : BaseFragment<CheckoutClass, CartModel>(CheckoutClass::inflate)
, View.OnClickListener{
    private val viewModel by viewModels<CheckoutViewModel>()
    private var lists: CartModel = CartModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetch()
    }

    override fun fetch() {
        mainViewModel.cartModel.observe(viewLifecycleOwner, {cart ->
            Helper.print("$cart")
            lists = cart
            setAdapter()
        })

    }

    override fun updateAmount() {

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

    override fun onClick(p0: View?) {

    }


}