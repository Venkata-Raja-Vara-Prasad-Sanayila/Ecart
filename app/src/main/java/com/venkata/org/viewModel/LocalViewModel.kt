package com.venkata.org.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.venkata.org.model.data.cartItems.CartItem
import com.venkata.org.model.data.getDeliveryAddress.Address
import com.venkata.org.model.localRepository.IRepository

class LocalViewModel(private val repo: IRepository): ViewModel() {



    val error: LiveData<String> = repo.error

    val cartItems: LiveData<List<CartItem>> = repo.cartItems

    val totalPrice: LiveData<Double> = cartItems.map { item ->
        item?.sumOf { it.quantity * it.unitPrice }?.toDouble() ?: 0.0
    }

    fun addCartItem(cartItem: CartItem){
        repo.addCartItem(cartItem)
    }

    fun getAllCartItems(){
        repo.getAllCartItems()
    }

    fun increaseQuantity(productID: Int){
        repo.increaseQuantity(productID)
    }

    fun decreaseQuantity(productID: Int){
        repo.decreaseQuantity(productID)
        repo.deleteIfZero(productID)
    }

    fun getQuantityOfProduct(productID: Int): LiveData<Int>{
         return repo.getQuantityOfProduct(productID)
    }

}



class LocalViewModelFactory(private val repo: IRepository): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocalViewModel(repo) as T
    }

}