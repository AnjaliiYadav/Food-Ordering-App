package com.example.sampleui.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.sampleui.utils.Constants.TAG

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 06/04/2022 8:51 PM
 */
class Helper {
    companion object {
        fun print(message: String) {
            Log.d(TAG, message)
        }

        fun toastHome(message: String, context: Context)
        {
            Toast.makeText( context, message, Toast.LENGTH_SHORT).show()
        }

        fun convertMapToList(map: Map<String, String>): ArrayList<String> {
            return ArrayList(map.values)
        }


    }
}