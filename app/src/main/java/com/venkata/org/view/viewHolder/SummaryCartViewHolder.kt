package com.venkata.org.view.viewHolder

import android.util.Log
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.venkata.org.databinding.ViewHolderSummaryCartItemBinding
import com.venkata.org.model.data.cartItems.CartItem

class SummaryCartViewHolder(val binding: ViewHolderSummaryCartItemBinding): ViewHolder(binding.root) {

    fun bind(cartItem: CartItem){
        with(binding){
            Log.d(SummaryCartViewHolder::class.simpleName, "$cartItem")
            txtProductNameS.text = cartItem.productName
            txtProductPriceS.text = "Unit Price    ${cartItem.unitPrice}"
            txtProductQuantityS.text = "Quantity    ${cartItem.quantity}"
            txtProductAmountS.text = "Amount    $ ${cartItem.quantity*cartItem.unitPrice}"
//            val url = "https://apolisrises.co.in/myshop/images/${cartItem.imageUrl}"
//            Picasso.get().load(url).into(imgProduct)
        }

    }
}