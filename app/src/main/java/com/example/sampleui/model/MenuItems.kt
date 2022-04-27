package com.example.sampleui.model

import java.io.Serializable

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 13/04/2022 1:13 PM
 */
data class MenuItems(
    var id: String? = null,
    var title: String? = null,
    var icon: String? = null,
    var description: String? = null,
    var rate: String? = null,
    var curr: String? = null,
) : Serializable
