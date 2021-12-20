package com.bashkir.services

import com.bashkir.models.Employee
import com.bashkir.models.People
import com.bashkir.models.Position
import com.bashkir.models.Tariff
import org.jetbrains.exposed.sql.transactions.transaction

class SharedService {
    fun getUsers(): List<People.Model> = transaction {
        People.all().map { it.toModel() }
    }

    fun getPositions(): List<String> = transaction {
        Position.all().map { it.toModel().position }
    }

    fun getTariffs(): List<Tariff.Model> = transaction {
        Tariff.all().map { it.toModel() }
    }

    fun getEmployees(): List<Employee.Model> = transaction {
        Employee.all().map { it.toModel() }
    }
}