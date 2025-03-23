package com.venkata.org.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.venkata.org.databinding.FragmentProductDetailBinding
import com.venkata.org.model.commons.ApiState
import com.venkata.org.model.data.cartItems.CartItem
import com.venkata.org.model.data.productDetail.ProductInfo
import com.venkata.org.model.localRepository.AppDatabase
import com.venkata.org.model.localRepository.IRepository
import com.venkata.org.model.localRepository.LocalRepository
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.view.Adapter.ImageCarouselViewPagerAdapter
import com.venkata.org.view.Adapter.SpecificationAdapter
import com.venkata.org.viewModel.LocalViewModel
import com.venkata.org.viewModel.LocalViewModelFactory
import com.venkata.org.viewModel.MainViewModel
import com.venkata.org.viewModel.MainViewModelFactory

class ProductDetailFragment: Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var localViewModel: LocalViewModel
    lateinit var cartItem: CartItem



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        intiView()
        return binding.root
    }

    private fun intiView() {
        initViewModel()
        arguments?.let { viewModel.getProductDetail(it.getInt("productId")) }

        setupObserve()

        val productID = arguments?.getInt("productId").toString().toInt()

        binding.txtCart.setOnClickListener {
            binding.txtCart.visibility = View.GONE
            binding.txtQuantityBlock.visibility = View.VISIBLE
            localViewModel.addCartItem(cartItem)
            localViewModel.increaseQuantity(productID)
            localViewModel.getQuantityOfProduct(productID)
            //binding.txtQuantityNumber.text = localViewModel.getQuantityOfProduct(productID).toString()
        }
        binding.imgBtnQuantityIncrease.setOnClickListener {
            localViewModel.increaseQuantity(productID)
            localViewModel.getQuantityOfProduct(productID)
        }
        binding.imgBtnQuantityDecrease.setOnClickListener {
            localViewModel.decreaseQuantity(productID)
            localViewModel.getQuantityOfProduct(productID)
        }
    }

    private fun setupObserve() {
        viewModel.apiStateProductDetail.observe(viewLifecycleOwner){
            when(it){
                is ApiState.Error -> {
                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is ApiState.Success -> {
                    val product = it.data.product
                    cartItem = CartItem(product.productId, 0, product.price)
                    Log.d(ProductDetailFragment::class.simpleName, "$product")
                    bindView(product)

                }
            }
        }


        val productID = arguments?.getInt("productId").toString().toInt()
        localViewModel.getQuantityOfProduct(productID).observe(viewLifecycleOwner){
            binding.txtQuantityNumber.text = it.toString()
        }

    }

    private fun bindView(product: ProductInfo) {

        with(binding){
            txtProductName.text = product.productName
            txtProductDescription.text = product.description
            txtPrice.text = product.price.toString()
            imgCarousel.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            imgCarousel.adapter = ImageCarouselViewPagerAdapter(product.images)
            recyclerSpecification.adapter = SpecificationAdapter(product.specifications)
        }


    }

    private fun initViewModel() {

        val repo = Repository(ApiClient.retrofit.create(ApiService::class.java))
        val factory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]



        val localRepo: IRepository = LocalRepository(AppDatabase.getInstance(requireContext()))
        val factoryLocal = LocalViewModelFactory(localRepo)
        localViewModel = ViewModelProvider(this, factoryLocal)[LocalViewModel::class.java]
    }
}