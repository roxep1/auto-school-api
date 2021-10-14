package com.bashkir.models

import com.bashkir.EntityWithModel
import com.bashkir.StringEntityClass
import com.bashkir.StringIdTable
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate

object EmployeesTable : IdTable<String>("employees",) {
    override val id: Column<EntityID<String>> = reference("phonenumber", PeopleTable)
    val salary: Column<Float> = float("salary")
    val coef: Column<Int> = integer("coef")
    val positionName: Column<String> = text("positionname")
    val dateOfDismissal = date("dateofdismissal").nullable()
}

class Employee(id: EntityID<String>) : Entity<String>(id), EntityWithModel<Employee.Model> {
    companion object : StringEntityClass<Employee>(EmployeesTable) {
        fun new(initEmp: Employee.() -> Unit, initMan: People.() -> Unit): Employee {
            val man = People.new(initMan)
            return new(id = man.id.value, initEmp)
        }
    }

    var phoneNumber by People referencedOn EmployeesTable.id
    var salary by EmployeesTable.salary
    var coef by EmployeesTable.coef
    var positionName by EmployeesTable.positionName
    var dateOfDismissal by EmployeesTable.dateOfDismissal

    override fun toModel() = Model(this)

    @Serializable
    data class Model(@Transient private val emp: Employee? = null) {
        val phoneNumber = emp?.phoneNumber?.id?.value
        val salary = emp?.salary
        val coef = emp?.coef
        val positionName = emp?.positionName
        val dateOfDismissal = emp?.dateOfDismissal.toString()
    }
}
