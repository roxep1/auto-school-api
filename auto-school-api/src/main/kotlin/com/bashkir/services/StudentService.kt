package com.bashkir.services

import com.bashkir.models.*
import com.bashkir.models.types.Code
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.SizedIterable
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Instant
import java.time.LocalDateTime

class StudentService {
    fun getLessons(studentId: String): List<Lessons.Model> = transaction {
        Students[studentId].lessons.toList().map { it.toModel() }
    }

    fun deleteLessons(studentId: String, userNow: Instant) = transaction {
        Students[studentId].lessons.filter { it.date.isBefore(userNow) }.forEach { it.delete() }
    }

    fun deleteLessons(studentId: String) = transaction {
        Students[studentId].lessons.forEach { it.delete() }
    }

    fun getTeachers(): List<Employee.Model> = transaction {
        People.find { PeopleTable.code eq Code.TEACHER }.map { Employee[it.id].toModel() }
    }

    fun getTeacherLessons(teacherPhone: String): List<Lessons.Model> = transaction {
        Lessons.find { LessonsTable.phoneNumber eq teacherPhone }.toList()
            .filter { if (it.type.onePlace) it.students.empty() else true }
            .map { it.toModel() }
    }

    fun signUpTeacher(studentId: String, lessonId: Int) = transaction {
        val student = Students[studentId]
        val lessons = student.lessons.toMutableList()
        lessons.add(Lessons[lessonId])
        student.lessons = SizedCollection(lessons)
    }
}