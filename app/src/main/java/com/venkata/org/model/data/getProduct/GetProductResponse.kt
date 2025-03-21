package com.venkata.org.model.data.getProduct

data class GetProductResponse(

    val status: Int,

    val message: String,

    val categories: List<Category>

)
