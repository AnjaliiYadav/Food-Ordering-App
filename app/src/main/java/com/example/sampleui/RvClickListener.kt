package com.example.sampleui

import android.view.View
import com.example.sampleui.adapter.GlobalAdapter

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 06/04/2022 12:58 AM
 */
interface RvClickListener {

    fun click(
        view: View,
        item: Any?,
        position: Int,
        adapter: GlobalAdapter<Any>,
    )
}