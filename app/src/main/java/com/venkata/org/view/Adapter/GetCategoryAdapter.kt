package com.venkata.org.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.venkata.org.view.viewHolder.GetCategoryViewHolder
import com.venkata.org.databinding.ViewHolderCategoryItemBinding
import com.venkata.org.model.data.getProduct.Category

class GetCategoryAdapter(val categories: List<Category>): Adapter<GetCategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetCategoryViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCategoryItemBinding.inflate(layoutInflater, parent, false)

        return GetCategoryViewHolder(binding)
    }

    override fun getItemCount() =  categories.size
    override fun onBindViewHolder(holder: GetCategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)

        holder.itemView.setOnClickListener {
            Log.d("SubCat", "inside ItemVire")

            if (::onSelectItem.isInitialized){
                Log.d("SubCat", "inside ItemVire If")
                onSelectItem(category, position)
            }



        }
    }


    private lateinit var onSelectItem:(Category, Int)->Unit
    fun onSelectedItemClicked(listener: (Category, Int)->Unit){
        onSelectItem = listener
    }
}