package com.example.sampleui.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampleui.databinding.SearchClass
import com.example.sampleui.model.Category
import com.example.sampleui.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchClass, Category>(SearchClass::inflate) {
    private val viewModel by viewModels<SearchViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun fetch() {

    }

    override fun updateAmount() {

    }

}