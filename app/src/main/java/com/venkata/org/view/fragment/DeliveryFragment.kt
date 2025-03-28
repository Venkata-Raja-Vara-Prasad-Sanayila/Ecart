package com.venkata.org.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.venkata.org.databinding.FragmentDeliveryBinding
import com.venkata.org.model.commons.ApiState
import com.venkata.org.model.data.deliveryAddress.AddDeliveryAddressRequest
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.view.Adapter.DeliveryAddressAdapter
import com.venkata.org.view.dialog.AddressDialog
import com.venkata.org.viewModel.MainViewModel
import com.venkata.org.viewModel.MainViewModelFactory

class DeliveryFragment: Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentDeliveryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryBinding.inflate(inflater, container, false)

        initViewModel()
        setupObservers()
        initView()

//        initTrail()

        return binding.root
    }



    private fun initView() {

        viewModel.getAddresses(444)



        with(binding){
            btnAddAddress.setOnClickListener {

                val addressDialog = AddressDialog(requireContext(), viewLifecycleOwner,this@DeliveryFragment)
                addressDialog.show()

                addressDialog.onClickSaveAddress { title, address ->
                    Log.d(DeliveryFragment::class.simpleName, "$title, $address")
                    viewModel.addDeliveryAddress(AddDeliveryAddressRequest(444, title, address))
                }

            }
        }
    }

    private fun setupObservers() {

        viewModel.apiStateAddAddress.observe(viewLifecycleOwner){
            when(it){
                is ApiState.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is ApiState.Success -> {
                    Toast.makeText(context, it.data.message, Toast.LENGTH_SHORT).show()
                    viewModel.getAddresses(444)
                }
            }
        }


        viewModel.apiStateGetAddress.observe(viewLifecycleOwner){
            when(it){
                is ApiState.Error -> {

                }
                is ApiState.Success -> {
                    val adapter = DeliveryAddressAdapter(it.data.addresses)
                    binding.recyclerAddress.adapter = adapter
                    adapter.onClickSelectedAddress { address, i ->
                        viewModel.selectedShippingAddress.value = address
                    }

                }
            }
        }
    }


    private fun initViewModel() {
        val repo = Repository(ApiClient.retrofit.create(ApiService::class.java))
        val factory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]

    }


}