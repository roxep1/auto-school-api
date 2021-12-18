package com.bashkir

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import org.junit.Assert.assertEquals
import org.junit.Test

class ApplicationTest {
    private val client = HttpClient(CIO)

    private var token = ""
//    private var token = ""

    private val empPhone = "74945952634"
    private val lessonId = 2

//    inner class AccountantRoutesTest {
//        private val path = "accountant"
//
//        @Test
//        fun salaryTest(): Unit = runBlocking {
//            val response = client.put("$path/salary/$empPhone", Json.encodeToJsonElement(30000F))
//
//            assertEquals(HttpStatusCode.OK, response.status)
//        }
//    }

//    inner class StudentRoutesTest {
//        private val path = "student"
//
//        @Test
//        fun lessonsTest(): Unit = runBlocking {
//            val response = client.get("$path/lessons")
//
//            assertEquals(HttpStatusCode.OK, response.status)
//            assertEquals(lessons, response.receive<String>())
//        }
//        @Test
//        fun teachersTest(): Unit = runBlocking {
//            val response = client.get("$path/teachers")
//
//            assertEquals(HttpStatusCode.OK, response.status)
//            assertEquals(teachers, response.receive<String>())
//        }
//        @Test
//        fun teacherLessonsTest(): Unit = runBlocking {
//            val response = client.get("$path/teachers/$empPhone/lessons")
//
//            assertEquals(HttpStatusCode.OK, response.status)
//            assertEquals(teacherLessons, response.receive<String>())
//        }
//        @Test
//        fun signUpTest(): Unit = runBlocking {
//            val response = client.post("$path/signUp/$lessonId")
//
//            assertEquals(HttpStatusCode.OK, response.status)
//        }
//    }
//
//    inner class AdminRoutesTest {
//        private val path = "admin"
//
//        @Test
//        fun studentTest(): Unit = runBlocking {
//            val response = client.post("$path/student", Json.encodeToJsonElement(studentToPost))
//
//            assertEquals(HttpStatusCode.OK, response.status)
//        }
//
//        @Test
//        fun employeeTest(): Unit = runBlocking {
//            val response = client.post("$path/employee", Json.encodeToJsonElement(empToPost))
//
//            assertEquals(HttpStatusCode.OK, response.status)
//        }
//    }
//
//    inner class HrRoutesTest {
//        private val path = "hr/vacation"
//
//        @Test
//        fun vacationPostTest(): Unit = runBlocking {
//            val response = client.post(path, Json.encodeToJsonElement(vacationToPost))
//
//            assertEquals(HttpStatusCode.OK, response.status)
//        }
//
//        @Test
//        fun vacationGetTest(): Unit = runBlocking {
//            val response = client.get(path)
//
//            assertEquals(HttpStatusCode.OK, response.status)
//            assertEquals(vacations, response.receive<String>())
//        }
//    }
//
//    inner class TeacherRoutesTest {
//        private val path = "teacher/lesson"
//
//        @Test
//        fun typesTest(): Unit = runBlocking {
//            val response = client.get("$path/types")
//
//            assertEquals(HttpStatusCode.OK, response.status)
//            assertEquals(types, response.receive<String>())
//        }
//        @Test
//        fun lessonsTest(): Unit = runBlocking {
//            val response = client.get(path)
//
//            assertEquals(HttpStatusCode.OK, response.status)
//            assertEquals(teacherOwnLessons, response.receive<String>())
//        }
//        @Test
//        fun lessonTest(): Unit = runBlocking {
//            val response = client.post(path, Json.encodeToJsonElement(lessonToPost))
//
//            assertEquals(HttpStatusCode.OK, response.status)
//        }
//    }
//
//    inner class SharedRoutesTest {
//
//        @Test
//        fun employeesTest(): Unit = runBlocking {
//            val response = client.get("employees")
//
//            assertEquals(HttpStatusCode.OK, response.status)
//            assertEquals(employees, response.receive<String>())
//        }
//
//        @Test
//        fun tariffsTest(): Unit = runBlocking {
//            val response = client.get("tariffs")
//
//            assertEquals(HttpStatusCode.OK, response.status)
//            assertEquals(tariffs, response.receive<String>())
//        }
//
//        @Test
//        fun positionsTest(): Unit = runBlocking {
//            val response = client.get("positions")
//
//            assertEquals(HttpStatusCode.OK, response.status)
//            assertEquals(positions, response.receive<String>())
//        }
//        @Test
//        fun usersTest(): Unit = runBlocking {
//            val response = client.get("users")
//            assertEquals(HttpStatusCode.OK, response.status)
//            assertEquals(users, response.receive<String>())
//        }
//    }
//
//    private suspend fun HttpClient.go(
//        path: String,
//        func: suspend HttpClient.(String, HttpRequestBuilder.() -> Unit) -> HttpResponse,
//        body: Any? = null
//    ): HttpResponse =
//        func("https://auto-school.herokuapp.com/$path") {
//            headers {
//                append(HttpHeaders.Authorization, "Bearer $token")
//            }
//            if (body != null) this.body = body
//        }
//
//    private suspend fun HttpClient.get(path: String) : HttpResponse =
//        go(path, HttpClient::get)
//
//    private suspend fun HttpClient.put(path: String, body: JsonElement): HttpResponse =
//        go(path, HttpClient::put, body)
//
//    private suspend fun HttpClient.post(path: String, body: JsonElement? = null): HttpResponse =
//        go(path, HttpClient::post, body)
}