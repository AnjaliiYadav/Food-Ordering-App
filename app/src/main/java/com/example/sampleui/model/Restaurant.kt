package com.example.sampleui.model

import java.io.Serializable

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 12/04/2022 4:09 PM
 */
data class Restaurant(
    var id: String? = null,
    var title: String? = null,
    var icon: String? = null,
    var description: String? = null,
    var rating: String? = null,
    var avgTime: String? = null,
    var avgCost: String? = null,
    var promoted: Boolean? = false,
    var offers: String? = null,
    var bookmarked: Boolean? = false,
    var tag: ArrayList<String>? = ArrayList(),
    var menu: ArrayList<MenuItems>? = ArrayList(),
) : Serializable
