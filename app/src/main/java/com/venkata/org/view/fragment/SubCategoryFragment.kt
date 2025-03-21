package com.venkata.org.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.venkata.org.Adapter.SubCategoryViewPageAdapter
import com.venkata.org.databinding.FragmentSubCategoryBinding
import com.venkata.org.model.commons.ApiState
import com.venkata.org.model.data.getProduct.Category
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.viewModel.MainViewModel
import com.venkata.org.viewModel.MainViewModelFactory

class SubCategoryFragment: Fragment() {

    private lateinit var binding: FragmentSubCategoryBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSubCategoryBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {

        initViewModel()

        val category = arguments?.getParcelable<Category>("categoryClicked")
        val categoryId = category?.id.toString()
        viewModel.getProductSubCategories(categoryId)

        setupObservers()

    }

    private fun setupObservers() {

        viewModel.apiStateSubCategories.observe(viewLifecycleOwner){
            when(it){
                is ApiState.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                is ApiState.Success -> {

                    val subCategories = it.data.subCategories.toMutableList()
                    with(binding) {
                        val fragments = subCategories.map { SubCategoryViewsFragment.newInstance(it) }
                        val adapter =
                            SubCategoryViewPageAdapter(fragments, childFragmentManager, lifecycle)
                        viewPager.adapter = adapter

                        TabLayoutMediator(tabsLayout, viewPager) { tab, position ->
                            tab.text = subCategories[position].name
//                            viewPager.registerOnPageChangeCallback(object :
//                                ViewPager2.OnPageChangeCallback() {
//                                override fun onPageSelected(position: Int) {
//                                    fragments[position].getSubCatProductsByID()
//
//                                }
//                            })
                        }.attach()
                    }

                }
            }
        }
    }

    private fun initViewModel() {
        val repo = Repository(ApiClient.retrofit.create(ApiService::class.java))
        val factory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

}