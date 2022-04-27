package com.example.sampleui.model

import java.io.Serializable

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 11/04/2022 12:28 AM
 */
data class Offers(
    var sno: String? = null,
    var icon: String? = null,
    var credit: String? = null,
    var active: Boolean = true,
) : Serializable