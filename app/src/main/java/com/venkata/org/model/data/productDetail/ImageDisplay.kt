package com.venkata.org.model.data.productDetail

import android.view.Display
import com.google.gson.annotations.SerializedName

data class ImageDisplay(
    @SerializedName("image")
    val imagePath: String,
    @SerializedName("display_order")
    val display_order: Int

)
//{
//    "image": "oneplus_9_pro1.jpg",
//    "display_order": "1"
//},