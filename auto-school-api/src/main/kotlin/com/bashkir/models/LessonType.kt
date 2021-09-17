package com.bashkir.models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object LessonTypes: IntIdTable(columnName = "typeid"){
    val name: Column<String> = text("name")
    val duration: Column<Int> = integer("duration")
    val onePlace: Column<Boolean> = bool("oneplace")
}

class LessonType(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<LessonType>(LessonTypes)

    var name by LessonTypes.name
    var duration by LessonTypes.duration
    var onePlace by LessonTypes.onePlace
}