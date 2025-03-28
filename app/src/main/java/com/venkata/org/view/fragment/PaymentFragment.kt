package com.venkata.org.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.venkata.org.databinding.FragmentPaymentBinding
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.viewModel.MainViewModel
import com.venkata.org.viewModel.MainViewModelFactory

class PaymentFragment: Fragment() {

    private lateinit var binding: FragmentPaymentBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)

        intiView()

        return binding.root
    }

    private fun intiView() {

        val repo = Repository(ApiClient.retrofit.create(ApiService::class.java))
        val factory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(requireActivity(), factory)[MainViewModel::class.java]


        binding.btnNextPayment.setOnClickListener {
            val selectedPayment = when {
                binding.radioCOD.isChecked -> "Cash on Delivery"
                binding.radioIB.isChecked -> "Internet Banking"
                binding.radioDCCC.isChecked -> "Debit/Credit Card"
                binding.radioPP.isChecked -> "PayPal"
                else -> null
            }

            if (selectedPayment != null) {
                viewModel.selectedPaymentMode.value = selectedPayment
            } else {
                Toast.makeText(requireContext(), "Please select a payment method", Toast.LENGTH_SHORT).show()
            }
        }

    }

}