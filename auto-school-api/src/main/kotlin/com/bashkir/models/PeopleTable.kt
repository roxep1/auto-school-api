package com.bashkir.models

import com.bashkir.EntityWithModel
import com.bashkir.PGEnum
import com.bashkir.StringEntityClass
import com.bashkir.StringIdTable
import com.bashkir.models.types.Code
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column

object PeopleTable: StringIdTable("people", "phonenumber", 11) {
    val name: Column<String> = varchar("name", 50)
    val lastName: Column<String> = varchar("lastname", 50)
    val middleName = varchar("middlename", 50).nullable()
    val email = varchar("email", 100).nullable()
    val login: Column<String> = varchar("login", 20)
    val password: Column<String> = varchar("password", 20)
    val code: Column<Code> =
        customEnumeration("codename", "Code", { value -> Code.valueOf(value as String) }, { PGEnum("Code", it) })
}

class People(id: EntityID<String>): Entity<String>(id), EntityWithModel<People.Model> {
    companion object : StringEntityClass<People>(PeopleTable)

    var name by PeopleTable.name
    var lastName by PeopleTable.lastName
    var middleName by PeopleTable.middleName
    var email by PeopleTable.email
    var login by PeopleTable.login
    var password by PeopleTable.password
    var code by PeopleTable.code

    override fun toModel() : Model = Model(this)

    @Serializable
    data class Model(
        @Transient private val man: People? = null
    ){
        val phoneNumber = man!!.id.value
        val name = man!!.name
        val lastName = man!!.lastName
        val middleName = man?.middleName
        val email = man?.email
        val login = man!!.login
        val password = man!!.password
        val code = man!!.code
    }
}
