package com.cs4520.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.databinding.ProductListLayoutBinding

class ProductListFragment: Fragment(R.layout.product_list_layout) {
    private var _binding: ProductListLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductListLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        observeProductList()
        viewModel.refreshProducts()
        return view
    }


    private fun observeProductList(){
        viewModel.products.observe(viewLifecycleOwner){
            products -> observerHelper(products)
        }
    }

    private fun observerHelper(products: List<Product>){
        val adapter = ProductAdapter(products)
        binding.recyclerview.adapter = adapter
    }
}