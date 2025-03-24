package com.venkata.org.view.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewpager2.widget.ViewPager2
import com.venkata.org.databinding.ViewHolderImageCarouselBinding
import com.venkata.org.model.data.productDetail.ImageDisplay
import com.venkata.org.view.viewHolder.ImageCarouselViewHolder

class ImageCarouselViewPagerAdapter(val images: List<ImageDisplay>): Adapter<ImageCarouselViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderImageCarouselBinding.inflate(inflater, parent, false)
        return ImageCarouselViewHolder(binding)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ImageCarouselViewHolder, position: Int) {
        holder.bind(images[position])
    }
}