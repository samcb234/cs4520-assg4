package com.cs4520.assignment4

sealed class Product(val name: Any?, val type: Any?, val expiryDate: Any?, val price: Any?) {


    class Food(name: Any?, expiryDate: Any?, price: Any?) :Product(name, "Food", expiryDate, price){

    }

    class Equipment(name: Any?, expiryDate: Any?, price: Any?) :Product(name, "Equipment", expiryDate, price){
    }

}