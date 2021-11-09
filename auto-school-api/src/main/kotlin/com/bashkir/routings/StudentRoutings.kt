package com.bashkir.routings

import com.bashkir.services.LessonsService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.studentRoutes() {
    val lessonsService: LessonsService by inject()
    route("/student") {
        get("/lessons") {
            val phoneNumber = call.principal<JWTPrincipal>()?.payload?.getClaim("phoneNumber")?.asString()
            call.respond(lessonsService.getLessons(phoneNumber?:""))
        }
    }
}

