package com.example.sampleui.ui.search

import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 08/04/2022 9:59 PM
 */
class SearchViewModel @Inject constructor(
    private val repos: SearchRepos,
) : ViewModel() {
}