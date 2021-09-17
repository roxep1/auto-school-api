package com.bashkir.models

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date
import java.time.LocalDate

/*
 * Base class for entities with string id
 */
abstract class StringEntityClass<out E: Entity<String>>(table: IdTable<String>, entityType: Class<E>? = null) : EntityClass<String, E>(table, entityType)

/*
 * Base class for table objects with string id
 */
open class StringIdTable(name: String = "", columnName: String = "id", columnLength: Int = 10) : IdTable<String>(name) {
        override val id: Column<EntityID<String>> = varchar(columnName, columnLength).entityId()
        override val primaryKey by lazy { super.primaryKey ?: PrimaryKey(id) }
}

object Employees: StringIdTable("phonenumber", "phonenumber", 11) {
        val salary: Column<Float> = float("salary")
        val coef: Column<Int> = integer("coef")
        val positionName: Column<String> = text("positionname")
        val dateOfDismissal: Column<LocalDate> = date("date")
}

class Employee(id: EntityID<String>): Entity<String>(id){
        companion object : StringEntityClass<Employee>(Employees)

        var salary by Employees.salary
        var coef by Employees.coef
        var positionName by Employees.positionName
        var dateOfDismissal by Employees.dateOfDismissal
}