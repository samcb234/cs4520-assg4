package com.cs4520.assignment4

sealed class Product(open val name: Any?, val type: Any?, open val expiryDate: Any?, open val price: Any?) {


    data class Food(override val name: Any?, override val expiryDate: Any?, override val price: Any?) :Product(name, "Food", expiryDate, price){

    }

    data class Equipment(override val name: Any?, override val expiryDate: Any?, override val price: Any?) :Product(name, "Equipment", expiryDate, price){
    }

}