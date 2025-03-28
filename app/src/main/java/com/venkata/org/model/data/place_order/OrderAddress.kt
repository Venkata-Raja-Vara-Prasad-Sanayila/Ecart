package com.venkata.org.model.data.place_order

import com.google.gson.annotations.SerializedName

data class OrderAddress (

    @SerializedName("title")
    val title: String,
    @SerializedName("address")
    val address: String

)