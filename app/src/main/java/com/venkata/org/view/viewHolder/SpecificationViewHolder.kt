package com.venkata.org.view.viewHolder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.venkata.org.databinding.ViewHolderSpecificationItemBinding
import com.venkata.org.model.data.productDetail.Specification

class SpecificationViewHolder(val binding: ViewHolderSpecificationItemBinding): ViewHolder(binding.root) {
    fun bind(specification: Specification){
        binding.txtSpecificationTitle.text = specification.title
        binding.txtSpecification.text = specification.specification
    }
}