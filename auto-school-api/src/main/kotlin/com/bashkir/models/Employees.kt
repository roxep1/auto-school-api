package com.bashkir.models

import com.bashkir.StringEntityClass
import com.bashkir.StringIdTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.date
import java.time.LocalDate

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