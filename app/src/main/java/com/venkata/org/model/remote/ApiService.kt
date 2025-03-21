package com.venkata.org.model.remote

import com.venkata.org.model.data.getProduct.GetProductResponse
import com.venkata.org.model.data.login.LoginRequest
import com.venkata.org.model.data.login.LoginResponse
import com.venkata.org.model.data.registration.RegistrationRequest
import com.venkata.org.model.data.registration.RegistrationResponse
import com.venkata.org.model.data.subCategory.SubCategoryResponse
import com.venkata.org.model.data.subCategoryProducts.SubCategoryProductResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("User/auth")
    @Headers("Content-type: application/json")
    suspend fun checkLoginUser(
        @Body loginRequest: LoginRequest
    ):Response<LoginResponse>

    @POST("User/register")
    @Headers("Content-type: application/json")
    suspend fun addUserRegistration(
        @Body registrationRequest: RegistrationRequest
    ):Response<RegistrationResponse>


    @GET("Category")
    suspend fun getProductCategories():Response<GetProductResponse>

    @GET("SubCategory")
    suspend fun getSubCategories(
        @Query("category_id")keyword: String
    ):Response<SubCategoryResponse>

    @GET("SubCategory/products/{sub_category_id}")
    suspend fun getSubCategoryProductsById(
        @Path("sub_category_id")keyword: Int
    ): Response<SubCategoryProductResponse>


}