package com.bashkir

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.bashkir.services.EmployeesService
import com.bashkir.services.StudentService
import com.bashkir.services.PeopleService
import io.ktor.application.*
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.*

private enum class JWTThings {
    Audience, Issuer, Secret, Realm;

    fun getValue(environment: ApplicationEnvironment): String =
        environment.config.property("jwt.${name.lowercase()}").getString()
}

val mainModule = module {
    single { EmployeesService() }
    single { PeopleService() }
    single { StudentService() }

    factory(named("token")) { (environment: ApplicationEnvironment, phoneNumber: String) ->
        JWT.create()
            .withAudience(JWTThings.Audience.getValue(environment))
            .withIssuer(JWTThings.Issuer.getValue(environment))
            .withClaim("phoneNumber", phoneNumber)
            .withExpiresAt(Date(System.currentTimeMillis() + 60000))
            .sign(Algorithm.HMAC256(JWTThings.Secret.getValue(environment)))
    }

    single(named("realm")) { (environment: ApplicationEnvironment) ->
        JWTThings.Realm.getValue(environment)
    }

    single { (environment: ApplicationEnvironment) ->
        JWT.require(Algorithm.HMAC256(JWTThings.Secret.getValue(environment)))
            .withAudience(JWTThings.Audience.getValue(environment))
            .withIssuer(JWTThings.Issuer.getValue(environment))
            .build()
    }
}
