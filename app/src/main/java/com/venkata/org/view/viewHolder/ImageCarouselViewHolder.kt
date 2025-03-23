package com.venkata.org.view.viewHolder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.venkata.org.R
import com.venkata.org.databinding.ViewHolderImageCarouselBinding
import com.venkata.org.model.data.productDetail.ImageDisplay

class ImageCarouselViewHolder(val binding: ViewHolderImageCarouselBinding): ViewHolder(binding.root) {
    fun bind(image: ImageDisplay){

//        val url = "https://apolisrises.co.in/myshop/images/${category.imageUrl}"
//        Picasso.get().load(url).into(binding.imgCategory)
        val url = "https://apolisrises.co.in/myshop/images/${image.imagePath}"
//        Picasso.get().load(url).into(binding.imgView)
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_star)
            .into(binding.imgView)


    }
}