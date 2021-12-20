package com.bashkir.models

import com.bashkir.EntityWithModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.javatime.timestamp

object LessonsTable : IntIdTable("lessons", "lessonid") {
    val date = timestamp("dateof")
    val type = reference("typeid", LessonTypeTable)
    val phoneNumber = reference("phonenumber", EmployeesTable)
}

class Lessons(id: EntityID<Int>) : IntEntity(id), EntityWithModel<Lessons.Model> {
    companion object : IntEntityClass<Lessons>(LessonsTable)

    var date by LessonsTable.date
    var type by LessonType referencedOn LessonsTable.type
    var phoneNumber by Employee referencedOn LessonsTable.phoneNumber
    var students by Students via SingUpsTable

    override fun toModel(): Model = Model(this)

    @Serializable
    data class Model(@Transient val model: Lessons? = null) {
        val id = model!!.id.value
        val date = model!!.date.toString()
        val type = model!!.type.toModel()
        val phoneNumber = model?.phoneNumber?.id?.value
        val students = model?.students?.map { it.toModelWithoutLessons() }
    }
}