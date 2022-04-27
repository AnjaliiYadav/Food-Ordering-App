package com.example.sampleui.ui.display

import com.example.sampleui.model.Restaurant
import com.example.sampleui.utils.Constants.RESTAURANT
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
 *@date 08/04/2022 9:58 PM
 */
class DisplayRepos @Inject constructor(
    @Named(RESTAURANT)
    private val restaurant: CollectionReference,
) {

    suspend fun getRestaurant(title: String) = flow {
        try {
            emit(Response.Loading)
            Helper.print(mutableListOf(title).toString())
            val result = restaurant
                .whereArrayContains("tag",title)
                .orderBy("promoted",Query.Direction.DESCENDING)
                .orderBy("bookmarked", Query.Direction.DESCENDING)
                .get().await().toObjects(Restaurant::class.java)
            Helper.print(result.toString())
            emit(Response.Success(result))
        } catch (e: Exception) {
            emit(Response.Error("${e.message}"))
        }
    }
}