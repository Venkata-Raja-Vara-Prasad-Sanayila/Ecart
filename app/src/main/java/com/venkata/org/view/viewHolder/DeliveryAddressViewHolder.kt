package com.venkata.org.view.viewHolder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.venkata.org.databinding.ViewHolderDeliveryAddressBinding
import com.venkata.org.model.data.getDeliveryAddress.Address

class DeliveryAddressViewHolder(val binding: ViewHolderDeliveryAddressBinding): ViewHolder(binding.root) {

    fun bind(address: Address){
        with(binding){
            txtAddressTitle.text = address.title
            txtAddress.text = address.address
        }
    }

}