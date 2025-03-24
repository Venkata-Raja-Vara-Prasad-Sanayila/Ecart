package com.venkata.org.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.venkata.org.view.viewHolder.SubCategoryProductViewHolder
import com.venkata.org.databinding.ViewHolderProductBinding
import com.venkata.org.model.data.cartItems.CartItem
import com.venkata.org.model.data.subCategoryProducts.SubCategoryProduct
import com.venkata.org.model.localRepository.AppDatabase
import com.venkata.org.model.localRepository.IRepository
import com.venkata.org.model.localRepository.LocalRepository
import com.venkata.org.viewModel.LocalViewModel
import com.venkata.org.viewModel.LocalViewModelFactory


class SubCategoryProductAdapter(val subCategoryProducts: MutableList<SubCategoryProduct>,
                                private val lifecycleOwner: LifecycleOwner): Adapter<SubCategoryProductViewHolder>() {

    private lateinit var localViewModel: LocalViewModel
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubCategoryProductViewHolder {
        val binding = ViewHolderProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val localRepo: IRepository = LocalRepository(AppDatabase.getInstance(parent.context))
        val factoryLocal = LocalViewModelFactory(localRepo)
        localViewModel = ViewModelProvider(parent.context as ViewModelStoreOwner, factoryLocal)[LocalViewModel::class.java]


        return SubCategoryProductViewHolder(binding)
    }

    override fun getItemCount() = subCategoryProducts.size

    override fun onBindViewHolder(holder: SubCategoryProductViewHolder, position: Int) {
        val subCategoryProduct = subCategoryProducts[position]
        val cartItem = CartItem(subCategoryProduct.id, 0, subCategoryProduct.price)
        holder.bind(subCategoryProduct)
        val productID = subCategoryProduct.id

        with(holder.binding) {
            txtAddToCart.setOnClickListener {
                Log.d(SubCategoryProductAdapter::class.simpleName, "inside txtcart clicked")
                txtAddToCart.visibility = View.GONE
                txtQuantityBlock.visibility = View.VISIBLE
                localViewModel.addCartItem(cartItem)
                localViewModel.increaseQuantity(productID)
                localViewModel.getQuantityOfProduct(productID)
                //binding.txtQuantityNumber.text = localViewModel.getQuantityOfProduct(productID).toString()
            }
            imgBtnQuantityIncrease.setOnClickListener {
                localViewModel.increaseQuantity(productID)
                localViewModel.getQuantityOfProduct(productID)
            }
            imgBtnQuantityDecrease.setOnClickListener {
                localViewModel.decreaseQuantity(productID)
                localViewModel.getQuantityOfProduct(productID)
            }
        }

        localViewModel.getQuantityOfProduct(productID).observe(lifecycleOwner){
            if (it == 0){
                holder.binding.txtQuantityBlock.visibility = View.GONE
                holder.binding.txtAddToCart.visibility = View.VISIBLE
            }
            holder.binding.txtQuantityNumber.text = it.toString()
        }


//
//        holder.binding.txtAddToCart.setOnClickListener {
//            if (::onAddItemCart.isInitialized){
//                val cartItem = CartItem(subCategoryProduct.id, 0 , subCategoryProduct.price)
//                onAddItemCart(cartItem, position)
//            }
//        }

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


//    lateinit var onAddItemCart:(CartItem, Int)-> Unit
//    fun onClickAddItemCart(listener:(CartItem, Int)->Unit){
//        onAddItemCart = listener
//    }



}