package com.example.sampleui.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import androidx.lifecycle.liveData
import com.example.sampleui.utils.Helper
import dagger.hilt.android.lifecycle.HiltViewModel

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 08/04/2022 9:59 PM
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repos: HomeRepos,
) : ViewModel() {

    fun getCategories() = liveData(Dispatchers.IO) {
        repos.getCategories().collect { response ->
            emit(response)
        }
    }

    fun getOffers() = liveData(Dispatchers.IO) {
        repos.getOffers().collect { response ->
            emit(response)
        }
    }
}