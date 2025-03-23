package com.venkata.org.model.data.productDetail

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(

    @SerializedName("status")
    val status : Int,
    @SerializedName("message")
    val message : String,
    @SerializedName("product")
    val product: ProductInfo
)
