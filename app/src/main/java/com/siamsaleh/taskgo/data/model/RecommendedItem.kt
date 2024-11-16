package com.siamsaleh.taskgo.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecommendedItem(
    @SerializedName("property_name")
    val propertyName: String? = null,
    val location: String? = null,
    val rating: Double? = null,
    val description: String? = null,
    val fare: Double? = null,
    @SerializedName("fare_unit")
    val fareUnit: String? = null,
    @SerializedName("is_available")
    val isAvailable: Boolean? = null,
    @SerializedName("hero_image")
    val heroImage: String? = null,
    @SerializedName("detail_images")
    val detailImages: List<String?>? = null,
    val currency: String? = null
) : Serializable {
    companion object {
        const val RECOMMENDED_ITEM_KEY = "RecommendedItem"
    }
}
