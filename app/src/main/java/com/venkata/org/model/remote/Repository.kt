package com.venkata.org.model.remote

import com.venkata.org.model.data.deliveryAddress.AddDeliveryAddressRequest
import com.venkata.org.model.data.deliveryAddress.AddDeliveryAddressResponse
import com.venkata.org.model.data.getDeliveryAddress.GetDeliveryAddressResponse
import com.venkata.org.model.data.getProduct.GetProductResponse
import com.venkata.org.model.data.login.LoginRequest
import com.venkata.org.model.data.login.LoginResponse
import com.venkata.org.model.data.place_order.PlaceOrder
import com.venkata.org.model.data.place_order.PlaceOrderResponse
import com.venkata.org.model.data.registration.RegistrationRequest
import com.venkata.org.model.data.registration.RegistrationResponse
import com.venkata.org.model.data.searchProduct.SearchProductResponse
import com.venkata.org.model.data.subCategory.SubCategoryResponse
import com.venkata.org.model.data.subCategoryProducts.SubCategoryProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

class Repository(private val apiService: ApiService) {

    suspend fun checkLoginUser(loginRequest: LoginRequest) = apiService.checkLoginUser(loginRequest)

    suspend fun addUserRegistration(registrationRequest: RegistrationRequest) = apiService.addUserRegistration(registrationRequest)

    suspend fun getProductCategories() = apiService.getProductCategories()

    suspend fun getProductSubCategories(keyword: String) = apiService.getSubCategories(keyword)

    suspend fun getSubCategoryProductsById(keyword: Int) = apiService.getSubCategoryProductsById(keyword)

    suspend fun getProductDetail(keyword: Int) = apiService.getProductDetail(keyword)

    suspend fun searchProducts(keyword: String) = apiService.searchProducts(keyword)

    suspend fun addDeliveryAddress(address: AddDeliveryAddressRequest) = apiService.addDeliveryAddress(address)

    suspend fun getDeliveryAddresses( userId: Int) = apiService.getDeliveryAddresses(userId)

    suspend fun postPlaceOrder( placeOrder: PlaceOrder) = apiService.postPlaceOrder(placeOrder)

}