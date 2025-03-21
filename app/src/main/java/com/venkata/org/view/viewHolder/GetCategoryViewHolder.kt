package com.venkata.org.view.viewHolder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.venkata.org.databinding.ViewHolderCategoryItemBinding
import com.venkata.org.model.data.getProduct.Category

class GetCategoryViewHolder(val binding: ViewHolderCategoryItemBinding):ViewHolder(binding.root) {

    fun bind(category: Category) {
        with(binding){

            txtCategory.text = category.name
            val url = "https://apolisrises.co.in/myshop/images/${category.imageUrl}"
            Picasso.get().load(url).into(binding.imgCategory)


        }
    }
}