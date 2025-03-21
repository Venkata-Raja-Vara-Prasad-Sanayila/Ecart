package com.venkata.org.model.data.subCategoryProducts

import com.google.gson.annotations.SerializedName

data class SubCategoryProduct(
    @SerializedName("product_id")
    val id: Int,
    @SerializedName("product_name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("sub_category_id")
    val subCategoryId: Int,
    @SerializedName("subcategory_name")
    val subCategoryName: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("average_rating")
    val averageRating: Int,
    @SerializedName("product_image_url")
    val imageUrl: String

)
