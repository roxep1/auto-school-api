package com.bashkir.routings

import com.bashkir.getCurrentUserPhone
import com.bashkir.services.TeacherService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.teacherRoutes() {
    val service: TeacherService by inject()

    route("teacher/lesson") {
        get {
            call.respond(service.getLessons(getCurrentUserPhone() ?: ""))
        }

        post {
            service.createLesson(getCurrentUserPhone() ?: "", call.receive())
            call.respond(HttpStatusCode.OK)
        }

        get("types") {
            call.respond(service.getLessonTypes())
        }
    }
}