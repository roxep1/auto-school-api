package com.bashkir.services

import com.bashkir.models.Lessons
import com.bashkir.models.Students
import org.jetbrains.exposed.sql.transactions.transaction

class LessonsService {
    fun getLessons(studentId: String): List<Lessons.Model> = transaction {
        Students[studentId].lessons.toList().map { it.toModel() }
    }
}