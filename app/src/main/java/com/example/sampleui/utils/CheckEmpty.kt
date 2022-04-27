package com.example.sampleui.utils

import android.widget.EditText
import com.example.sampleui.utils.Constants.INCOMPLETE_RECORDS

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 08/04/2022 10:53 PM
 */
class CheckEmpty<T : EditText> {

    fun checkEmpty(input: T): Int {
        if (input.text.toString().isEmpty()) {
            input.error = INCOMPLETE_RECORDS
            return 1
        } else {

        }
        return 0
    }
}