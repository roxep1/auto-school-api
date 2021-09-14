package com.bashkir

import io.ktor.application.*
import com.bashkir.plugins.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    routing {
        get("/") {
            call.respond(Customer(1))
        }
    }
    configureRouting()
    configureSerialization()
}
