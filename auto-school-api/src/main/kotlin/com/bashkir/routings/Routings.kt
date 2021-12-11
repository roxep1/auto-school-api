package com.bashkir.routings

import com.bashkir.services.SharedService
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.routes(){
    val service : SharedService by inject()

    route("/"){
        get("users"){
            call.respond(service.getUsers())
        }

        get("positions"){
            call.respond(service.getPositions())
        }

        get("tariffs"){
            call.respond(service.getTariffs())
        }

        get("employees"){
            call.respond(service.getEmployees())
        }
    }
}