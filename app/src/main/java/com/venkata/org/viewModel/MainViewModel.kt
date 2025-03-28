package com.venkata.org.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.venkata.org.model.commons.ApiState
import com.venkata.org.model.data.deliveryAddress.AddDeliveryAddressRequest
import com.venkata.org.model.data.deliveryAddress.AddDeliveryAddressResponse
import com.venkata.org.model.data.getDeliveryAddress.Address
import com.venkata.org.model.data.getDeliveryAddress.GetDeliveryAddressResponse
import com.venkata.org.model.data.getProduct.GetProductResponse
import com.venkata.org.model.data.login.LoginRequest
import com.venkata.org.model.data.login.LoginResponse
import com.venkata.org.model.data.place_order.PlaceOrder
import com.venkata.org.model.data.place_order.PlaceOrderResponse
import com.venkata.org.model.data.productDetail.ProductDetailResponse
import com.venkata.org.model.data.registration.RegistrationRequest
import com.venkata.org.model.data.registration.RegistrationResponse
import com.venkata.org.model.data.searchProduct.SearchProductResponse
import com.venkata.org.model.remote.Repository
import com.venkata.org.model.data.subCategory.SubCategoryResponse
import com.venkata.org.model.data.subCategoryProducts.SubCategoryProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.Query

class MainViewModel(private val repo: Repository): ViewModel() {


    val selectedShippingAddress = MutableLiveData<Address>()

    val selectedPaymentMode = MutableLiveData<String>()

    private val _apiStateLoginUser = MutableLiveData<ApiState<LoginResponse>>()
    val apiStateLoginUser: LiveData<ApiState<LoginResponse>> = _apiStateLoginUser


    private val _apiStateUserRegistration = MutableLiveData<ApiState<RegistrationResponse>>()
    val apiStateUserRegistration: LiveData<ApiState<RegistrationResponse>> = _apiStateUserRegistration

    private val _apiStateProductCategories = MutableLiveData<ApiState<GetProductResponse>>()
    val apiStateProductCategories: LiveData<ApiState<GetProductResponse>> = _apiStateProductCategories

    private val _apiStateSubCategories = MutableLiveData<ApiState<SubCategoryResponse>>()
    val apiStateSubCategories: LiveData<ApiState<SubCategoryResponse>> = _apiStateSubCategories

    private val _apiStateSubCategoryProductsById = MutableLiveData<ApiState<SubCategoryProductResponse>>()
    val apiStateSubCategoryProductsById: LiveData<ApiState<SubCategoryProductResponse>> = _apiStateSubCategoryProductsById

    private val _apiStateProductDetail = MutableLiveData<ApiState<ProductDetailResponse>>()
    val apiStateProductDetail: LiveData<ApiState<ProductDetailResponse>> = _apiStateProductDetail

    private val _apiStateSearchProducts = MutableLiveData<ApiState<SubCategoryProductResponse>>()
    val apiStateSearchProducts: LiveData<ApiState<SubCategoryProductResponse>> = _apiStateSearchProducts

    private val _apiStateAddAddress = MutableLiveData<ApiState<AddDeliveryAddressResponse>>()
    val apiStateAddAddress: LiveData<ApiState<AddDeliveryAddressResponse>> = _apiStateAddAddress

    private val _apiStateGetAddress = MutableLiveData<ApiState<GetDeliveryAddressResponse>>()
    val apiStateGetAddress: LiveData<ApiState<GetDeliveryAddressResponse>> = _apiStateGetAddress


    private val _apiStatePlaceOrder = MutableLiveData<ApiState<PlaceOrderResponse>>()
    val apiStatePlaceOrder: LiveData<ApiState<PlaceOrderResponse>> = _apiStatePlaceOrder



    fun getUserLogin(loginRequest: LoginRequest){

        viewModelScope.launch(Dispatchers.IO) {

            try {

                val response: Response<LoginResponse> = repo.checkLoginUser(loginRequest)
                if (!response.isSuccessful){
                    _apiStateLoginUser.postValue(ApiState.Error("Something went wrong while performing operation. Error is: ${
                        response.errorBody()?.string()
                    }"))
                    return@launch
                }

                val result: LoginResponse? = response.body()
                if (result == null) {
                    _apiStateLoginUser.postValue(ApiState.Error("Empty response from server. Please retry."))
                    return@launch
                }

                if (result.status == 0){
                    _apiStateLoginUser.postValue(ApiState.Success(result))

                }
                else{
                    _apiStateLoginUser.postValue(ApiState.Error(result.message))

                }


            }
            catch (e: Exception){
                e.printStackTrace()
                _apiStateLoginUser.postValue(ApiState.Error("Error is $e"))
            }

        }

    }

    //==============================================================


    fun addUserRegistration(userRegistrationRequest: RegistrationRequest){

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val response: Response<RegistrationResponse> =  repo.addUserRegistration(userRegistrationRequest)

                if (!response.isSuccessful) {
                    _apiStateUserRegistration.postValue(ApiState.Error("Failed to search. Error is: ${response.errorBody()?.string()}"))
                    return@launch
                }

                val result = response.body()

                if (result == null) {
                    _apiStateUserRegistration.postValue((ApiState.Error("Empty response from server. Please retry.")))
                    return@launch
                }

                if (result.status == 0) {
                    _apiStateUserRegistration.postValue(ApiState.Success(result))

                }
                else{
                    _apiStateUserRegistration.postValue(ApiState.Error(result.message))
                }


            } catch (e: Exception) {
                e.printStackTrace()
                _apiStateUserRegistration.postValue(ApiState.Error("Error is : $e"))
            }
        }


    }


    //===================================================================

    fun getProductCategories(){
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response: Response<GetProductResponse> = repo.getProductCategories()
                if (!response.isSuccessful){
                    _apiStateProductCategories.postValue(ApiState.Error("Failed to get Products Categories"))
                    return@launch
                }
                val result  = response.body()

                if (result == null) {
                    _apiStateProductCategories.postValue(ApiState.Error("Empty response from server. Please retry."))
                    return@launch
                }
                if (result.status == 0) {

                    _apiStateProductCategories.postValue(ApiState.Success(result))

                }
                else {
                    _apiStateProductCategories.postValue(ApiState.Error(result.message))
                }

            }
            catch (e: Exception){
                e.printStackTrace()
                _apiStateProductCategories.postValue(ApiState.Error("Error is e: $e"))
            }


        }
    }

    //================================================================


    fun getProductSubCategories(categoryId: String){

        viewModelScope.launch(Dispatchers.IO) {

            try {

                val response: Response<SubCategoryResponse> = repo.getProductSubCategories(categoryId)

                if (!response.isSuccessful){
                    _apiStateSubCategories.postValue(ApiState.Error("Failed to get Product Sub Categories"))
                    return@launch
                }

                val result = response.body()
                if (result == null){
                    _apiStateSubCategories.postValue(ApiState.Error("Empty Response from the server. Please retry."))
                    return@launch
                }

                if (result.status == 0){
                    _apiStateSubCategories.postValue(ApiState.Success(result))
                }
                else{
                    _apiStateSubCategories.postValue(ApiState.Error(result.message))
                }
            }
            catch (e: Exception){
                _apiStateSubCategories.postValue(ApiState.Error("Error is e: $e"))
            }

        }



    }

    //===============================================================
    fun getProductsBySubCategoryId(subCategoryId: Int){

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response: Response<SubCategoryProductResponse> = repo.getSubCategoryProductsById(subCategoryId)

                if (!response.isSuccessful){
                    _apiStateSubCategoryProductsById.postValue(ApiState.Error("Failed to get Product Sub Categories"))
                    return@launch
                }

                val result = response.body()
                if (result == null){
                    _apiStateSubCategoryProductsById.postValue(ApiState.Error("Empty Response from the server. Please retry."))
                    return@launch
                }

                if (result.status == 0){
                    _apiStateSubCategoryProductsById.postValue(ApiState.Success(result))
                }
                else{
                    _apiStateSubCategoryProductsById.postValue(ApiState.Error(result.message))
                }
            }
            catch (e: Exception){
                _apiStateSubCategoryProductsById.postValue(ApiState.Error("Error is e: $e"))
            }

        }


    }


    //===============================================================
    fun getProductDetail(productId: Int){

        try {


            viewModelScope.launch(Dispatchers.IO) {

                val response = repo.getProductDetail(productId)
                if (!response.isSuccessful) {
                    _apiStateProductDetail.postValue(ApiState.Error("Failed to get Response from the Server"))
                    return@launch
                }

                val result = response.body()
                if (result == null) {
                    _apiStateProductDetail.postValue(ApiState.Error("Empty Response from the server. Please retry."))
                    return@launch
                }

                if (result.status == 0) {
                    _apiStateProductDetail.postValue(ApiState.Success(result))
                } else {
                    _apiStateProductDetail.postValue(ApiState.Error(result.message))
                }


            }
        }
        catch (e: Exception){
            _apiStateProductDetail.postValue(ApiState.Error("Error e: $e"))
        }
    }

    //===============================================================

    fun getSearchProducts(queryParam: String){

        viewModelScope.launch(Dispatchers.IO) {

            try {

                val response: Response<SubCategoryProductResponse> = repo.searchProducts(queryParam)

                if (!response.isSuccessful){
                    _apiStateSearchProducts.postValue(ApiState.Error("Failed to get Search Products"))
                    return@launch
                }

                val result = response.body()
                if (result == null){
                    _apiStateSearchProducts.postValue(ApiState.Error("Empty Response from the server. Please retry."))
                    return@launch
                }

                if (result.status == 0){
                    _apiStateSearchProducts.postValue(ApiState.Success(result))
                }
                else{
                    _apiStateSearchProducts.postValue(ApiState.Error(result.message))
                }
            }
            catch (e: Exception){
                _apiStateSearchProducts.postValue(ApiState.Error("Error is e: $e"))
            }

        }



    }


    //===================================================================

    fun addDeliveryAddress(address: AddDeliveryAddressRequest){
        viewModelScope.launch(Dispatchers.IO) {

            try {

                val response: Response<AddDeliveryAddressResponse> = repo.addDeliveryAddress(address)

                if (!response.isSuccessful){
                    _apiStateAddAddress.postValue(ApiState.Error("Failed to Add Address"))
                    return@launch
                }

                val result = response.body()
                if (result == null){
                    _apiStateAddAddress.postValue(ApiState.Error("Empty Response from the server. Please retry."))
                    return@launch
                }

                if (result.status == 0){
                    _apiStateAddAddress.postValue(ApiState.Success(result))
                }
                else{
                    _apiStateAddAddress.postValue(ApiState.Error(result.message))
                }
            }
            catch (e: Exception){
                _apiStateAddAddress.postValue(ApiState.Error("Error is e: $e"))
            }


        }
    }



    //===================================================================
    fun getAddresses(userId: Int){
        viewModelScope.launch(Dispatchers.IO) {

            try {

                val response: Response<GetDeliveryAddressResponse> = repo.getDeliveryAddresses(userId)

                if (!response.isSuccessful){
                    _apiStateGetAddress.postValue(ApiState.Error("Failed to Get delivery Addresses"))
                    return@launch
                }

                val result = response.body()
                if (result == null){
                    _apiStateGetAddress.postValue(ApiState.Error("Empty Response from the server. Please retry."))
                    return@launch
                }

                if (result.status == 0){
                    _apiStateGetAddress.postValue(ApiState.Success(result))
                }
                else{
                    _apiStateGetAddress.postValue(ApiState.Error(result.message))
                }
            }
            catch (e: Exception){
                _apiStateGetAddress.postValue(ApiState.Error("Error is e: $e"))
            }


        }
    }

    //=====================================================================
    fun placeFinalOrder(placeOrder: PlaceOrder){
        viewModelScope.launch(Dispatchers.IO) {

            try {

                val response: Response<PlaceOrderResponse> = repo.postPlaceOrder(placeOrder)

                if (!response.isSuccessful){
                    _apiStatePlaceOrder.postValue(ApiState.Error("Failed to place the order"))
                    return@launch
                }

                val result = response.body()
                if (result == null){
                    _apiStatePlaceOrder.postValue(ApiState.Error("Empty Response from the server. Please retry."))
                    return@launch
                }

                if (result.status == 0){
                    _apiStatePlaceOrder.postValue(ApiState.Success(result))
                }
                else{
                    _apiStatePlaceOrder.postValue(ApiState.Error(result.message))
                }
            }
            catch (e: Exception){
                _apiStatePlaceOrder.postValue(ApiState.Error("Error is e: $e"))
            }


        }
    }


}

class MainViewModelFactory(private val repo: Repository): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }


}