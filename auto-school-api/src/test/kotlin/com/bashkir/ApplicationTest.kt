package com.bashkir

import io.ktor.client.*
import io.ktor.client.call.*
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


    private val accPath = "accountant"

    @Test
    fun salaryTest(): Unit = runBlocking {
        val response = client.put("$accPath/salary/$empPhone", accountantToken, Json.encodeToJsonElement(30000F))

        assertEquals(HttpStatusCode.OK, response.status)
    }


    private val studentPath = "student"

    @Test
    fun lessonsTest(): Unit = runBlocking {
        val response = client.get("$studentPath/lessons", studentToken)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(lessons, response.receive<String>())
    }

    @Test
    fun teachersTest(): Unit = runBlocking {
        val response = client.get("$studentPath/teachers", studentToken)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(teachers, response.receive<String>())
    }

    @Test
    fun teacherLessonsTest(): Unit = runBlocking {
        val response = client.get("$studentPath/teachers/$empPhone/lessons", studentToken)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(teacherLessons, response.receive<String>())
    }

    @Test
    fun signUpTest(): Unit = runBlocking {
        val response = client.post("$studentPath/signUp/$lessonId", studentToken)

        assertEquals(HttpStatusCode.OK, response.status)
    }

//    inner class AdminRoutesTest {
//        private val path = "admin"
//
//        @Test
//        fun studentTest(): Unit = runBlocking {
//            val response = client.post("$path/student", adminToken, Json.encodeToJsonElement(studentToPost))
//
//            assertEquals(HttpStatusCode.OK, response.status)
//        }
//
//        @Test
//        fun employeeTest(): Unit = runBlocking {
//            val response = client.post("$path/employee", adminToken, Json.encodeToJsonElement(empToPost))
//
//            assertEquals(HttpStatusCode.OK, response.status)
//        }
//    }


    private val hrPath = "hr/vacation"

    @Test
    fun vacationPostTest(): Unit = runBlocking {
        val response = client.post(hrPath, hrToken, Json.encodeToJsonElement(vacationToPost))

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun vacationGetTest(): Unit = runBlocking {
        val response = client.get(hrPath, hrToken)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(vacations, response.receive<String>())
    }


    private val teacherPath = "teacher/lesson"

    @Test
    fun typesTest(): Unit = runBlocking {
        val response = client.get("$teacherPath/types", teacherToken)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(types, response.receive<String>())
    }

    @Test
    fun teacherGetLessonsTest(): Unit = runBlocking {
        val response = client.get(teacherPath, teacherToken)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(teacherOwnLessons, response.receive<String>())
    }

    @Test
    fun lessonTest(): Unit = runBlocking {
        val response = client.post(teacherPath, teacherToken, Json.encodeToJsonElement(lessonToPost))

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun employeesTest(): Unit = runBlocking {
        val response = client.get("employees", token)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(employees, response.receive<String>())
    }

    @Test
    fun tariffsTest(): Unit = runBlocking {
        val response = client.get("tariffs", token)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(tariffs, response.receive<String>())
    }

    @Test
    fun positionsTest(): Unit = runBlocking {
        val response = client.get("positions", token)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(positions, response.receive<String>())
    }

    @Test
    fun usersTest(): Unit = runBlocking {
        val response = client.get("users", token)
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(users, response.receive<String>())
    }


    private suspend fun HttpClient.go(
        path: String,
        func: suspend HttpClient.(String, HttpRequestBuilder.() -> Unit) -> HttpResponse,
        authToken: String,
        body: Any? = null
    ): HttpResponse =
        func("https://auto-school.herokuapp.com/$path") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $authToken")
            }
            if (body != null) this.body = body
        }

    private suspend fun HttpClient.get(path: String, token: String): HttpResponse =
        go(path, HttpClient::get, token)

    private suspend fun HttpClient.put(path: String, token: String, body: JsonElement): HttpResponse =
        go(path, HttpClient::put, token, body)

    private suspend fun HttpClient.post(path: String, token: String, body: JsonElement? = null): HttpResponse =
        go(path, HttpClient::post, token, body)
}