package com.venkata.org.model.data.productDetail

import com.google.gson.annotations.SerializedName

data class Specification(
    @SerializedName("specification_id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("specification")
    val specification: String,
    @SerializedName("display_order")
    val displayOrder: Int
)
//"specifications": [
//{
//    "specification_id": "2",
//    "title": "Model Name",
//    "specification": "OnePlus 9 Pro 5G",
//    "display_order": "1"
//},