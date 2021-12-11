package com.bashkir.routings

import com.bashkir.models.People
import com.bashkir.models.SingUpsTable.student
import com.bashkir.models.Students
import com.bashkir.services.AdminService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.adminRoutes() {
    val service: AdminService by inject()

    route("admin") {
        post("student") {
            service.createStudent(call.receive(), call.receive())
            call.respond(HttpStatusCode.OK)
        }

        post("employee"){
            service.createEmployee(call.receive(), call.receive())
            call.respond(HttpStatusCode.OK)
        }
    }
}