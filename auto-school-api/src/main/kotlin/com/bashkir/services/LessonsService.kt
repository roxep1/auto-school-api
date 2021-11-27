package com.bashkir.services

import com.bashkir.models.Lessons
import com.bashkir.models.LessonsTable
import com.bashkir.models.Students
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class LessonsService {
    fun getLessons(studentId: String): List<Lessons.Model> = transaction {
        Students[studentId].lessons.toList().map { it.toModel() }
    }

    fun deleteLessons(studentId: String, userNow: LocalDateTime) = transaction {
        Students[studentId].lessons.filter { it.date.isBefore(userNow) }.forEach { it.delete() }
    }

    fun deleteLessons(studentId: String) = transaction {
        Students[studentId].lessons.forEach { it.delete()}
    }
}