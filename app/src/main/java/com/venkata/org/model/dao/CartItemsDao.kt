package com.venkata.org.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.venkata.org.model.data.cartItems.CartItem

@Dao
interface CartItemsDao {

    @Insert
    fun addCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cartitems")
    fun getAllCartItems(): LiveData<List<CartItem>>

    @Query("UPDATE cartitems SET quantity = quantity + 1 WHERE product_id = :productId ")
    fun increaseQuantity(productId: Int)

    @Query("UPDATE cartitems SET quantity = quantity - 1 WHERE product_id = :productId ")
    fun decreaseQuantity(productId: Int)

    @Query("DELETE FROM cartitems WHERE product_id = :productId AND quantity <= 0")
    fun deleteIfZero(productId: Int)

    @Query("SELECT QUANTITY FROM CARTITEMS WHERE product_id = :productId ")
    fun getQuantityOfProduct(productId: Int): LiveData<Int>

}