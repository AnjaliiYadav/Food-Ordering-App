package com.example.sampleui.model

import java.io.Serializable

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 08/04/2022 11:24 PM
 */
data class CartModel(
    var id: Long? = null,
    var restaurantCode: String? = null,
    var restaurantName: String? = null,
    var totalAmount: Double? = 0.0,
    var cartItems: MutableList<CartItems>? = ArrayList(),
) : Serializable
