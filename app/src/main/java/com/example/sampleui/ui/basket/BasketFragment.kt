package com.example.sampleui.ui.basket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sampleui.R
import com.example.sampleui.databinding.BasketClass
import com.example.sampleui.model.CartItems
import com.example.sampleui.model.MenuItems
import com.example.sampleui.ui.main.MainViewModel
import com.example.sampleui.utils.Constants.MENU_ITEM
import com.example.sampleui.utils.DateUtils
import com.example.sampleui.utils.Helper
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BasketClass
    private lateinit var item: MenuItems
    private var quantity: Int = 1;
    private var amount: Double = 0.0

    private val viewModel by activityViewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BasketClass.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetch()
        binding.addItem.setOnClickListener {
            addQuantity()
        }
        binding.removeItem.setOnClickListener {
            removeQuantity()
        }
        binding.closeBasket.setOnClickListener {
            dismiss()
        }
        binding.addToCart.setOnClickListener {
            if (quantity > 0)
                addToCart()
            else
                removeFromCart()
        }
    }



    override fun getTheme(): Int = R.style.CustomBottomSheetDialog

    private fun addQuantity() {
        quantity++
        calculate()
    }

    private fun removeQuantity() {
        if (quantity > 0) {
            quantity--
            calculate()
        }
    }

    private fun fetch() {
        try {
            item = arguments?.getSerializable(MENU_ITEM) as MenuItems
            binding.model = item
            setAmount()
        } catch (e: Exception) {
            Helper.print("${e.message}")
        }

    }

    private fun setAmount() {
        amount = item.rate.toString().toDouble()
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {
        setAmount()
        amount *= quantity
        val temp = "%,.3f".format(amount)
        binding.amount.text = "$temp ${item.curr}"
        Helper.print("amount $temp")
        binding.itemQty.text = quantity.toString()
    }

    private fun addToCart() {
        val items = CartItems()

        items.amount = amount
        items.items = item
        items.qty = quantity
        items.id = item.id
       // viewModel.addTotalAmount(amount)
        //viewModel.setCartItem(items)

        val model= viewModel.getCartModel()

        model?.cartItems?.add(items)
        if (model !=null){
            viewModel.setCartModel(model)
            viewModel.addTotalAmount(amount)
        }
        Helper.print("updated cart :${viewModel.cartModel.value}")
        findNavController().navigateUp()
    }

    private fun removeFromCart() {

    }


}