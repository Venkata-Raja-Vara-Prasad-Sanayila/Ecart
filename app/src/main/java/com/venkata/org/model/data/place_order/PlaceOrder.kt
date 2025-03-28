package com.venkata.org.model.data.place_order

import com.google.gson.annotations.SerializedName
import com.venkata.org.model.data.getDeliveryAddress.Address

data class PlaceOrder(
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("delivery_address")
    val deliverAddress: OrderAddress,
    @SerializedName("items")
    val items: List<OrderItem>,
    @SerializedName("bill_amount")
    val billAmount: Long,
    @SerializedName("payment_method")
    val paymentMethod: String




)
