package com.venkata.org.model.data.place_order

import com.google.gson.annotations.SerializedName

data class OrderItem(

    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("unit_price")
    val unitPrice: Int
)
