package com.bashkir

import com.bashkir.models.Employee
import com.bashkir.plugins.configureRouting
import com.bashkir.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
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

    val db = getConnection()
    transaction {
        val emps = Employee.all()
        emps.forEach {

        }
    }
}

@Throws(URISyntaxException::class, SQLException::class)
private fun getConnection(): Database {
    val dbUri = URI(System.getenv("DATABASE_URL"))
    val dbUrl =
        "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath().toString() + "?sslmode=require"
    return Database.connect(dbUrl, driver = "com.impossibl.postgres.jdbc.PGDriver",
        user = "Admin", password = "1234567890")
}
