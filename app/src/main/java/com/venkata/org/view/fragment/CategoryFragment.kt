package com.venkata.org.view.fragment

//import android.view.MenuItem
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.GravityCompat


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.venkata.org.Adapter.GetCategoryAdapter
import com.venkata.org.Adapter.SubCategoryProductAdapter
import com.venkata.org.R
import com.venkata.org.databinding.FragmentCategoryBinding
import com.venkata.org.databinding.NavigationHeaderMenuBinding
import com.venkata.org.model.commons.ApiState
import com.venkata.org.model.data.subCategoryProducts.SubCategoryProduct
import com.venkata.org.model.data.subCategoryProducts.SubCategoryProductResponse
import com.venkata.org.model.localRepository.AppDatabase
import com.venkata.org.model.localRepository.IRepository
import com.venkata.org.model.localRepository.LocalRepository
import com.venkata.org.model.remote.ApiClient
import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.remote.Repository
import com.venkata.org.model.sharedPreference.SharedPreferenceManager
import com.venkata.org.view.SplashActivity
import com.venkata.org.viewModel.LocalViewModel
import com.venkata.org.viewModel.LocalViewModelFactory
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
//            SharedPreferenceManager.clearPreference()
//            startActivity(Intent(context, SplashActivity::class.java))
//            requireActivity().finish()
        }

        //-------------

        binding.imgBtnMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
            //val menuBinding:NavigationHeaderMenuBinding = NavigationHeaderMenuBinding
            val menuBinding:NavigationHeaderMenuBinding = NavigationHeaderMenuBinding.bind(binding.navView.getHeaderView(0))

            val user = SharedPreferenceManager.getUser(SharedPreferenceManager.KEY_USER)
            menuBinding.txtMenuName.text = user?.name
            menuBinding.txtMenuEmail.text = user?.emailId
            menuBinding.txtMenuMobile.text = user?.mobileNo

        }

        binding.navView.setNavigationItemSelectedListener {
            items->
//            items.isChecked = true
            when(items.itemId){

                R.id.menu_home ->{
                    //requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.drawerLayout, CategoryFragment())
                        .commit()

                }

                R.id.menu_cart->{

                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayoutCategory, CartFragment())
                        .addToBackStack("CartFragment")
                        .commit()

                }
                R.id.menu_orders -> {

                }
                R.id.imgBtnMenu -> {

                }

                R.id.menu_logout -> {
                    SharedPreferenceManager.clearPreference()
                    startActivity(Intent(context, SplashActivity::class.java))
                    requireActivity().finish()
                }


            }
            true

        }

        //----------------------

        binding.imgBtnSearch.setOnClickListener {
            binding.linearSearch.visibility = View.VISIBLE
        }

        binding.imgBtnSearchProduct.setOnClickListener {
            val searchText = binding.edtTextSearch.text.toString()
//            if (searchTe)
            Log.d(CategoryFragment::class.simpleName, "imgBtnClickd")
            viewModel.getSearchProducts(searchText)
        }

        binding.imgBtnCancel.setOnClickListener {
            binding.edtTextSearch.text.clear()
            binding.linearSearch.visibility = View.GONE
            viewModel.getProductCategories()
        }

    }

    private fun setupObservers() {
        viewModel.apiStateProductCategories.observe(viewLifecycleOwner){
            when(it){
                is ApiState.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                is ApiState.Success -> {

                    adapter = GetCategoryAdapter(it.data.categories)
                    binding.recyclerCategories.layoutManager = GridLayoutManager(requireContext(), 2)
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

        viewModel.apiStateSearchProducts.observe(viewLifecycleOwner){

            when(it){
                is ApiState.Error -> Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                is ApiState.Success -> {
                    val searchProductsAdapter = SubCategoryProductAdapter(it.data.subCategoryProducts, viewLifecycleOwner)
                    binding.recyclerCategories.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerCategories.adapter = searchProductsAdapter

                    Log.d(CategoryFragment::class.simpleName, "Inside searchproducr")
                    searchProductsAdapter.onClickSelectedItem{ category, i ->
                        Log.d(CategoryFragment::class.simpleName, "Inside searchproducr adapter $category")
                        val fragment = ProductDetailFragment()
                        val bundle = Bundle()
                        bundle.putInt("productId", it.data.subCategoryProducts[i].id)
                        fragment.arguments = bundle
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.mainContainer, fragment)
                            .addToBackStack("SubCategoryViewsTest")
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
