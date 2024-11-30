package com.example.controlemercadinho.data

class ProductRepository(private val dataSource: DataSource) {

    fun addProductToRepository(product: String) {
        dataSource.addProduct(product)
    }

    fun getProductList(): List<String> {
        return dataSource.getProducts()
    }
}
