package com.venkata.org.model.data.subCategoryProducts

import com.google.gson.annotations.SerializedName

data class SubCategoryProductResponse(

    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("products")
    val subCategoryProducts: MutableList<SubCategoryProduct>

)