package com.bashkir.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object LessonTypeTable: IntIdTable( "lessontype", "typeid"){
    val name: Column<String> = varchar("name", 18)
    val duration: Column<Int> = integer("duration")
    val onePlace: Column<Boolean> = bool("oneplace")
}

class LessonType(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<LessonType>(LessonTypeTable)

    var name by LessonTypeTable.name
    var duration by LessonTypeTable.duration
    var onePlace by LessonTypeTable.onePlace

    fun toModel(): LessonTypeModel = LessonTypeModel(this)
}

@Serializable
data class LessonTypeModel(@Transient private val type: LessonType? = null) {

    val id = type?.id?.value
    val name = type?.name
    val duration = type?.duration
    val onePlace = type?.onePlace
}
