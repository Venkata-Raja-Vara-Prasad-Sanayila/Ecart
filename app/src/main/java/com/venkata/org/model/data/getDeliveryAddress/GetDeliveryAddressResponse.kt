package com.venkata.org.model.data.getDeliveryAddress

import com.google.gson.annotations.SerializedName

data class GetDeliveryAddressResponse(

    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("addresses")
    val addresses: List<Address>


)
