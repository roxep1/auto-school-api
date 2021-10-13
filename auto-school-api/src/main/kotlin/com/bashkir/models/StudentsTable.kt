package com.bashkir.models

import com.bashkir.EntityWithModel
import com.bashkir.StringEntityClass
import com.bashkir.StringIdTable
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.date

object StudentsTable : StringIdTable("students", "phonenumber", 11) {
    override val id: Column<EntityID<String>> = reference("phonenumber", PeopleTable)
    val graduation = date("graduationdate")
    val tariff = reference("tariffid", TariffTable)
}

class Students(id: EntityID<String>): Entity<String>(id), EntityWithModel<Students.Model>{
    companion object : StringEntityClass<Students>(StudentsTable){
        fun new(initEmp: Students.() -> Unit, initMan: People.() -> Unit): Students {
            val man = People.new(initMan)
            return Students.new(id = man.id.value, initEmp)
        }
    }

    var graduation by StudentsTable.graduation
    var tariff by Tariff referencedOn StudentsTable.tariff

    @Serializable
    data class Model(@Transient val model: Students? = null){
        val id = model?.id?.value
        val graduation = model?.graduation.toString()
        val tariff = model?.tariff?.name
    }

    override fun toModel(): Model = Model(this)
}
