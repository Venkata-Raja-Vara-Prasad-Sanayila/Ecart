package com.venkata.org.model.data.getProduct

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(

    @SerializedName("category_id")
    val id : Int,

    @SerializedName("category_name")
    val name: String,

    @SerializedName("category_image_url")
    val imageUrl: String,

    @SerializedName("is_active")
    val isActive: Int

):Parcelable
