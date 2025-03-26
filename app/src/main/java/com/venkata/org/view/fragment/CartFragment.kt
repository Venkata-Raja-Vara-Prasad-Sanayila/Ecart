package com.venkata.org.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.venkata.org.R
import com.venkata.org.databinding.FragmentCartBinding
import com.venkata.org.model.localRepository.AppDatabase
import com.venkata.org.model.localRepository.IRepository
import com.venkata.org.model.localRepository.LocalRepository
import com.venkata.org.view.Adapter.CartAdapter
import com.venkata.org.viewModel.LocalViewModel
import com.venkata.org.viewModel.LocalViewModelFactory

class CartFragment: Fragment() {

    private lateinit var binding: FragmentCartBinding
    lateinit var localViewModel: LocalViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        initDb()
        setupObserver()
        localViewModel.getAllCartItems()


        binding.btnCheckout.setOnClickListener {

            requireActivity().supportFragmentManager.popBackStack()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.drawerLayout, CheckoutFragment())
                .addToBackStack("CartFragment")
                .commit()

        }
        return binding.root
    }

    private fun setupObserver() {
        localViewModel.cartItems.observe(viewLifecycleOwner){

            val adapter = CartAdapter(it, this)
            binding.recyclerCart.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerCart.adapter = adapter

        }
    }

    private fun initDb() {
//            val localRepo: IRepository = LocalRepository(AppDatabase.getInstance(requireContext()))
//            val factoryLocal = LocalViewModelFactory(localRepo)
//            localViewModel = ViewModelProvider(this, factoryLocal)[LocalViewModel::class.java]

        val localRepo: IRepository = LocalRepository(AppDatabase.getInstance(requireContext()))
        val factoryLocal = LocalViewModelFactory(localRepo)
        localViewModel = ViewModelProvider(this, factoryLocal)[LocalViewModel::class.java]

    }
}