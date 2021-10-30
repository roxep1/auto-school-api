package com.bashkir.services

import com.bashkir.models.People
import com.bashkir.models.PeopleTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class PeopleService {
    fun login(login: String, password: String): People.Model? = transaction {
        val res = People.find { PeopleTable.login eq login and (PeopleTable.password eq password) }
        if (!res.empty()) null else res.first().toModel()
    }
}