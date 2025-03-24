package com.venkata.org.model.data.productDetail

import android.media.Image
import com.google.gson.annotations.SerializedName

data class ProductInfo(
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("product_name")
    val productName : String,
    @SerializedName("description")
    val description: String,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("average_rating")
    val averageRating: Int,
    @SerializedName("product_image_url")
    val productImageUrl: String,
    @SerializedName("is_active")
    val isActive: Int,
    @SerializedName("images")
    val images: List<ImageDisplay>,
    @SerializedName("specifications")
    val specifications: List<Specification>,
    @SerializedName("reviews")
    val reviews: List<String>


    )

