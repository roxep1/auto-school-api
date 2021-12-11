package com.bashkir.services

import com.bashkir.models.Employee
import com.bashkir.models.LessonType
import com.bashkir.models.Lessons
import com.bashkir.models.LessonsTable
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant

class TeacherService {
    fun getLessons(id: String): List<Lessons.Model> = transaction {
        Lessons.find { LessonsTable.phoneNumber eq id }.map { it.toModel() }
    }

    fun createLesson(id: String, lesson: Lessons.Model) = transaction {
        Lessons.new {
            date = Instant.parse(lesson.date)
            phoneNumber = Employee[id]
            type = LessonType[lesson.type.id]
        }
    }

    fun getLessonTypes(): List<LessonType.Model> = transaction {
        LessonType.all().map { it.toModel() }
    }
}