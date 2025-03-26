package com.venkata.org.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.venkata.org.Adapter.SubCategoryViewPageAdapter
import com.venkata.org.databinding.FragmentCheckoutBinding

class CheckoutFragment: Fragment() {

    private lateinit var binding: FragmentCheckoutBinding
    private lateinit var viewPagerAdapter: SubCategoryViewPageAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false)

        initView()
        return binding.root
    }

    private fun initView() {

        with(binding){
            val fragments = listOf(CartFragment(), DeliveryFragment(), PaymentFragment(), SummaryFragment())

            viewPagerAdapter = SubCategoryViewPageAdapter(fragments, requireActivity().supportFragmentManager, lifecycle )

            viewPagerCheckout.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayoutCheckout, viewPagerCheckout){
              tab, position ->
                tab.text = when(position) {
                    0 -> "Cart Items"
                    1 -> "Delivery"
                    2 -> "Payment"
                    3 -> "Summary"
                    else -> ""
                }
            }.attach()



        }



    }
}