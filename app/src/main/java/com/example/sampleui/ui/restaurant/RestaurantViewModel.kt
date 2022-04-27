package com.example.sampleui.ui.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 16/04/2022 3:56 PM
 */
@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repos: RestaurantRepos,
) : ViewModel() {
    fun getRestaurant(id: String) = liveData {
        repos.getOfferRestaurant(id).collect { response ->
            emit(response)
        }
    }
}