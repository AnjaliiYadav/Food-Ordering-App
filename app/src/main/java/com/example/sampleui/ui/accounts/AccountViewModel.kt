package com.example.sampleui.ui.accounts

import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 08/04/2022 9:57 PM
 */
class AccountViewModel @Inject constructor(
    private val repos: AccountRepos,
) : ViewModel() {
}