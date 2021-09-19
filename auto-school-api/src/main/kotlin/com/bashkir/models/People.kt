package com.bashkir.models

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column

object People: StringIdTable("people", "people", 11){
    val name: Column<String> = varchar("name", 50)
    val lastName: Column<String> = varchar("lastname", 50)
    val middleName: Column<String> = varchar("middlename", 50)
    val email: Column<String> = varchar("email", 100)
    val login: Column<String> = varchar("login", 20)
    val password: Column<String> = varchar("password", 20)
}
class Man(id: EntityID<String>): Entity<String>(id) {
    companion object : StringEntityClass<Man>(People)

    var name by People.name
    var lastName by People.lastName
    var middleName by People.middleName
    var email by People.email
    var login by People.login
    var password by People.password
}