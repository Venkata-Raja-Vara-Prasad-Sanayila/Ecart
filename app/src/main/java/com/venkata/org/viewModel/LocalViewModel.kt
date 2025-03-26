package com.venkata.org.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.venkata.org.model.data.cartItems.CartItem
import com.venkata.org.model.localRepository.IRepository

class LocalViewModel(private val repo: IRepository): ViewModel() {


    val error: LiveData<String> = repo.error

    val cartItems: LiveData<List<CartItem>> = repo.cartItems

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