package com.venkata.org.view.viewHolder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.venkata.org.databinding.ViewHolderCartItemBinding
import com.venkata.org.model.data.cartItems.CartItem

class CartViewHolder(val binding: ViewHolderCartItemBinding): ViewHolder(binding.root) {

    fun bind(cartItem: CartItem){
        with(binding){
            txtName.text = cartItem.productName
            txtDescription.text = cartItem.description
            txtPrice.text = "$ ${cartItem.unitPrice}"
            txtTotalPrice.text = "$ ${(cartItem.unitPrice*cartItem.quantity)}"
            val url = "https://apolisrises.co.in/myshop/images/${cartItem.imageUrl}"
            Picasso.get().load(url).into(imgImageUrl)
        }
    }
}