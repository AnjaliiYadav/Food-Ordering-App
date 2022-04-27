package com.example.sampleui.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleui.model.CartItems
import com.example.sampleui.model.CartModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.function.Predicate
import javax.inject.Inject


/**
 *SampleUI
 *@author Anjali Yadav
 *@date 21/04/2022 12:17 PM
 */

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _data = MutableLiveData<CartModel>()
    var cartModel: LiveData<CartModel> = _data


    //private var cart= CartItems()

    fun setCartModel(model: CartModel) {
        // _data.value = model
        _data.postValue(model)

    }

    fun getCartModel(): CartModel? {
        return _data.value
    }

    fun setCartItem(items: CartItems) {
       // _data.value?.cartItems?.add(items)

        _data.value?.cartItems?.add(items)

    }

    fun addTotalAmount(amount: Double) {
        cartModel.value?.totalAmount = cartModel.value?.totalAmount?.plus(amount);
    }

    fun subTotalAmount(amount: Double) {
        cartModel.value?.totalAmount = cartModel.value?.totalAmount?.minus(amount);
    }

    fun removeCartItem(items: CartItems) {

        val condition = Predicate<CartItems> {
            it.id == items.id
        }
        _data.value?.cartItems?.removeIf(condition)
    }

}