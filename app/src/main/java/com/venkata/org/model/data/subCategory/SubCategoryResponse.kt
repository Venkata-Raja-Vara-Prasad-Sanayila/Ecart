package com.venkata.org.model.data.subCategory

import com.google.gson.annotations.SerializedName

data class SubCategoryResponse(

    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("subcategories")
    val subCategories: List<SubCategory>

    )
