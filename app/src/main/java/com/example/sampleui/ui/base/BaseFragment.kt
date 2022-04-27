package com.example.sampleui.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.sampleui.R
import com.example.sampleui.RvClickListener
import com.example.sampleui.adapter.GlobalAdapter
import com.example.sampleui.model.Category
import com.example.sampleui.model.MenuItems
import com.example.sampleui.model.Offers
import com.example.sampleui.model.Restaurant
import com.example.sampleui.ui.basket.BasketFragment
import com.example.sampleui.ui.main.MainViewModel
import com.example.sampleui.utils.Constants
import com.example.sampleui.utils.Constants.CATEGORY
import com.example.sampleui.utils.Constants.MENU_ITEM
import com.example.sampleui.utils.Constants.OFFERS
import com.example.sampleui.utils.Constants.RESTAURANT
import com.example.sampleui.utils.Constants.SCREEN_ID
import com.example.sampleui.utils.Constants.TAG
import com.example.sampleui.utils.Helper

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 02/04/2022 2:14 PM
 */

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding, T>(private val inflate: Inflate<VB>) : Fragment() {
    lateinit var fragmentClickListener: RvClickListener
    private var _dataBinding: VB? = null
    val dataBinding get() = _dataBinding!!
    val mainViewModel by activityViewModels<MainViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _dataBinding = inflate.invoke(inflater, container, false)
        setListeners()
        return dataBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _dataBinding = null
    }


    abstract fun fetch()

    fun setListeners() {
        fragmentClickListener = object : RvClickListener {
            override fun click(
                view: View,
                item: Any?,
                position: Int,
                adapter: GlobalAdapter<Any>,
            ) {

                val bundle = Bundle()
                when (item) {
                    is Category -> {
                        bundle.putSerializable(CATEGORY, item)
                        findNavController().navigate(R.id.displayFragment, bundle)
                    }
                    is Offers -> {
                        bundle.putString(SCREEN_ID, OFFERS)
                        bundle.putSerializable(OFFERS, item)
                        findNavController().navigate(R.id.restaurantFragment, bundle)
                    }

                    is Restaurant -> {
                        bundle.putString(SCREEN_ID, RESTAURANT)
                        bundle.putSerializable(RESTAURANT, item)
                        findNavController().navigate(R.id.restaurantFragment, bundle)
                    }

                    is MenuItems -> {
                        Helper.print("MenuItems : $item")
                        bundle.putSerializable(MENU_ITEM, item)
                        findNavController().navigate(R.id.basketFragment, bundle)

                        /* val modalBottomSheet = BasketFragment()
                         modalBottomSheet.apply {
                             modalBottomSheet.activity?.supportFragmentManager?.let {
                                 show(it,
                                     TAG)
                                 Helper.print(it.toString())
                             }
                         }*/
                    }
                }
            }
        }
    }


    open abstract fun updateAmount()

}