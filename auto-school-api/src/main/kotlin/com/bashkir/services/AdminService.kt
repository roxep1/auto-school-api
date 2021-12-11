package com.bashkir.services

import com.bashkir.models.*
import com.bashkir.models.types.Code
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate

class AdminService {
    fun createStudent(user: People.Model, student: Students.Model) = transaction {
        Students.new(user.phoneNumber, initStudent = {
            graduation = LocalDate.parse(student.graduation)
            tariff = Tariff[student.tariff.id]
        }) {
            set(user)
        }
    }

    fun createEmployee(user: People.Model, employee: Employee.Model) = transaction {
        Employee.new(user.phoneNumber, {
            peopleInfo = People[employee.peopleInfo.phoneNumber]
            salary = employee.salary
            coef = employee.coef
            position = Position[employee.positionName]
            dateOfDismissal = null
        }){
            set(user)
        }
    }

    private fun People.set(user: People.Model){
        name = user.name
        lastName = user.lastName
        middleName = user.middleName
        email = user.email
        login = user.cred!!.login
        password = user.cred.password
        code = Code.valueOf(user.code)
    }
}
