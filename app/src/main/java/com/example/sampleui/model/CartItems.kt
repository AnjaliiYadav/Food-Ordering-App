package com.example.sampleui.model

import java.io.Serializable

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 20/04/2022 12:00 PM
 */
data class CartItems(
    var id: String? = null,
    var items: MenuItems? = null,
    var qty: Int? = 0,
    var amount: Double? = 0.0,
) : Serializable