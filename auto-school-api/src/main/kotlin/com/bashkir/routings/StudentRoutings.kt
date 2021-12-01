package com.bashkir.routings

import com.bashkir.getCurrentUserPhone
import com.bashkir.services.StudentService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import java.time.LocalDateTime

fun Route.studentRoutes() {
    val studentService: StudentService by inject()

    route("/student") {
        get("/lessons") {
            val phoneNumber = getCurrentUserPhone() ?: ""
            call.respond(studentService.getLessons(phoneNumber))
        }

        delete("/lessons") {
            val phoneNumber = getCurrentUserPhone() ?: ""
            val userNow: LocalDateTime? = LocalDateTime.parse(call.request.queryParameters["now"])
            if (userNow != null)
                studentService.deleteLessons(phoneNumber, userNow)
            else
                studentService.deleteLessons(phoneNumber)
            call.respond(HttpStatusCode.OK)
        }

        get("/teachers"){
            call.respond(studentService.getTeachers())
        }

        get("/teachers/{id}/lessons"){
            val teacherId = call.parameters["id"]?: ""
            call.respond(studentService.getTeacherLessons(teacherId))
        }

        post("/signUp"){

        }
    }
}