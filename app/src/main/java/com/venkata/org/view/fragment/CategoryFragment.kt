package com.venkata.org.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.venkata.org.Adapter.GetCategoryAdapter
import com.venkata.org.R
import com.venkata.org.databinding.FragmentCategoryBinding
import com.venkata.org.model.commons.ApiState
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.model.sharedPreference.SharedPreferenceManager
import com.venkata.org.view.SplashActivity
import com.venkata.org.viewModel.MainViewModel
import com.venkata.org.viewModel.MainViewModelFactory

class CategoryFragment: Fragment(){

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: GetCategoryAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    private fun initViews() {
        initViewModel()
        viewModel.getProductCategories()
        setupObservers()

        binding.txtBtnLogout.setOnClickListener {
            SharedPreferenceManager.clearPreference()
            startActivity(Intent(context, SplashActivity::class.java))
            requireActivity().finish()
        }

    }

    private fun setupObservers() {
        viewModel.apiStateProductCategories.observe(viewLifecycleOwner){
            when(it){
                is ApiState.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                is ApiState.Success -> {

                    adapter = GetCategoryAdapter(it.data.categories)
                    binding.recyclerCategories.layoutManager = GridLayoutManager(context, 2)
                    binding.recyclerCategories.adapter = adapter

                    adapter.onSelectedItemClicked { category, i ->
                        Log.d("SubCat", "inside cate init")
                        val subCategoryFragment = SubCategoryFragment()
                        val bundle = Bundle()
                        bundle.putParcelable("categoryClicked", category)
                        subCategoryFragment.arguments = bundle
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.mainContainer, subCategoryFragment)
                            .addToBackStack("CategoryFragment")
                            .commit()
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
