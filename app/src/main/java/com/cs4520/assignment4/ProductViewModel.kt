package com.cs4520.assignment4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel: ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIService::class.java)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products


     fun refreshProducts(){

         val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
             throwable.printStackTrace()
         }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            println("called")
            val response = retrofit.getProducts()
            println("received response from api")
            if(response.isSuccessful){
                println("API worked!")
                if(response.body().isNullOrEmpty()){
                    launch(Dispatchers.Main){
                        _products.value = buildProducts()
                    }
                } else {
                    launch(Dispatchers.Main){
                        _products.value = response.body()!!
                    }
                }
            } else {
                println("API didn't work")
                launch(Dispatchers.Main){
                    _products.value = buildProducts()
                }
            }

            println("made it to the end of the coroutine")
        }
     }

    private fun buildProducts(): ArrayList<Product>{
        val productList: ArrayList<Product> = ArrayList()
        for(i in 0..productsDataset.size -1){
            val l = productsDataset[i]
            if(l[1] == "Food"){
                productList.add(Product.Food(l[0], l[1], l[2], l[3]))
            } else{
                productList.add(Product.Equipment(l[0], l[1], l[2], l[3]))
            }
        }
        return productList
    }
}