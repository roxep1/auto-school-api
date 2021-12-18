package com.bashkir

import com.bashkir.models.Employee
import com.bashkir.models.Position
import com.bashkir.models.types.Code
import com.bashkir.routings.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.Koin
import org.koin.logger.slf4jLogger
import org.postgresql.util.PGobject
import java.sql.Connection
import java.sql.DriverManager

fun connectDatabase() =
    Database.connect(::getConnection)

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(mainModule)
    }
}

fun Application.authorizedRouting() =
    routing {
        authenticate {
            accountantRoutes()
            studentRoutes()
            adminRoutes()
            hrRoutes()
            routes()
            teacherRoutes()
        }
    }

private fun getConnection(): Connection {
    val dbUrl = System.getenv("JDBC_DATABASE_URL")
    return DriverManager.getConnection(dbUrl)
}


fun PipelineContext<Unit, ApplicationCall>.getCurrentUserPhone(): String? =
    call.principal<JWTPrincipal>()?.payload?.getClaim("phoneNumber")?.asString()

abstract class StringEntityClass<out E : Entity<String>>(table: IdTable<String>, entityType: Class<E>? = null) :
    EntityClass<String, E>(table, entityType)

open class StringIdTable(name: String, columnName: String, columnLength: Int) : IdTable<String>(name) {
    override val id: Column<EntityID<String>> = varchar(columnName, columnLength).entityId()
    override val primaryKey by lazy { super.primaryKey ?: PrimaryKey(id) }
}

class PGEnum<T : Enum<T>>(enumTypeName: String, enumValue: T?) : PGobject() {
    init {
        value = enumValue?.name
        type = enumTypeName
    }
}

interface EntityWithModel<T> {
    fun toModel(): T
}
fun Application.insertTestData() = transaction {
    val accountant = Position.new("Бухгалтер") {}
    val hr = Position.new("Рекуртер") {}
    val admin = Position.new("Администратор") {}

    Employee.new("76948952838", {
        salary = 20000F
        coef = 0.5F
        position = accountant
    }) {
        name = "Дмитрий"
        lastName = "Человек"
        email = "best@vest.com"
        login = "account"
        password = "123456"
        code = Code.ACCOUNTANT
    }

    Employee.new("76142942438", {
        salary = 150000F
        coef = 1.5F
        position = admin
    }) {
        name = "Кирилл"
        lastName = "Волк"
        email = "ploh@loh.com"
        login = "admin1"
        password = "123456"
        code = Code.ADMIN
    }

    Employee.new("71142945468", {
        salary = 15000F
        coef = 2F
        position = hr
    }) {
        name = "Артемий"
        lastName = "Авдеев"
        middleName = "Сергеевич"
        email = "ggg@google.com"
        login = "humanr"
        password = "123456"
        code = Code.HR
    }
}