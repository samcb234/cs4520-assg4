package com.cs4520.assignment4

import com.google.gson.annotations.SerializedName

sealed class Product(@SerializedName("name") open val name: Any?,
                     @SerializedName("type") open val type: Any?,
                     @SerializedName("expiryDate") open val expiryDate: Any?,
                     @SerializedName("price") open val price: Any?) {


    data class Food(override val name: Any?,
                    override val type: Any?,
                    override val expiryDate: Any?,
                    override val price: Any?) :Product(name, type, expiryDate, price){

    }

    data class Equipment(override val name: Any?,
                    override val type: Any?,
                    override val expiryDate: Any?,
                    override val price: Any?) :Product(name, type, expiryDate, price){

    }

}