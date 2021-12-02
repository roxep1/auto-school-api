package com.bashkir

import com.bashkir.models.Education
import com.bashkir.models.Employee
import com.bashkir.models.People
import com.bashkir.models.Position
import com.bashkir.models.types.Code
import com.bashkir.plugins.configureAuthentication
import com.bashkir.plugins.configureRouting
import com.bashkir.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.locations.*
import org.jetbrains.exposed.sql.transactions.transaction


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

    insertTestData()
}
