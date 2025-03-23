package com.venkata.org.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.venkata.org.databinding.ViewHolderSpecificationItemBinding
import com.venkata.org.model.data.productDetail.Specification
import com.venkata.org.view.viewHolder.SpecificationViewHolder

class SpecificationAdapter(val specifications: List<Specification>): Adapter<SpecificationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificationViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderSpecificationItemBinding.inflate(inflater, parent, false)
        return SpecificationViewHolder(binding)
    }

    override fun getItemCount() = specifications.size

    override fun onBindViewHolder(holder: SpecificationViewHolder, position: Int) {
        holder.bind(specifications[position])
    }
}