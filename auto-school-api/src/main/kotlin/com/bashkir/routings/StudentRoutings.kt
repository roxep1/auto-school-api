package com.bashkir.routings

import com.bashkir.getCurrentUserPhone
import com.bashkir.services.StudentService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import java.time.Instant

fun Route.studentRoutes() {
    val studentService: StudentService by inject()

    route("/student") {
        get("/lessons") {
            val phoneNumber = getCurrentUserPhone() ?: ""
            call.respond(studentService.getLessons(phoneNumber))
        }

        get("/teachers") {
            call.respond(studentService.getTeachers())
        }

        get("/teachers/{id}/lessons") {
            val teacherId = call.parameters["id"] ?: ""
            call.respond(studentService.getTeacherLessons(teacherId))
        }

        post("/signUp/{id}") {
            studentService.signUpTeacher(getCurrentUserPhone() ?: "", call.parameters["id"]?.toInt() ?: -1)
            call.respond(HttpStatusCode.OK)
        }
    }
}