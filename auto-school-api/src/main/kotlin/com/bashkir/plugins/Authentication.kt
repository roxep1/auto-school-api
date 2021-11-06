package com.bashkir.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.bashkir.models.People
import com.bashkir.services.PeopleService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.ktor.ext.get
import org.koin.ktor.ext.inject
import java.util.*

//@KtorExperimentalLocationsAPI
fun Application.configureAuthentication() {
//    @Location("/login")
//    data class Credentials(val login: String, val password: String)

    val peopleService: PeopleService by inject()

    install(Authentication) {
        jwt {
            realm = get(qualifier = named("realm")) { parametersOf(environment) }
            verifier(get<JWTVerifier> { parametersOf(environment) })

            validate { jwtCredential ->
                if (!jwtCredential.payload.getClaim("phoneNumber").asString().isNullOrBlank())
                    JWTPrincipal(jwtCredential.payload)
                else null
            }
        }
    }

    routing {
        post("/login") {
            val user = peopleService.login(
                call.request.queryParameters["login"] ?: "",
                call.request.queryParameters["password"] ?: ""
            )
            if (user != null) {
                val token: String by this@routing.inject(qualifier = named("token")) {
                    parametersOf(
                        environment,
                        user.phoneNumber
                    )
                }
                call.respond(hashMapOf("token" to token, "user" to user))
            } else call.respondText("Error: no user", status = HttpStatusCode.NotFound)
        }
    }
}
