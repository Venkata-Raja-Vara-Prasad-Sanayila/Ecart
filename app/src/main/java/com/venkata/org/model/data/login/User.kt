package com.venkata.org.model.data.login

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("full_name")
    val name: String,

    @SerializedName("mobile_no")
    val mobileNo: String,

    @SerializedName("email_id")
    val emailId: String

)
