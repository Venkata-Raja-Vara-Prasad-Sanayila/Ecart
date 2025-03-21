package com.venkata.org.model.data.registration

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("full_name")
    val name:String,

    @SerializedName("mobile_no")
    val mobileNumber: String,

    @SerializedName("email_id")
    val emailId: String,

    @SerializedName("password")
    val password: String

)
