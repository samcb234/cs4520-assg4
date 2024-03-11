package com.cs4520.assignment4


import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("prod/")
    suspend fun getProducts(): Response<List<Product>>
}