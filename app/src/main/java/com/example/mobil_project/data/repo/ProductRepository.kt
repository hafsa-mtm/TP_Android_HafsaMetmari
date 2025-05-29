package com.example.mobil_project.data.repo

import com.example.mobil_project.R
import com.example.mobil_project.data.entities.Product
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import android.util.Log
import java.io.Console

import com.example.mobil_project.data.api.ProductApi


@Singleton
    class ProductRepository @Inject constructor(
        private val api: ProductApi
    ) {
        suspend fun getProducts(): List<Product> {
            val products = api.getProducts()
            Log.d("products repo", "size :"+ products.size)
            return products
    }}
