package com.cs4520.assignment4

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val productList: List<Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val product: Product = productList[position]

        holder.imageView.setAdjustViewBounds(true)
        holder.imageView.maxHeight = 150
        holder.imageView.maxWidth = 150
        when(product){
            is Product.Food -> {
                holder.imageView.setImageResource(R.drawable.food)
                holder.setBGColor("#FFD965")
            }
            is Product.Equipment -> {
                holder.imageView.setImageResource(R.drawable.equipment)
                holder.setBGColor("#E06666")
            }
        }


        holder.nameView.text = product.name.toString()
        if(product.expiryDate != null){
            holder.expirationView.text = product.expiryDate.toString()
        } else {
            holder.expirationView.text = "" //THIS IS SUBJECT TO CHANGE
        }

        holder.priceView.text = "$" + product.price.toString()
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val nameView: TextView = itemView.findViewById(R.id.product_name)
        val expirationView: TextView = itemView.findViewById(R.id.expiration_date)
        val priceView: TextView = itemView.findViewById(R.id.price)

        fun setBGColor(color: String){
            itemView.setBackgroundColor(Color.parseColor(color))
        }
    }

}