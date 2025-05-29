package com.example.mobil_project.data.api
import com.example.mobil_project.data.entities.Product
import retrofit2.http.GET

interface ProductApi {
    @GET("products.json")
    suspend fun getProducts(): List<Product>
}