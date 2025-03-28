package com.venkata.org.view.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.venkata.org.databinding.ViewHolderSummaryCartItemBinding
import com.venkata.org.model.data.cartItems.CartItem
import com.venkata.org.view.viewHolder.SummaryCartViewHolder

class SummaryCartAdapter(val cartItems: List<CartItem>): Adapter<SummaryCartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryCartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderSummaryCartItemBinding.inflate(inflater, parent, false)
        Log.d(SummaryCartAdapter::class.simpleName, "onCreateView called")

        return SummaryCartViewHolder(binding)
    }

    override fun getItemCount() = cartItems.size

    override fun onBindViewHolder(holder: SummaryCartViewHolder, position: Int) {
        holder.bind(cartItems[position])
        Log.d(SummaryCartAdapter::class.simpleName, "${cartItems[position]}")
    }
}