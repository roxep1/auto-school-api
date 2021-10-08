package com.bashkir.models

import com.bashkir.StringEntityClass
import com.bashkir.StringIdTable
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column

object PeopleTable: StringIdTable("people", "phonenumber", 11) {
    val name: Column<String> = varchar("name", 50)
    val lastName: Column<String> = varchar("lastname", 50)
    val middleName: Column<String> = varchar("middlename", 50)
    val email: Column<String> = varchar("email", 100)
    val login: Column<String> = varchar("login", 20)
    val password: Column<String> = varchar("password", 20)
}

class People(id: EntityID<String>): Entity<String>(id) {
    companion object : StringEntityClass<People>(PeopleTable)

    var name by PeopleTable.name
    var lastName by PeopleTable.lastName
    var middleName by PeopleTable.middleName
    var email by PeopleTable.email
    var login by PeopleTable.login
    var password by PeopleTable.password

    fun toModel() : PeopleModel = PeopleModel(this)
}

@Serializable
data class PeopleModel(
     @Transient private val man: People? = null
){
    val phoneNum = man?.id?.value
    val name = man?.name
    val lastName = man?.lastName
    val middleName = man?.middleName
    val email = man?.email
    val login = man?.login
    val password = man?.password
}