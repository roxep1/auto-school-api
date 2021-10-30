package com.bashkir.routings

import com.bashkir.services.EmployeesService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.get
import org.koin.ktor.ext.inject

fun Application.employeesRouting(){
    val service by inject<EmployeesService>()

    routing{
            get("/"){
                call.respond(service.getFirst())
            }
    }
}