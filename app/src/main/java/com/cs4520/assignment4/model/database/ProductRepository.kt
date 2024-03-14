package com.cs4520.assignment4.model.database

import androidx.lifecycle.LiveData
import com.cs4520.assignment4.model.APIResponse

class ProductRepository(private val dao: ProductDao) {

    suspend fun insertAll(products: List<APIResponse>){
        dao.insertAll(products)
    }

    suspend fun clearDB(){
        dao.clearTable()
    }

    val products = dao.getAll()
}