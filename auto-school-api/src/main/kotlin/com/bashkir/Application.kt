package com.bashkir

import com.bashkir.plugins.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import java.net.URI
import java.net.URISyntaxException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException


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
    getConnection()
}

@Throws(URISyntaxException::class, SQLException::class)
private fun getConnection(): Connection? {
    val dbUri = URI(System.getenv("DATABASE_URL"))
    val username: String = dbUri.userInfo.split(":")[0]
    val password: String = dbUri.userInfo.split(":")[1]
    val dbUrl = "jdbc:postgresql://" + dbUri.host + ':' + dbUri.port + dbUri.path
    return DriverManager.getConnection(dbUrl, username, password)
}
