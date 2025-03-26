package com.venkata.org.model.data.searchProduct

import com.google.gson.annotations.SerializedName

data class SearchProduct(

    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("description")
    val productDescription: String,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("sub_category_id")
    val subCategoryId: Int,
    @SerializedName("subcategory_name")
    val subCategoryName: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("average_rating")
    val averageRating: Int,
    @SerializedName("product_image_url")
    val productImageUrl: String

//    "product_id": "1",
//"product_name": "One Plus 9 Pro",
//"description": "5G, OS: Android 10, RAM: 16 GB, Internal Storage: 256 GB, Primary Camera: 64 MP, Secondary Camera: 13 MP",
//"category_id": "1",
//"category_name": "Smart Phones",
//"sub_category_id": "1",
//"subcategory_name": "Android Phones",
//"price": "745",
//"average_rating": "0",
//"product_image_url": "one_plus_9_pro.png"
)
