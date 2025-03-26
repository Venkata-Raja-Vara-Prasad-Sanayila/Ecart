package com.venkata.org.model.data.deliveryAddress

import com.google.gson.annotations.SerializedName

data class AddDeliveryAddressResponse(


    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)
