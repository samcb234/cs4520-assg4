package com.cs4520.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductListFragment: Fragment(R.layout.product_list_layout) {

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
        val view: View = inflater.inflate(R.layout.product_list_layout, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = ProductAdapter(productList)
        recyclerView.adapter = adapter
        return view
    }
}