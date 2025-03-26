package com.venkata.org.model.data.deliveryAddress

import com.google.gson.annotations.SerializedName

data class AddDeliveryAddressRequest(

    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("address")
    val address: String
)
