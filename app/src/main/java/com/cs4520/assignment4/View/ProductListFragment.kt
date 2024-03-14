package com.cs4520.assignment4.View

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs4520.assignment4.R
import com.cs4520.assignment4.databinding.ProductListLayoutBinding
import com.cs4520.assignment4.model.Product
import com.cs4520.assignment4.model.database.AppDatabase
import com.cs4520.assignment4.model.database.ProductDao
import com.cs4520.assignment4.model.database.ProductRepository
import com.cs4520.assignment4.viewmodel.ProductViewModel
import com.cs4520.assignment4.viewmodel.ProductViewModelFactory

class ProductListFragment: Fragment(R.layout.product_list_layout) {
    private var _binding: ProductListLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application: Application = requireNotNull(this.activity).application
        val dao: ProductDao = AppDatabase.getDatabase(application).productDao()
        val repository = ProductRepository(dao)
        val factory = ProductViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(ProductViewModel::class.java)

        _binding = ProductListLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        observeErrorMessages()
        observeProductList()
        viewModel.refreshProducts()
        return view
    }

    private fun observeErrorMessages(){
        viewModel.errorMessage.observe(viewLifecycleOwner){
            if(!it.isNullOrEmpty()){
                val toast = Toast.makeText(context, it, Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }


    private fun observeProductList(){
        viewModel.products.observe(viewLifecycleOwner){
            products -> observerHelper(products)
        }
    }

    private fun observerHelper(products: List<Product>){
        val adapter = ProductAdapter(products)
        binding.recyclerview.adapter = adapter
        if(products.size > 0){
            binding.recyclerview.visibility = View.VISIBLE
            binding.noProductsAvailable.visibility = View.GONE
        } else {
            binding.recyclerview.visibility = View.GONE
            binding.noProductsAvailable.visibility = View.VISIBLE
        }
    }
}