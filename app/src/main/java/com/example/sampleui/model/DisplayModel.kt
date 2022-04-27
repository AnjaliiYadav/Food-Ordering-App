package com.example.sampleui.model

import java.io.Serializable

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 12/04/2022 10:33 PM
 */
data class DisplayModel(
    var restaurants: ArrayList<Restaurant> = ArrayList(),
) : Serializable