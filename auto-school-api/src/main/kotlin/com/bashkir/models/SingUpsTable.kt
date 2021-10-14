package com.bashkir.models

import org.jetbrains.exposed.sql.Table

object SingUpsTable : Table("singups") {
    val lesson = reference("lessonid", LessonsTable)
    val student = reference("phonenumber", StudentsTable)
    override val primaryKey = PrimaryKey(lesson, student)
}