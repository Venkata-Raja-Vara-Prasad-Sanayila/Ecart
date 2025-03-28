package com.venkata.org.view.Adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.venkata.org.databinding.ViewHolderDeliveryAddressBinding
import com.venkata.org.model.data.getDeliveryAddress.Address
import com.venkata.org.view.viewHolder.DeliveryAddressViewHolder

class DeliveryAddressAdapter(val addresses: List<Address>): Adapter<DeliveryAddressViewHolder>() {
    private lateinit var binding: ViewHolderDeliveryAddressBinding
    private var selectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryAddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        binding = ViewHolderDeliveryAddressBinding.inflate(inflater, parent, false)

        return DeliveryAddressViewHolder(binding)

    }

    override fun getItemCount() = addresses.size

    override fun onBindViewHolder(holder: DeliveryAddressViewHolder, position: Int) {
        val address = addresses[position]
        holder.bind(address)


        holder.binding.radioButton.isChecked = position == selectedPosition


        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
//            holder.binding.radioButton.performClick()
//            notifyDataSetChanged()
            if (::onSelectAddress.isInitialized){
                onSelectAddress(address, position)
            }
        }
    }


    lateinit var onSelectAddress:(Address, Int)-> Unit
    fun onClickSelectedAddress(listener:(Address, Int)->Unit){
        onSelectAddress = listener
    }



}