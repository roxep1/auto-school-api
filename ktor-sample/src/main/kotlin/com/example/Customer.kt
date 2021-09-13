package com.example

import kotlinx.serialization.Serializable

@Serializable
data class Customer(val id: String, val firstName: String, val lastName: String, val email: String){
    companion object{
        val customerStorage = mutableListOf<Customer>()
    }
}