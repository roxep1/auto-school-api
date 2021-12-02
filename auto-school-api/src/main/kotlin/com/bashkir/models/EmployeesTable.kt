package com.bashkir.models

import com.bashkir.EntityWithModel
import com.bashkir.StringEntityClass
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.date

object EmployeesTable : IdTable<String>("employees",) {
    override val id: Column<EntityID<String>> = reference("phonenumber", PeopleTable)
    val salary: Column<Float> = float("salary")
    val coef = float("coef")
    val position = reference("positionname", PositionTable)
    val dateOfDismissal = date("dateofdismissal").nullable()
}

class Employee(id: EntityID<String>) : Entity<String>(id), EntityWithModel<Employee.Model> {
    companion object : StringEntityClass<Employee>(EmployeesTable) {
        fun new(id : String, initEmp: Employee.() -> Unit, initMan: People.() -> Unit): Employee {
            People.new(id = id,initMan)
            return new(id = id, initEmp)
        }
    }

    var peopleInfo by People referencedOn EmployeesTable.id
    var salary by EmployeesTable.salary
    var coef by EmployeesTable.coef
    var position by Position referencedOn EmployeesTable.position
    var dateOfDismissal by EmployeesTable.dateOfDismissal

    override fun toModel() = Model(this)

    @Serializable
    data class Model(@Transient private val emp: Employee? = null) {
        val peopleInfo = emp!!.peopleInfo.toModel()
        val salary = emp!!.salary
        val coef = emp!!.coef
        val positionName = emp!!.position.toModel()
        val dateOfDismissal = emp?.dateOfDismissal?.toString()
    }
}
