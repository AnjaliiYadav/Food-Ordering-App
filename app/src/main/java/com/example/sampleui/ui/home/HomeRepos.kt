package com.example.sampleui.ui.home

import com.example.sampleui.model.Category
import com.example.sampleui.model.HomeModel
import com.example.sampleui.model.Offers
import com.example.sampleui.utils.Constants.CATEGORY
import com.example.sampleui.utils.Constants.OFFERS
import com.example.sampleui.utils.Helper
import com.example.sampleui.utils.Response
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Error
import javax.inject.Inject
import javax.inject.Named

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 08/04/2022 9:59 PM
 */
class HomeRepos @Inject constructor(
    @Named(CATEGORY)
    private val category: CollectionReference,
    @Named(OFFERS)
    private val offers: CollectionReference,
) {

    suspend fun getCategories() = flow {
        try {
            emit(Response.Loading)
            val result = category.orderBy("sno").get().await().toObjects(Category::class.java)
            emit(Response.Success(result))
        } catch (e: Exception) {
            Helper.print("${e.message}")
            emit(Error(e.message))

        }
    }

    suspend fun getOffers() = flow {
        try {
            emit(Response.Loading)
            val result = offers.orderBy("sno").get().await().toObjects(Offers::class.java)
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Error(e.message))
        }
    }
}