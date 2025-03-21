package com.venkata.org.model.data.registration

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String
)
