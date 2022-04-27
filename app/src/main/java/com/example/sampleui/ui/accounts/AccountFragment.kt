package com.example.sampleui.ui.accounts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.sampleui.databinding.AccountClass
import com.example.sampleui.model.Category
import com.example.sampleui.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseFragment<AccountClass, Category>(AccountClass::inflate) {
    private val viewModel by viewModels<AccountViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun fetch() {

    }

    override fun updateAmount() {

    }

}