package com.siamsaleh.taskgo.data.remote

import com.siamsaleh.taskgo.data.model.RecommendedItem
import retrofit2.http.GET

interface ApiService {

    @GET("/")
    suspend fun getRecommendedPlace(): List<RecommendedItem>
}