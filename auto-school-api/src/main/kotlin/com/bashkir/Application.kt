package com.bashkir

import com.bashkir.plugins.configureAuthentication
import com.bashkir.plugins.configureRouting
import com.bashkir.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.locations.*


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalLocationsAPI
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    connectDatabase()
    configureKoin()
    configureRouting()
    configureAuthentication()
    configureSerialization()

    authorizedRouting()

//    insertTestData()
}