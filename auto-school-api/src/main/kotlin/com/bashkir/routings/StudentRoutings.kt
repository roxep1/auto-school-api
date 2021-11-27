package com.bashkir.routings

import com.bashkir.getCurrentUserPhone
import com.bashkir.services.LessonsService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import java.time.LocalDateTime

fun Route.studentRoutes() {
    val lessonsService: LessonsService by inject()

    route("/student") {
        get("/lessons") {
            val phoneNumber = getCurrentUserPhone() ?: ""
            call.respond(lessonsService.getLessons(phoneNumber))
        }

        delete("/lessons") {
            val phoneNumber = getCurrentUserPhone() ?: ""
            val userNow: LocalDateTime? = LocalDateTime.parse(call.parameters["now"])
            if (userNow != null)
                lessonsService.deleteLessons(phoneNumber, userNow)
            else
                lessonsService.deleteLessons(phoneNumber)
            call.respond(HttpStatusCode.OK)
        }
    }
}