package com.venkata.org.model.localRepository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import com.venkata.org.model.data.cartItems.CartItem


interface IRepository {
    val error: LiveData<String>
    val cartItems: LiveData<List<CartItem>>

    fun addCartItem(cartItem: CartItem)

    fun getAllCartItems(): LiveData<List<CartItem>>

    fun increaseQuantity(productId: Int)

    fun decreaseQuantity(productId: Int)

    fun deleteIfZero(productId: Int)

    fun getQuantityOfProduct(productId: Int): LiveData<Int>


}