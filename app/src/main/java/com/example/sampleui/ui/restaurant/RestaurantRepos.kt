package com.example.sampleui.ui.restaurant

import com.example.sampleui.model.Restaurant
import com.example.sampleui.utils.Constants
import com.example.sampleui.utils.Helper
import com.example.sampleui.utils.Response
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 16/04/2022 3:56 PM
 */
class RestaurantRepos @Inject constructor(
    @Named(Constants.RESTAURANT)
    private val restaurant: CollectionReference,
) {

    suspend fun getOfferRestaurant(id: String) = flow {
        try {
            emit(Response.Loading)
            val result = restaurant.whereEqualTo("id", id).get().await().toObjects(Restaurant::class.java)
            Helper.print("Repos: $result")
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error("${e.message}"))
        }
    }
}