package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.routes.registerCustomerRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

fun main(){
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    registerCustomerRoutes()
}

