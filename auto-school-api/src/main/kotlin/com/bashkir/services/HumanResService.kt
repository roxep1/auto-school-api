package com.bashkir.services

import com.bashkir.models.Employee
import com.bashkir.models.Vacation
import org.jetbrains.exposed.sql.transactions.transaction

class HumanResService {
    fun getVacations(): List<Vacation.Model> = transaction {
        Vacation.all().map { it.toModel() }
    }

    fun createVacation(vacation: Vacation.Model) = transaction{
        Vacation.new {
            coef = vacation.coef
            employee = Employee[vacation.employee]
            periodOfVacation = vacation.periodOfVacation
        }
    }
}