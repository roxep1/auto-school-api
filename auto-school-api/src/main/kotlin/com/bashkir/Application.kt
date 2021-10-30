package com.bashkir

import com.bashkir.models.*
import com.bashkir.plugins.configureAuthentication
import com.bashkir.plugins.configureRouting
import com.bashkir.plugins.configureSerialization
import com.bashkir.routings.employeesRouting
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
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

//    routing {
//        authenticate {
    employeesRouting()
//        }
//    }

//    routing {
//            exec("call update_person('7964561924')")
//        get("/") {
//
//            call.respond(transaction {
//                Employee.all().elementAt(0).toModel()
//            })
//        }
//    }
}
