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

            val response = retrofit.getProducts()

            if(response.isSuccessful){

                if(response.body().isNullOrEmpty()){
                    launch(Dispatchers.Main){

                    }
                } else {
                    launch(Dispatchers.Main){
                        _products.value = response.body()!!
                    }
                }
            } else {

                launch(Dispatchers.Main){

                }
            }
        }
     }


}