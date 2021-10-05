package com.bashkir.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column

object People: StringIdTable("people", "phonenumber", 11){
    val name: Column<String> = varchar("name", 50)
    val lastName: Column<String> = varchar("lastname", 50)
    val middleName: Column<String> = varchar("middlename", 50)
    val email: Column<String> = varchar("email", 100)
    val login: Column<String> = varchar("login", 20)
    val password: Column<String> = varchar("password", 20)
}

//@Serializable
class Man(id: EntityID<String>): Entity<String>(id) {
    companion object : StringEntityClass<Man>(People)

    var name by People.name
    var lastName by People.lastName
    var middleName by People.middleName
    var email by People.email
    var login by People.login
    var password by People.password
}

@Serializable
data class checkMan(
     @Transient val man: Man? = null
){
    val phoneNum = man?.id?.value
    val name = man?.name
    val lastName = man?.lastName
    val middleName = man?.middleName
    val email = man?.email
    val login = man?.login
    val password = man?.password
}