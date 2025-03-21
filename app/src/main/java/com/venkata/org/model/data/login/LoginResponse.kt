package com.venkata.org.model.data.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("user")
    val user: User?

)
