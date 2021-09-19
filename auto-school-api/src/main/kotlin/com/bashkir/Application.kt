package com.bashkir

import com.bashkir.models.Employee
import com.bashkir.models.LessonType
import com.bashkir.models.Man
import com.bashkir.plugins.configureRouting
import com.bashkir.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import java.net.URI
import java.net.URISyntaxException
import java.sql.DriverManager
import java.sql.SQLException


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    getConnection()
    configureRouting()
    configureSerialization()

    routing {
        var man: Man? = null
        transaction {
            man = Man.all().elementAt(0)
        }
        get("/") {
            call.respond(man!!)
        }

    }
}

@Throws(URISyntaxException::class, SQLException::class)
private fun getConnection(): Database {
    val dbUri = URI(System.getenv("DATABASE_URL"))
    val username = dbUri.userInfo.split(":").toTypedArray()[0]
    val password = dbUri.userInfo.split(":").toTypedArray()[1]
    val dbUrl = "jdbc:postgresql://" + dbUri.host + ':' + dbUri.port + dbUri.path.toString()
    DriverManager.getConnection(dbUrl, username, password)
    return Database.connect(
        dbUrl, driver = "com.impossibl.postgres.jdbc.PGDriver",
        user = username, password = password
    )
}
