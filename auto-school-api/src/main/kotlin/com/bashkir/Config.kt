package com.bashkir

import com.bashkir.models.*
import com.bashkir.models.types.Code
import com.bashkir.routings.employeesRouting
import com.bashkir.routings.studentRoutes
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
import sun.security.jca.GetInstance
import java.sql.Connection
import java.sql.DriverManager
import java.time.Instant

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
            employeesRouting()
            studentRoutes()
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
    val positionInstructor = Position.new("Преподаватель") {}

    val man1 = Employee.new("74945952634", {
        salary = 40000F
        coef = 1F
        position = positionInstructor
    }) {
        name = "Андрей"
        lastName = "Попов"
        middleName = "Викторович"
        email = "test@test.com"
        login = "listar"
        password = "forgotui1401"
        code = Code.TEACHER
    }

    val man2 = Employee.new("79640000000", {
        salary = 30000F
        coef = 1.5F
        position = Position["Инструктор"]

    }) {
        name = "Настасья"
        lastName = "Бойченко"
        middleName = "Сергеевна"
        email = "anime@tyan.nya"
        login = "stef"
        password = "1234"
        code = Code.TEACHER
    }

    val curse = EducationType.new("Курс") {}
    val height = EducationType.new("Высшее") {}
    val middle = EducationType.new("Среднее") {}
    val middleSpecial = EducationType.new("Среднее специальное") { }

    Education.new {
        speciality = "Инженер"
        institution = "МГУ"
        periodOfStudy = "2010-2015"
        educationTypeName =  curse
        employee = man1
    }

    Education.new {
        speciality = "Слесарь"
        institution = "МПТ"
        periodOfStudy = "2015-2019"
        educationTypeName =  height
        employee = man2
    }

    val lesson =  LessonType.new {
        name = "Лекция"
        duration = 120
        onePlace = false
    }

    val driving = LessonType.new {
        name = "Вождение"
        duration = 120
        onePlace = true
    }

    Lessons.new{
        date = Instant.now()
        type = lesson
        phoneNumber = man2
    }

    Lessons.new{
        date = Instant.now().plusSeconds(1000000000)
        type = driving
        phoneNumber = man1
    }
}
