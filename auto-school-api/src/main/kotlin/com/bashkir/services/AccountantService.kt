package com.bashkir.services

import com.bashkir.models.Employee
import org.jetbrains.exposed.sql.transactions.transaction

class AccountantService {

    fun changeSalary(id: String, salary: Float) = transaction {
        val emp = Employee[id]
        emp.salary = salary
    }
}