package com.venkata.org.model.data.subCategory

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubCategory(
    @SerializedName("subcategory_id")
    val id: Int,

    @SerializedName("subcategory_name")
    val name: String,

    @SerializedName("category_id")
    val categoryId: Int,

    @SerializedName("subcategory_image_url")
    val imageUrl: String,

    @SerializedName("is_active")
    val isActive: Int
    ):Parcelable
