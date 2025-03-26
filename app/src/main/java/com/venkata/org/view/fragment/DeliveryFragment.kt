package com.venkata.org.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.venkata.org.databinding.FragmentDeliveryBinding
import com.venkata.org.view.dialog.AddressDialog

class DeliveryFragment: Fragment() {

    private lateinit var binding: FragmentDeliveryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }

    private fun initView() {



        with(binding){
            btnAddAddress.setOnClickListener {

                val addressDialog = AddressDialog(requireContext(), this@DeliveryFragment)
                addressDialog.show()

            }
        }
    }
}