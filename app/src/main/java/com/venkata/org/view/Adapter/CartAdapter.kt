package com.venkata.org.view.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.venkata.org.Adapter.SubCategoryProductAdapter
import com.venkata.org.databinding.ViewHolderCartItemBinding
import com.venkata.org.databinding.ViewHolderCategoryItemBinding
import com.venkata.org.model.data.cartItems.CartItem
import com.venkata.org.model.localRepository.AppDatabase
import com.venkata.org.model.localRepository.IRepository
import com.venkata.org.model.localRepository.LocalRepository
import com.venkata.org.view.viewHolder.CartViewHolder
import com.venkata.org.viewModel.LocalViewModel
import com.venkata.org.viewModel.LocalViewModelFactory

class CartAdapter(val cartItems: List<CartItem>,private val lifecycleOwner: LifecycleOwner): Adapter<CartViewHolder>() {
    private lateinit var localViewModel: LocalViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCartItemBinding.inflate(inflater, parent, false)

        val localRepo: IRepository = LocalRepository(AppDatabase.getInstance(parent.context))
        val factoryLocal = LocalViewModelFactory(localRepo)
        localViewModel = ViewModelProvider(parent.context as ViewModelStoreOwner, factoryLocal)[LocalViewModel::class.java]

        return CartViewHolder(binding)
    }

    override fun getItemCount() = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem  = cartItems[position]
        holder.bind(cartItem)


        val productID = cartItem.productId

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





    }





}