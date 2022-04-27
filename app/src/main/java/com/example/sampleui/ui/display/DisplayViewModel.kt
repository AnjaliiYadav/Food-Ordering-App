package com.example.sampleui.ui.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 08/04/2022 9:59 PM
 */
@HiltViewModel
class DisplayViewModel @Inject constructor(
    private val repos: DisplayRepos,
) : ViewModel(){
    fun getRestaurants(title: String) = liveData {
        repos.getRestaurant(title).collect { response ->
            emit(response)
        }
    }
}