package com.venkata.org.model.data.cartItems

import androidx.core.content.pm.PermissionInfoCompat.Protection
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cartitems")
data class CartItem(

    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val productId: Int,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "unit_price")
    val unitPrice: Int

)
