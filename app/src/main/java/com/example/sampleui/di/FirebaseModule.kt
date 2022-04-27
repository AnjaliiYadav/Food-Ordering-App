package com.example.sampleui.di

import com.example.sampleui.utils.Constants.CATEGORY
import com.example.sampleui.utils.Constants.OFFERS
import com.example.sampleui.utils.Constants.RESTAURANT
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/**
 *SampleUI
 *@author Anjali Yadav
 *@date 07/04/2022 5:46 PM
 */

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    fun providesFirebaseFirestore() = FirebaseFirestore.getInstance();

    @Provides
    fun providesFirebaseDatabase() = FirebaseDatabase.getInstance();

    @Provides
    @Named(CATEGORY)
    fun providesCategory(db: FirebaseFirestore) = db.collection(CATEGORY)

    @Provides
    @Named(OFFERS)
    fun providesOffers(db: FirebaseFirestore) = db.collection(OFFERS)

    @Provides
    @Named(RESTAURANT)
    fun providesRestaurants(db: FirebaseFirestore) = db.collection(RESTAURANT)
}