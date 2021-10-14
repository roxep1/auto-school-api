package com.bashkir

import com.bashkir.models.*
import com.bashkir.plugins.configureRouting
import com.bashkir.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.transactions.transaction


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    connectDatabase()
    configureRouting()
    configureSerialization()

    routing {
//            exec("call update_person('7964561924')")
        get("/") {
            val emp = transaction {
                Employee.all().elementAt(0)
            }
            call.respond(emp.toModel())
        }
    }
}
