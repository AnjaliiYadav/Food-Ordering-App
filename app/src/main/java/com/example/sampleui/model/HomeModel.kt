package com.example.sampleui.model

import java.io.Serializable

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 11/04/2022 12:24 AM
 */
data class HomeModel(
    var category: ArrayList<Category> = ArrayList(),
    var offers: ArrayList<Offers> = ArrayList(),
) : Serializable
