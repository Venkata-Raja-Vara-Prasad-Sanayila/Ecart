package com.venkata.org.model.data.searchProduct

import com.google.gson.annotations.SerializedName

data class SearchProductResponse(

    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("products")
    val products: List<SearchProduct>

//    "status": 0,
//    "message": "Success",
//"products":
)
