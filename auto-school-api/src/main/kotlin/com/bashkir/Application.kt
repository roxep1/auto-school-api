package com.bashkir

import com.bashkir.models.*
import com.bashkir.plugins.configureRouting
import com.bashkir.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.utils.io.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.postgresql.util.PGobject
import java.sql.Connection
import java.sql.DriverManager


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    Database.connect(::getConnection)
    configureRouting()
    configureSerialization()

    routing {
        var man: People? = null
        transaction {
//            exec("call update_person('7964561924')")
            man =
                People.all().elementAt(0)
        }
        get("/") {
            call.respond(man?.toModel() ?:"Error")
        }
    }
}

private fun getConnection(): Connection {
    val dbUrl = System.getenv("JDBC_DATABASE_URL")
    return DriverManager.getConnection(dbUrl)
}

abstract class StringEntityClass<out E: Entity<String>>(table: IdTable<String>, entityType: Class<E>? = null) : EntityClass<String, E>(table, entityType)

open class StringIdTable(name: String , columnName: String , columnLength: Int ) : IdTable<String>(name) {
    override val id: Column<EntityID<String>> = varchar(columnName, columnLength).entityId()
    override val primaryKey by lazy { super.primaryKey ?: PrimaryKey(id) }
}

class PGEnum<T : Enum<T>>(enumTypeName: String, enumValue: T?) : PGobject() {
    init {
        value = enumValue?.name
        type = enumTypeName
    }
}

interface EntityWithModel<T>{
    fun toModel(): T
}