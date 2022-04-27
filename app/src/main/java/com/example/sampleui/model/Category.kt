package com.example.sampleui.model

import java.io.Serializable

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 07/04/2022 10:52 PM
 */
data class Category(
    var sno: String? = null,
    var title: String? = null,
    var icon: String? = null,
    var credit: String? = null,
    var active: Boolean? = true
) : Serializable
