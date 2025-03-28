package com.venkata.org.model.data.place_order

import com.google.gson.annotations.SerializedName

data class PlaceOrderResponse(

    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("order_id")
    val orderId: Long

)