package com.bashkir.routings

import com.bashkir.services.AccountantService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.accountantRoutes(){
    val service : AccountantService by inject()

    route("accountant"){
        put("salary/{id}"){
            val id = call.parameters["id"]!!
            val salary = call.receive<Float>()
            service.changeSalary(id, salary)
            call.respond(HttpStatusCode.OK)
        }
    }
}