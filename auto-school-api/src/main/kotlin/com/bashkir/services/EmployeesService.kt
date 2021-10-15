package com.bashkir.services

import com.bashkir.models.Employee
import org.jetbrains.exposed.sql.transactions.transaction

class EmployeesService {
    fun get(phoneNumber: String) = transaction {
        Employee[phoneNumber].toModel()
    }

    fun getFirst() = transaction {
        Employee.all().first().toModel()
    }
}