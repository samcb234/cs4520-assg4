package com.cs4520.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs4520.assignment4.databinding.ProductListLayoutBinding

class ProductListFragment: Fragment(R.layout.product_list_layout) {
    private var _binding: ProductListLayoutBinding? = null
    private val binding get() = _binding!!

    private val productList: ArrayList<Product> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        for(i in 0..productsDataset.size -1){
            val l = productsDataset[i]
            if(l[1] == "Food"){
                productList.add(Product.Food(l[0], l[2], l[3]))
            } else{
                productList.add(Product.Equipment(l[0], l[2], l[3]))
            }
        }
        println(productList.size)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductListLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter

        return view
    }
}