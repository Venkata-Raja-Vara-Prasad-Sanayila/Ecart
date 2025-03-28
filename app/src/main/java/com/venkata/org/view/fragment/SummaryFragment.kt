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
import com.venkata.org.databinding.FragmentSummaryBinding
import com.venkata.org.model.commons.ApiState
import com.venkata.org.model.data.cartItems.CartItem
import com.venkata.org.model.data.getDeliveryAddress.Address
import com.venkata.org.model.data.place_order.OrderAddress
import com.venkata.org.model.data.place_order.OrderItem
import com.venkata.org.model.data.place_order.PlaceOrder
import com.venkata.org.model.localRepository.AppDatabase
import com.venkata.org.model.localRepository.IRepository
import com.venkata.org.model.localRepository.LocalRepository
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.view.Adapter.SummaryCartAdapter
import com.venkata.org.viewModel.LocalViewModel
import com.venkata.org.viewModel.LocalViewModelFactory
import com.venkata.org.viewModel.MainViewModel
import com.venkata.org.viewModel.MainViewModelFactory

class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var localViewModel: LocalViewModel
    private lateinit var orderItems: List<OrderItem>
    private var totalBillAmount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSummaryBinding.inflate(inflater, container, false)

        initViewModel()
        setupObservers()
//        setupRecyclerView()
        localViewModel.getAllCartItems()

        initOrder()

        return binding.root
    }

    private fun initOrder() {
        binding.btnConfirmOrder.setOnClickListener {
            val orderAddress = viewModel.selectedShippingAddress.value?.let { it ->
                OrderAddress(it.title, it.address
                )
            }?:OrderAddress("NA", "NA")


            val placeOrder = PlaceOrder(444, orderAddress, orderItems, totalBillAmount.toLong(),
                viewModel.selectedShippingAddress.toString()
            )
            viewModel.placeFinalOrder(placeOrder)

        }
    }

//    private fun setupRecyclerView() {
//        val cartItems = mutableListOf<CartItem>()
//        cartItems.add(CartItem(productId=1, quantity=10, unitPrice=745, productName="One Plus 9 Pro", description="5G, OS: Android 10, RAM: 16 GB, Internal Storage: 256 GB, Primary Camera: 64 MP, Secondary Camera: 13 MP", imageUrl="one_plus_9_pro.png"))
//        binding.recyclerSummaryCartS.layoutManager = LinearLayoutManager(context)
//        binding.recyclerSummaryCartS.adapter = SummaryCartAdapter(cartItems)
//    }

    private fun setupObservers() {
        localViewModel.cartItems.observe(viewLifecycleOwner) { cartItems ->
            Log.d(SummaryFragment::class.simpleName, cartItems.toString())
//            val cartItems = mutableListOf<CartItem>()
//            cartItems.add(CartItem(productId=1, quantity=10, unitPrice=745, productName="One Plus 9 Pro", description="5G, OS: Android 10, RAM: 16 GB, Internal Storage: 256 GB, Primary Camera: 64 MP, Secondary Camera: 13 MP", imageUrl="one_plus_9_pro.png"))
            val adapter = SummaryCartAdapter(cartItems)
            binding.recyclerSummaryCartS.adapter = adapter

            orderItems = cartItems.map { OrderItem(it.productId, it.quantity, it.unitPrice) }
            totalBillAmount = cartItems.sumOf { it.unitPrice*it.quantity }

            binding.txtTotalPriceCartItemsS.text = totalBillAmount.toString()

        }

        viewModel.selectedShippingAddress.observe(viewLifecycleOwner){
            binding.txtAddressTitleS.text = it.title
            binding.txtAddressS.text = it.address
        }

        viewModel.selectedPaymentMode.observe(viewLifecycleOwner){
            binding.txtPaymentTypeS.text = it
        }

        viewModel.apiStatePlaceOrder.observe(viewLifecycleOwner){
            when(it){
                is ApiState.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is ApiState.Success -> {
                    with(binding){
                        linearOrderId.visibility = View.VISIBLE
                        linearOrderstatus.visibility = View.VISIBLE
                        txtOrderId.text = "# ${it.data.orderId}"
                        btnConfirmOrder.text = "Cancel Order"
                        btnConfirmOrder.isEnabled = false
                    }
                }
            }
        }


    }

    private fun initViewModel() {
        val repo = Repository(ApiClient.retrofit.create(ApiService::class.java))
        val factory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

        val localRepo: IRepository = LocalRepository(AppDatabase.getInstance(requireContext()))
        val factoryLocal = LocalViewModelFactory(localRepo)
        localViewModel = ViewModelProvider(this, factoryLocal)[LocalViewModel::class.java]
    }


    override fun onResume() {
        super.onResume()
        initViewModel()
//        setupObservers()
//        setupRecyclerView()
//        localViewModel.getAllCartItems()

    }
}