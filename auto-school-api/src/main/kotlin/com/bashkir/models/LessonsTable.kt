package com.bashkir.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.timestamp

object LessonsTable : IntIdTable("lessons", "lessonid") {
    val date = timestamp("dateof")
    val type = reference("typeid", ExamTypesTable)
    val phoneNumber = reference("phonenumber", EmployeesTable)
}

class Lessons(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Lessons>(LessonsTable)

    var date by LessonsTable.date
    var type by ExamTypes referencedOn LessonsTable.type
    var phoneNumber by Employee referencedOn LessonsTable.phoneNumber

    fun toModel(): LessonsModel = LessonsModel(this)
}

@Serializable
data class LessonsModel(@Transient val les: Lessons? = null) {
    val id = les?.id?.value
    val date = les?.date.toString()
    val type = les?.type?.id?.value
    val phoneNumber = les?.phoneNumber?.id?.value
}
