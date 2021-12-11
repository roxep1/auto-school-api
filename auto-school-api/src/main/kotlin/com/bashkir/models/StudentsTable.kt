package com.bashkir.models

import com.bashkir.EntityWithModel
import com.bashkir.StringEntityClass
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.date

object StudentsTable : IdTable<String>("students") {
    override val id: Column<EntityID<String>> = reference("phonenumber", PeopleTable)
    val graduation = date("graduationdate")
    val tariff = reference("tariffid", TariffTable)
}

class Students(id: EntityID<String>) : Entity<String>(id), EntityWithModel<Students.Model> {
    companion object : StringEntityClass<Students>(StudentsTable) {
        fun new(id : String, initStudent: Students.() -> Unit, initMan: People.() -> Unit): Students {
            People.new(id, initMan)
            return Students.new(id, initStudent)
        }
    }

    var graduation by StudentsTable.graduation
    var tariff by Tariff referencedOn StudentsTable.tariff
    var lessons by Lessons via SingUpsTable

    @Serializable
    data class Model(@Transient val model: Students? = null, @Transient val withLessons: Boolean = true) {
        val id = model!!.id.value
        val graduation = model!!.graduation.toString()
        val tariff = model!!.tariff.toModel()
        val lessons = if (withLessons) model!!.lessons.map { it.toModel() } else listOf()
    }

    override fun toModel(): Model = Model(this)

    fun toModelWithoutLessons(): Model = Model(this, false)
}
