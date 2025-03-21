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
import com.venkata.org.Adapter.SubCategoryProductAdapter
import com.venkata.org.databinding.FragmentSubCategoryViewsBinding
import com.venkata.org.model.commons.ApiState
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.model.data.subCategory.SubCategory
import com.venkata.org.viewModel.MainViewModel
import com.venkata.org.viewModel.MainViewModelFactory

class SubCategoryViewsFragment: Fragment() {

    private lateinit var binding: FragmentSubCategoryViewsBinding
    private lateinit var adapter: SubCategoryProductAdapter
    private lateinit var viewModel: MainViewModel

    companion object {
        const val ARGS_NAME = "SubCategory"

        fun newInstance(subCategory: SubCategory): SubCategoryViewsFragment {

            val subCategoryViewsFragment  = SubCategoryViewsFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARGS_NAME, subCategory)
            subCategoryViewsFragment.arguments = bundle

            return subCategoryViewsFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubCategoryViewsBinding.inflate(inflater, container, false)


        initView()

        return binding.root
    }

    private fun initView() {

        intialiseViewModel()

        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        getSubCatProductsByID()
    }

    fun getSubCatProductsByID() {


        val subCategory = arguments?.getParcelable<SubCategory>(ARGS_NAME)
        Log.d(SubCategoryViewsFragment::class.java.simpleName, "${subCategory.toString()}")
        subCategory?.id?.let { viewModel.getProductsBySubCategoryId(it) }

    }

    private fun setupObserver() {
        viewModel.apiStateSubCategoryProductsById.observe(viewLifecycleOwner) {
            when(it) {


                is ApiState.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                is ApiState.Success -> {

                    adapter = SubCategoryProductAdapter(it.data.subCategoryProducts)
                    binding.recycler.layoutManager = LinearLayoutManager(context)
                    binding.recycler.adapter = adapter

                }
            }
        }
    }

    private fun intialiseViewModel() {
        val repo = Repository(ApiClient.retrofit.create(ApiService::class.java))
        val factory = MainViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }
}