package com.bashkir

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.bashkir.services.*
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
//    single { AccountantService() }
//    single { AdminService() }
    single { PeopleService() }
    single { StudentService() }
//    single { HumanResService() }
//    single { SharedService() }
//    single { TeacherService() }

    factory(named("token")) { (environment: ApplicationEnvironment, phoneNumber: String) ->
        JWT.create()
            .withAudience(JWTThings.Audience.getValue(environment))
            .withIssuer(JWTThings.Issuer.getValue(environment))
            .withClaim("phoneNumber", phoneNumber)
            .withExpiresAt(Date(System.currentTimeMillis() + 360000))
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