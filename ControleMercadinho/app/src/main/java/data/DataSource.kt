package com.example.controlemercadinho.data

class DataSource {
    private val productList = mutableListOf<String>()

    fun addProduct(product: String) {
        productList.add(product)
    }

    fun getProducts(): List<String> {
        return productList
    }

    fun clearProducts() {
        productList.clear()
    }
}
