package com.example.sampleui.utils

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 07/04/2022 8:51 PM
 */
sealed class Response<out T> {
    object Loading : Response<Nothing>()

    data class Success<out T>(
        val data: T,
    ) : Response<T>()

    data class Error(
        val message: String,
    ) : Response<Nothing>();
}