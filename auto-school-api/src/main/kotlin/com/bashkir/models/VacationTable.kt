package com.bashkir.models

import com.bashkir.EntityWithModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object VacationTable: IntIdTable("vacation", "vacationid") {
    val coef = float("coef")
    val periodOfVacation = varchar("periodofvacation", 30)
    val employee = reference("phonenumber", EmployeesTable)
}

class Vacation(id: EntityID<Int>): IntEntity(id), EntityWithModel<Vacation.Model>{
    companion object: IntEntityClass<Vacation>(VacationTable)

    var coef by VacationTable.coef
    var periodOfVacation by VacationTable.periodOfVacation
    var employee by Employee referencedOn VacationTable.employee

    @Serializable
    data class Model(@Transient val model: Vacation? = null) {
        val id = model!!.id.value
        val coef = model!!.coef
        val periodOfVacation = model!!.periodOfVacation
        val employee = model!!.employee.id.value
    }

    override fun toModel(): Model = Model(this)
}
