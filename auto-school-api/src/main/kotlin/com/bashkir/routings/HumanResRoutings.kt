package com.bashkir.routings

import com.bashkir.services.HumanResService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.hrRoutes(){
    val service: HumanResService by inject()

    route("hr/vacation"){
        post{
            service.createVacation(call.receive())
            call.respond(HttpStatusCode.OK)
        }

        get{
            call.respond(service.getVacations())
        }
    }
}