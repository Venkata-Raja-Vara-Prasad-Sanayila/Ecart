package com.venkata.org.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.venkata.org.view.viewHolder.SubCategoryProductViewHolder
import com.venkata.org.databinding.ViewHolderProductBinding
import com.venkata.org.model.data.subCategoryProducts.SubCategoryProduct

class SubCategoryProductAdapter(val subCategoryProducts: MutableList<SubCategoryProduct>): Adapter<SubCategoryProductViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubCategoryProductViewHolder {
        val binding = ViewHolderProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SubCategoryProductViewHolder(binding)
    }

    override fun getItemCount() = subCategoryProducts.size

    override fun onBindViewHolder(holder: SubCategoryProductViewHolder, position: Int) {
        val subCategoryProduct = subCategoryProducts[position]
        holder.bind(subCategoryProduct)
        holder.itemView.setOnClickListener {
            if (::onSelectItem.isInitialized) {
                onSelectItem(subCategoryProduct, position)
            }
        }
    }



    lateinit var onSelectItem:(SubCategoryProduct, Int)-> Unit
    fun onClickSelectedItem(listener:(SubCategoryProduct, Int)->Unit){
        onSelectItem = listener
    }

}