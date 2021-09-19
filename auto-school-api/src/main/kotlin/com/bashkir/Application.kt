package com.bashkir

import com.bashkir.models.Employee
import com.bashkir.models.LessonType
import com.bashkir.models.Man
import com.bashkir.models.checkMan
import com.bashkir.plugins.configureRouting
import com.bashkir.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import java.net.URI
import java.net.URISyntaxException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import javax.sql.DataSource


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    Database.connect(::getConnection)
    configureRouting()
    configureSerialization()

    routing {
        var man: Man? = null
        transaction {
            man = Man.all().elementAt(0)
        }
        get("/") {
            call.respond(checkMan(man))
        }

    }
}

@Throws(URISyntaxException::class, SQLException::class)
private fun getConnection(): Connection {
    val dbUrl = System.getenv("JDBC_DATABASE_URL")
    return DriverManager.getConnection(dbUrl)
}
