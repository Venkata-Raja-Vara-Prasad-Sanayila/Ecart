package com.venkata.org.model.localRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.venkata.org.model.data.cartItems.CartItem

class LocalRepository(private val appDb: AppDatabase): IRepository {

    private val _error = MutableLiveData<String>()
    override val error: LiveData<String> = _error

    override val cartItems: LiveData<List<CartItem>> = getAllCartItems()

    override fun addCartItem(cartItem: CartItem) {
        try {
            appDb.cartItemDao.addCartItem(cartItem)
        }
        catch (e: Exception){
            _error.postValue("Error is e: $e")
        }
    }

    override fun getAllCartItems(): LiveData<List<CartItem>> {
        try {
           return appDb.cartItemDao.getAllCartItems()
        }
        catch (e: Exception){
            _error.postValue("Error is e: $e")
            return MutableLiveData(emptyList<CartItem>())
        }
    }

    override fun increaseQuantity(productId: Int) {
        try {
            appDb.cartItemDao.increaseQuantity(productId)
        }
        catch (e: Exception){
            _error.postValue("Error is e: $e")
        }
    }

    override fun decreaseQuantity(productId: Int) {
        try {
            appDb.cartItemDao.decreaseQuantity(productId)
        }
        catch (e: Exception){
            _error.postValue("Error is e: $e")
        }
    }

    override fun deleteIfZero(productId: Int) {
        try {
            appDb.cartItemDao.deleteIfZero(productId)
        }
        catch (e: Exception){
            _error.postValue("Error is e: $e")
        }
    }

    override fun getQuantityOfProduct(productId: Int): LiveData<Int> {
        try {
            return appDb.cartItemDao.getQuantityOfProduct(productId)
        }
        catch (e: Exception){
            _error.postValue("Error is e: $e")
            return MutableLiveData(-1)
        }
    }


}