package com.venkata.org.view.viewHolder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.venkata.org.databinding.ViewHolderProductBinding
import com.venkata.org.model.data.subCategoryProducts.SubCategoryProduct

class SubCategoryProductViewHolder(val binding: ViewHolderProductBinding): ViewHolder(binding.root) {

    fun bind(subCategoryProduct: SubCategoryProduct){

        with(binding){
            txtName.text = subCategoryProduct.name
            txtDescription.text = subCategoryProduct.description
            txtPrice.text = "$ ${subCategoryProduct.price}"
            val url = "https://apolisrises.co.in/myshop/images/samsung_21_plus.png"//${subCategoryProduct.imageUrl}"
            Picasso.get().load(url).into(binding.imgImageUrl)
        }

    }
}