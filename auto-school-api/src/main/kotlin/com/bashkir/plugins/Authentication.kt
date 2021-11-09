package com.bashkir.plugins

import com.auth0.jwt.JWTVerifier
import com.bashkir.services.PeopleService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
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
            val password = call.request.queryParameters["password"]
            val login = call.request.queryParameters["login"]
            val user = peopleService.login(
                login ?: "",
                password ?: ""
            )
            if (user != null) {
                val token: String by this@routing.inject(qualifier = named("token")) {
                    parametersOf(
                        environment,
                        user.phoneNumber
                    )
                }
                call.respond(Json.encodeToJsonElement(hashMapOf("token" to token, "isSuccess" to true.toString())))
            } else call.respond(Json.encodeToJsonElement(hashMapOf("isSuccess" to true)))
        }
    }
}
